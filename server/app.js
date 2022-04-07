const express = require("express");
const cors = require("cors");
const app = express();
const PORT = 3000;

app.use(cors());
app.get("/id", (req, res) => res.json());

app.listen(PORT, () => console.log(`Server running on ${PORT}`));
