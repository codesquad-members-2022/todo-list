import express from "express";

import {
  getColumns,
  getColumnById,
  postColumnCreate,
  deleteColumnById,
} from "../controllers/columnController";

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
 *  /column/{id}:
 *    get:
 *      summary: "특정 Column 검색"
 *      parameters:
 *        - in: path
 *          name: id
 *          required: true
 *      tags: [Column]
 *      responses:
 *        "200":
 *          description: id 를 통해 특정 Column 겁색
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
columnRouter.get("/:id", getColumnById);

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

/**
 * @swagger
 *  /column/delete/{id}:
 *    delete:
 *      summary: "특정 Column 삭제"
 *      parameters:
 *        - in: path
 *          name: id
 *          required: true
 *      tags: [Column]
 *      responses:
 *        "200":
 *          description: id 를 통해 특정 Column 삭제
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
columnRouter.delete("/delete/:id", deleteColumnById);

export default columnRouter;
