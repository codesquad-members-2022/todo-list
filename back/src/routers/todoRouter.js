import express from "express";

import {
  getTodoById,
  getTodos,
  postTodoCreate,
  deleteTodoById,
  updateTodoById,
} from "../controllers/todoController";

const todoRouter = express.Router();

/**
 * @swagger
 *  /todo:
 *    get:
 *      summary: "전체 Todo 검색"
 *      tags: [Todo]
 *      responses:
 *        "200":
 *          description: 전체 Todo 검색
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
 *                      $ref: '#/components/schemas/Todo'
 */
todoRouter.get("/", getTodos);

/**
 * @swagger
 *  /todo/{id}:
 *    get:
 *      summary: "특정 Todo 검색"
 *      parameters:
 *        - in: path
 *          name: id
 *          required: true
 *      tags: [Todo]
 *      responses:
 *        "200":
 *          description: id 를 통해 특정 Todo 겁색
 *          content:
 *            application/json:
 *              schema:
 *                type: object
 *                properties:
 *                  ok:
 *                    type: boolean
 *                  results:
 *                    type: object
 *                    $ref: '#/components/schemas/Todo'
 */
todoRouter.get("/:id", getTodoById);

/**
 * @swagger
 *  /todo/create:
 *    post:
 *      summary: "Todo 생성"
 *      tags: [Todo]
 *      requestBody:
 *        content:
 *          application/json:
 *            schema:
 *              $ref: '#/components/schemas/CreateTodo'
 *      responses:
 *        "200":
 *          description: 새로운 Todo 생성
 *          content:
 *            application/json:
 *              schema:
 *                type: object
 *                properties:
 *                  ok:
 *                    type: boolean
 *                  results:
 *                    type: object
 *                    $ref: '#/components/schemas/Todo'
 */
todoRouter.post("/create", postTodoCreate);

/**
 * @swagger
 *  /todo/delete/{id}:
 *    delete:
 *      summary: "특정 Todo 삭제"
 *      parameters:
 *        - in: path
 *          name: id
 *          required: true
 *      tags: [Todo]
 *      responses:
 *        "200":
 *          description: id 를 통해 특정 Todo 삭제
 *          content:
 *            application/json:
 *              schema:
 *                type: object
 *                properties:
 *                  ok:
 *                    type: boolean
 *                  results:
 *                    type: object
 *                    $ref: '#/components/schemas/Todo'
 */
todoRouter.delete("/delete/:id", deleteTodoById);

/**
 * @swagger
 *  /todo/update/{id}:
 *    patch:
 *      summary: "특정 Todo 정보 수정"
 *      parameters:
 *        - in: path
 *          name: id
 *          required: true
 *      tags: [Todo]
 *      requestBody:
 *        content:
 *          application/json:
 *            schema:
 *              $ref: '#/components/schemas/CreateTodo'
 *      responses:
 *        "200":
 *          description: id 를 통해 특정 Todo 정보 수정
 *          content:
 *            application/json:
 *              schema:
 *                type: object
 *                properties:
 *                  ok:
 *                    type: boolean
 *                  results:
 *                    type: object
 *                    $ref: '#/components/schemas/Todo'
 */
todoRouter.patch("/update/:id", updateTodoById);

export default todoRouter;
