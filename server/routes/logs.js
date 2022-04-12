const express = require("express");
const router = express.Router();
const Log = require("../models/log");

//Get Log
router.get("/", async (req, res) => {
  try {
    Log.find({})
      .sort({ createdAt: -1 })
      .exec((err, result) => {
        res.json(result);
      });
  } catch (err) {
    res.status(400).json({ message: err.message });
  }
});

module.exports = router;
