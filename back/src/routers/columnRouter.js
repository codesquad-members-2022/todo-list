import express from "express";

import { getColumns, postColumnCreate } from "../controllers/columnController";

const columnRouter = express.Router();

/**
 * @swagger
 *  /column:
 *    get:
 *      summary: "전체 Column 검색"
 *      tags: [Column]
 *      responses:
 *        "200":
 *          description: 전체 Column 검색
 *          content:
 *            application/json:
 *              schema:
 *                type: object
 *                properties:
 *                  ok:
 *                    type: boolean
 *                  results:
 *                    type: array
 *                    items:
 *                      $ref: '#/components/schemas/Column'
 */
columnRouter.get("/", getColumns);

/**
 * @swagger
 *  /column/create:
 *    post:
 *      summary: "Column 생성"
 *      tags: [Column]
 *      requestBody:
 *        content:
 *          application/json:
 *            schema:
 *              $ref: '#/components/schemas/CreateColumn'
 *      responses:
 *        "200":
 *          description: 새로운 Column 생성
 *          content:
 *            application/json:
 *              schema:
 *                type: object
 *                properties:
 *                  ok:
 *                    type: boolean
 *                  results:
 *                    type: object
 *                    $ref: '#/components/schemas/Column'
 */
columnRouter.post("/create", postColumnCreate);

export default columnRouter;
