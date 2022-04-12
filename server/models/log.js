const mongoose = require("mongoose");

//.save()로 저장하게 되면 고유한 id 생성 됨
const logSchema = new mongoose.Schema(
  {
    userId: { type: String, default: "crong" },
    log: { type: String, required: true },
  },
  {
    timestamps: true,
  }
);

module.exports = mongoose.model("Log", logSchema);

//Log.find().sort({"timestamp": -1})
