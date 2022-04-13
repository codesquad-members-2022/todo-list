import express from "express";

import card from "./card/index.js";
// import column from "./column/index.js";
// import user from "./user/index.js";

const router = express.Router();

router.use("/card", card);
// router.use("/column", column);
// router.use("/user", user);

export default router;
