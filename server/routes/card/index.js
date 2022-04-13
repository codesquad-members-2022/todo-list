import express from "express";
import tempDB from "../../DB/tempDB.js";

const router = express.Router();

// mansaout 계정
const tempDB_id_mansaout = tempDB.find((userInfo) => userInfo.id === "mansaout");

// http://localhost:3000/mansaout/card
router.get(`/`, (req, res) => {
  res.json(tempDB_id_mansaout.items);
});

router.get(`/:itemId`, (req, res) => {
  res.json(tempDB_id_mansaout.items.find((item) => item.id === Number(req.params.itemId)));
});

// router.post();
// router.delete();
// router.put();

export default router;
