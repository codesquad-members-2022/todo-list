import "./db";
import "./models/Todo";
import "./models/TodoLog";
import "./models/Column";
import app from "./app";
import { API_URL } from "./common/constants";

const PORT = process.env.PORT || 3000;

app.listen(PORT, () => {
  console.log(`✅ EXPRESS server is listening on ${API_URL(PORT)}`);
});
