import express from "express";

import {
  getTodoById,
  getTodos,
  postTodoCreate,
  deleteTodoById,
  updateTodoById,
} from "../controllers/todoController";

const todoRouter = express.Router();

todoRouter.get("/", getTodos);
todoRouter.get("/:id", getTodoById);
todoRouter.post("/create", postTodoCreate);
todoRouter.delete("/delete/:id", deleteTodoById);
todoRouter.patch("/update/:id", updateTodoById);

export default todoRouter;
