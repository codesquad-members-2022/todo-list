const express = require("express");
const path = require("path");
const app = express();
const port = 3000;

app.use(express.static(path.join(__dirname, "../src")));

app.use(function (req, res, next) {
  res.header("Access-Control-Allow-Origin", "*");
  next();
});

app.get("/", (req, res) => {
  res.sendFile(__dirname + "../src/index.html");
});

app.listen(port, (error) => {
  if (error) {
    throw error;
  }
  console.log(`Example app listening on port ${port}`);
});
