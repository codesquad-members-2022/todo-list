import mongoose from "mongoose";

const columnSchema = new mongoose.Schema({
  title: String,
  createdAt: Date,
  updatedAt: Date,
});

const model = mongoose.model("Column", columnSchema);

export default model;
