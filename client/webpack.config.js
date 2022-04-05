const path = require("path");

module.exports = {
  mode: "development", // 실서비스에서는 "production"
  entry: {
    app: ["./public/js/main.js"],
  },
  // devtool: "eval",
  output: {
    path: path.resolve(__dirname, "dist"),
    filename: "[name].bundle.js",
  },
  module: {
    rules: [
      {
        test: /\.js$/,
        exclude: /node_modules/,
        use: {
          loader: "babel-loader",
        },
      },
    ],
  },
};
