import express from "express";

import { getTodoLog } from "../controllers/logController";

const logRouter = express.Router();

logRouter.get("/todo", getTodoLog);

export default logRouter;
