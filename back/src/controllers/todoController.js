import { LOG_TYPE } from "../common/constants";
import { getDate, sendMethodResult } from "../common/utils";
import Todo from "../models/Todo";
import { createTodoLog } from "./logController";

export const getTodos = sendMethodResult(async () => {
  const todos = await Todo.find();
  return todos;
});

export const getTodoById = sendMethodResult(async (req) => {
  const {
    params: { id },
  } = req;

  const todo = await Todo.findById(id);

  if (!todo) {
    throw Error("해당하는 ID의 Todo 가 없습니다.");
  }

  return todo;
});

export const postTodoCreate = sendMethodResult(async (req) => {
  const { body } = req;
  const createdAt = getDate();
  const updatedAt = createdAt;
  const newTodoData = {
    ...body,
    createdAt,
    updatedAt,
  };

  const newTodo = await Todo.create(newTodoData);
  const type = LOG_TYPE.CREATE;

  const { ok: isLogCreate, message: logFailMessage } = await createTodoLog({
    type,
    logData: newTodo,
  });

  if (!isLogCreate) {
    throw Error(logFailMessage);
  }

  return newTodo;
});

export const deleteTodoById = sendMethodResult(async (req) => {
  const {
    params: { id },
  } = req;

  const deletedTodo = await Todo.findByIdAndRemove(id);
  const type = LOG_TYPE.DELETE;

  const { ok: isLogCreate, message: logFailMessage } = await createTodoLog({
    type,
    logData: deletedTodo,
  });

  if (!isLogCreate) {
    throw Error(logFailMessage);
  }

  return deletedTodo;
});

export const updateTodoById = sendMethodResult(async (req) => {
  const {
    params: { id },
    body,
  } = req;

  const todoExist = await Todo.exists({ _id: id });

  if (!todoExist) {
    throw Error("해당 Todo 가 없습니다.");
  }

  const updatedTodo = await Todo.findByIdAndUpdate(id, body);
  const type = body.columnId ? LOG_TYPE.MOVE : LOG_TYPE.UPDATE;

  const { ok: isLogCreate, message: logFailMessage } = await createTodoLog({
    type,
    logData: updatedTodo,
  });

  if (!isLogCreate) {
    throw Error(logFailMessage);
  }

  return updatedTodo;
});
