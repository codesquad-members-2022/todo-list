import { join, dirname } from "path";
import { getDate } from "./utils.js";
import jsonServer from "json-server";
import { Low, JSONFile } from "lowdb";
import { fileURLToPath } from "url";

const server = jsonServer.create();
const router = jsonServer.router("db.json");
const middlewares = jsonServer.defaults();
const __dirname = dirname(fileURLToPath(import.meta.url));

const adapter = new JSONFile(join(__dirname, "db.json"));
const db = new Low(adapter);

const PORT = process.env.PORT || 3000;

server.use(middlewares);
server.use(jsonServer.bodyParser);

/**
 * todo logs controllers
 */
const getTodoLogs = async () => {
  try {
    await db.read();
    const todoLogs = db.data.todoLogs;
    if (!todoLogs) {
      throw Error("Todo 로그를 찾지 못했습니다.");
    }
    return {
      ok: true,
      results: todoLogs,
    };
  } catch (error) {
    return {
      ok: false,
      message: error.message,
    };
  }
};

const createTodoLogs = async ({ type, oldTodo, newTodo }) => {
  try {
    const { id: lastId } = db.data.lastTodoLogsId;
    const createdAt = getDate();
    const updatedAt = createdAt;
    const logsData = { newTodo };
    if (type === "move") {
      logsData.oldTodo = oldTodo;
    }

    const newTodoLogs = {
      id: lastId + 1,
      type,
      ...logsData,
      createdAt,
      updatedAt,
    };

    db.data.todoLogs.push(newTodoLogs);

    db.data.lastTodoLogsId = { id: lastId + 1 };
    return {
      ok: true,
      results: newTodoLogs,
    };
  } catch (error) {
    return {
      ok: false,
      message: error.message,
    };
  }
};
/**
 * todo controllers
 */
const getTodos = async () => {
  try {
    await db.read();
    const todos = db.data.todo;
    if (!todos) {
      throw Error("Todo 리스트를 찾지 못했습니다.");
    }
    return {
      ok: true,
      results: todos,
    };
  } catch (error) {
    return {
      ok: false,
      message: error.message,
    };
  }
};

const getTodoById = async (id) => {
  try {
    const { results: todos } = await getTodos();
    const todo = todos.find((todo) => todo.id === +id);
    if (!todo) {
      throw Error("Todo 를 찾지 못했습니다.");
    }
    return {
      ok: true,
      results: todo,
    };
  } catch (error) {
    return {
      ok: false,
      message: error.message,
    };
  }
};

const postTodoCreate = async ({ title, desc, author, column }) => {
  try {
    await db.read();

    const { id: lastId } = db.data.lastTodoId;
    const createdAt = getDate();
    const updatedAt = createdAt;

    const newTodo = {
      id: lastId + 1,
      title,
      desc,
      author,
      column,
      createdAt,
      updatedAt,
    };

    db.data.todo.push(newTodo);

    db.data.lastTodoId = { id: lastId + 1 };
    await createTodoLogs({ type: "create", newTodo });
    await db.write();

    return {
      ok: true,
      results: newTodo,
    };
  } catch (error) {
    return {
      ok: false,
      message: error.message,
    };
  }
};

const deleteTodoById = async ({ id, logging = true }) => {
  try {
    await db.read();

    const { ok, results: deletedTodo } = await getTodoById(id);
    console.log(ok, deletedTodo);
    if (!ok) {
      throw Error("해당하는 ID의 Todo 가 없습니다.");
    }
    const { results: todos } = await getTodos();
    const newTodo = todos.filter((todo) => todo.id !== +id);
    db.data.todo = newTodo;

    if (logging) {
      await createTodoLogs({ type: "delete", newTodo: deletedTodo });
    }
    await db.write();

    return {
      ok: true,
      results: newTodo,
    };
  } catch (error) {
    return {
      ok: false,
      message: error.message,
    };
  }
};

const putTodoById = async (id, updatedData) => {
  try {
    await db.read();

    const { ok: isTodoGet, results: todo } = await getTodoById(id);
    if (!isTodoGet) {
      throw Error("해당하는 ID의 Todo 가 없습니다.");
    }
    const { ok: isTodoDeleted } = await deleteTodoById({ id, logging: false });
    if (!isTodoDeleted) {
      throw Error("Todo 를 삭제할 수 없습니다.");
    }
    const updatedAt = getDate();

    const newTodo = {
      ...todo,
      updatedAt,
      ...updatedData,
    };

    db.data.todo.push(newTodo);

    if (updatedData.column) {
      await createTodoLogs({ type: "move", oldTodo: todo, newTodo });
    } else {
      await createTodoLogs({ type: "update", newTodo });
    }

    await db.write();

    return {
      ok: true,
      results: newTodo,
    };
  } catch (error) {
    return {
      ok: false,
      message: error.message,
    };
  }
};

/**
 * todo logs routes
 */
server.get("/todo/logs", async (req, res) => {
  const sendData = await getTodoLogs();
  res.send(sendData);
});
/**
 * todo routes
 */
server.post("/todo/create", async (req, res) => {
  const { body } = req;
  const sendData = await postTodoCreate(body);
  res.send(sendData);
});

server.get("/todo", async (req, res) => {
  const sendData = await getTodos();
  res.send(sendData);
});

server.get("/todo/:id", async (req, res) => {
  const {
    params: { id },
  } = req;
  const sendData = await getTodoById(id);
  res.send(sendData);
});

server.delete("/todo/:id", async (req, res) => {
  const {
    params: { id },
  } = req;
  const sendData = await deleteTodoById({ id });
  res.send(sendData);
});

server.put("/todo/:id", async (req, res) => {
  const {
    params: { id },
    body,
  } = req;
  const sendData = await putTodoById(id, body);
  res.send(sendData);
});

server.use(router);

server.listen(PORT, () => {
  console.log(`✅ JSON server is listening on ${PORT}`);
});
