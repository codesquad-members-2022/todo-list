const mongoose = require("mongoose");

const cardSchema = new mongoose.Schema(
  {
    type: { type: String },
    title: { type: String, required: true, maxlength: 25 },
    description: { type: String, required: true, maxlength: 100 },
  },
  {
    timestamps: true,
  }
);

const columnSchema = new mongoose.Schema({
  title: { type: String, required: true, maxlength: 25 },
  cards: [cardSchema],
});

module.exports = mongoose.model("Column", columnSchema);
