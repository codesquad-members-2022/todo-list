import mongoose from "mongoose";

const todoLogSchema = new mongoose.Schema({
  title: String,
  todoId: { type: mongoose.Schema.Types.ObjectId },
  type: String,
  desc: String,
  author: String,
  columnId: String,
  createdAt: Date,
});

const model = mongoose.model("TodoLog", todoLogSchema);

export default model;
