const mongoose = require("mongoose");

//.save()로 저장하게 되면 고유한 id 생성 됨
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
