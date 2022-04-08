import bodyParser from "body-parser";
import express from "express";

import columnRouter from "./routers/columnRouter";
import logRouter from "./routers/logRouter";
import todoRouter from "./routers/todoRouter";

const app = express();
app.use(bodyParser.json());
app.use("/todo", todoRouter);
app.use("/column", columnRouter);
app.use("/log", logRouter);

export default app;
