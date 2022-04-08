import express from "express";

import { getColumn, postColumnCreate } from "../controllers/columnController";

const columnRouter = express.Router();

columnRouter.get("/", getColumn);
columnRouter.post("/create", postColumnCreate);

export default columnRouter;
