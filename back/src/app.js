import bodyParser from "body-parser";
import express from "express";

import router from "./router";
import swagger from "./swagger/swagger";

const app = express();

app.use(bodyParser.json());

app.use("/api-docs", swagger);
app.use("/api", router);

export default app;
