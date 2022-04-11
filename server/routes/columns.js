const express = require("express");
const router = express.Router();
const Card = require("../models/card");
const Column = require("../models/column");

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
  if (req.body.name !== null) {
    res.column.name = req.body.name;
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
  const card = new Card({
    title: req.body.title,
    description: req.body.description,
  });

  try {
    const newCard = await card.save();
    res.column.cards.unshift(newCard);
    res.column.save();
    res.json(res.column);
  } catch (err) {
    res.status(400).json({ message: err.message });
  }
});

//Get Card
router.get("/:columnId/:cardId", getColumn, async (req, res) => {
  const card = res.column.cards.filter((v) => v["_id"] == req.params.cardId);
  try {
    res.json(card);
  } catch (err) {
    res.status(400).json({ message: err.message });
  }
});

//Update Card
router.patch("/:columnId", getColumn, async (req, res) => {
  if (req.body.name !== null) {
    res.column.name = req.body.name;
  }

  try {
    const updatedColumn = await res.column.save();
    res.json(updatedColumn);
  } catch (err) {
    res.status(400).json({ message: err.message });
  }
});

router.patch("/:columnId/:cardId/update", getColumn, async (req, res) => {
  const card = res.column.cards.filter((v) => v["_id"] == req.params.cardId)[0];

  if (req.body.title !== null) {
    card.title = req.body.title;
  }

  if (req.body.description !== null) {
    card.description = req.body.description;
  }

  try {
    res.column.save();
    res.json(res.column);
  } catch (err) {
    res.status(400).json({ message: err.message });
  }
});

//Delete Card
router.delete("/:columnId/:cardId/delete/", getColumn, async (req, res) => {
  try {
    res.column.cards.forEach((v) => {
      if (v["_id"] == req.params.cardId) {
        res.column.cards.splice(res.column.cards.indexOf(v), 1);
      }
    });

    res.column.save();
    res.json({ message: "Deleted Card" });
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

function getDocument(doc, collection) {
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
