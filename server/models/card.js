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

module.exports = mongoose.model("Card", cardSchema);
