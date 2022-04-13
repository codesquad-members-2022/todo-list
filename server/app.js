import express from "express";
import router from "./routes/index.js";
import cors from "cors";
import tempDB from "./DB/tempDB.js";

const userId = "mansaout";
const PORT = process.env.PORT || 3000;
const app = express();
app.locals.pretty = true;

app.get("/", (req, res) => {
  res.json(tempDB);
});

app.use(cors());
app.use(`/${userId}`, router);

app.listen(PORT, (err) => {
  err ? console.log(`err: ${err}`) : console.log(`Listening on port ${PORT}`);
});
