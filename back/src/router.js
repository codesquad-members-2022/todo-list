import express from "express";

import columnRouter from "./routers/columnRouter";
import logRouter from "./routers/logRouter";
import todoRouter from "./routers/todoRouter";

const router = express.Router();

/**
 *  @swagger
 *  tags:
 *    name: Todo
 *    description: Todo Management
 */
router.use("/todo", todoRouter);

/**
 *  @swagger
 *  tags:
 *    name: Column
 *    description: Column Management
 */
router.use("/column", columnRouter);

router.use("/log", logRouter);

export default router;
