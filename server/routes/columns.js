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
router.get("/:id", getColumn, (req, res) => {
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
router.patch("/:id", getColumn, async (req, res) => {
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
router.delete("/:id", getColumn, async (req, res) => {
  try {
    await res.column.remove();
    res.json({ message: "Deleted Column" });
  } catch (err) {
    res.status(500).json({ message: err.message });
  }
});

//Add Card
router.post("/:id/add", getColumn, async (req, res) => {
  const card = new Card({
    title: req.body.title,
    description: req.body.description,
    column: res.column._id,
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

//Delete Card
// router.delete("/:id/delete", getColumn, async(req, res) => {
//   //column 정보는 있음
//   //card 정보로 특정을 해서 지워야
// })

//Middleware
async function getColumn(req, res, next) {
  let column;
  try {
    column = await Column.findById(req.params.id);
    if (column == null) {
      return res.status(404).json({ message: "Cannot find column" });
    }
  } catch (err) {
    return res.status(500).json({ message: err.message });
  }

  res.column = column;
  next();
}

module.exports = router;
