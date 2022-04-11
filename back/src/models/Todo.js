import mongoose from "mongoose";

const todoSchema = new mongoose.Schema({
  title: String,
  desc: String,
  author: String,
  columnId: { type: mongoose.Schema.Types.ObjectId, ref: "Column" },
  createdAt: Date,
  updatedAt: Date,
});

const model = mongoose.model("Todo", todoSchema);

export default model;
