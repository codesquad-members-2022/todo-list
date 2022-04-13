import { createRequire } from "module"; // Bring in the ability to create the 'require' method
const require = createRequire(import.meta.url);
import ejs from "ejs";
import express from "express";
import cors from "cors";
import * as path from "path";
import logger from "morgan";
import { fileURLToPath } from "url";

const db = require("./static/db.json");
const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);

const app = express();

app.use(cors());
app.use(logger());
app.use(express.static("static"));
app.set("views", __dirname + "/static");
app.set("view engine", "ejs");
app.engine("html", ejs.renderFile);

const PORT = 3000;

app.use((req, res, next) => {
  res.header("Access-Control-Allow-Origin", "*");
  next();
});
app.get("/", (req, res) => {
  res.json(db);
});

app.listen(PORT, () => console.log(`listening to http://localhost:${PORT}`));
