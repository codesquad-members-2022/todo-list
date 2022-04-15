const express = require("express");
const router = express.Router();
const Card = require("../models/card");
const Column = require("../models/column");
const Log = require("../models/log");

//Getting ALl
router.get("/", async (req, res) => {
  try {
    const columns = await Column.find();
    res.json(columns);
  } catch (err) {
    res.status(500).json({ message: err.message });
  }
});

//Getting One
router.get("/:columnId", getColumn, (req, res) => {
  res.json(res.column);
});

//Creating One
router.post("/", async (req, res) => {
  const column = new Column({
    title: req.body.title,
    cards: [],
  });

  try {
    const newColumn = await column.save();
    res.status(201).json(newColumn);
  } catch (err) {
    res.status(400).json({ message: err.message });
  }
});

//Updating One
router.patch("/:columnId", getColumn, async (req, res) => {
  const {
    body: { title }
  } = req;

  if (title !== null) {
    res.column.title = title;
  }

  try {
    const updatedColumn = await res.column.save();
    res.json(updatedColumn);
  } catch (err) {
    res.status(400).json({ message: err.message });
  }
});

//Deleting One
router.delete("/:columnId", getColumn, async (req, res) => {
  try {
    await res.column.remove();
    res.json({ message: "Deleted Column" });
  } catch (err) {
    res.status(500).json({ message: err.message });
  }
});

//Add Card
router.post("/:columnId/add", getColumn, async (req, res) => {
  const {
    body: { title, description }
  } = req;
  const card = new Card({
    title: title,
    description: description
  });

  const log = new Log({
    log: `${res.column.title}에 ${card.title}를 등록하였습니다.`
  });

  try {
    const newCard = await card.save();
    await log.save();
    res.column.cards.unshift(newCard);
    res.column.save();
    res.json(res.column);
  } catch (err) {
    res.status(400).json({ message: err.message });
  }
});

//Get Card
router.get("/:columnId/:cardId", getColumn, async (req, res) => {
  const card = res.column.cards.filter((v) => v["_id"] == req.params.cardId)[0];
  try {
    res.json(card);
  } catch (err) {
    res.status(400).json({ message: err.message });
  }
});

//Update Card
router.patch("/:columnId/:cardId/update", getColumn, async (req, res) => {
  const {
    body: { name, title, description }
  } = req;
  const card = res.column.cards.filter((v) => v["_id"] == req.params.cardId)[0];
  const temp = card.title;

  if (title !== null) {
    card.title = req.body.title;
  }

  if (description !== null) {
    card.description = description;
  }

  const log = new Log({
    log: `${temp}이/가 ${card.title}로 변경되었습니다.`
  });

  try {
    await log.save();
    res.column.save();
    res.json(res.column);
  } catch (err) {
    res.status(400).json({ message: err.message });
  }
});

//Delete Card
router.delete("/:columnId/:cardId/delete/", getColumn, async (req, res) => {
  const card = res.column.cards.filter((v) => v["_id"] == req.params.cardId)[0];
  const log = new Log({
    log: `${card.title}이/가 ${res.column.title}에서 삭제되었습니다.`
  });

  try {
    res.column.cards.splice(res.column.cards.indexOf(card), 1);
    await Card.deleteOne({ _id: card["_id"] });
    await log.save();
    res.column.save();
    res.json(res.column);
  } catch (err) {
    res.status(400).json({ message: err.message });
  }
});

//Move Card
router.post("/:columnId/:cardId/:targetColumnId/:index", getColumn, async (req, res) => {
  const card = res.column.cards.filter((v) => v["_id"] == req.params.cardId)[0];
  const targetColumn = await Column.findById(req.params.targetColumnId);
  const log = new Log({
    log: `${card.title}이/가 ${res.column.title}에서 ${targetColumn.title}로 이동되었습니다.`,
  });

  try {
    const tempCard = res.column.cards.splice(res.column.cards.indexOf(card), 1)[0];
    targetColumn.cards.splice(req.params.index, 0, tempCard);
    await res.column.save();
    await targetColumn.save();
    await log.save();
    const columns = await Column.find();
    res.json(columns);
  } catch (err) {
    res.status(400).json({ message: err.message });
  }
});

//Middleware
async function getColumn(req, res, next) {
  let column;
  try {
    column = await Column.findById(req.params.columnId);
    if (column == null) {
      return res.status(404).json({ message: "Cannot find column" });
    }
  } catch (err) {
    return res.status(500).json({ message: err.message });
  }

  res.column = column;
  next();
}

async function getDocument(doc, collection) {
  return async (req, res, next) => {
    let doc;
    try {
      doc = await collection.findById(req.params.id);
      if (doc == null) {
        return res.status(404).json({ message: `Cannot find ${doc}` });
      }
    } catch (err) {
      return res.status(500).json({ message: err.message });
    }

    res.doc = doc;
    next();
  };
}

module.exports = router;
