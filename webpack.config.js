const path = require("path");

module.exports = {
  mode: "development",
  entry: "./src/app.js",
  devtool: "eval-source-map",
  output: {
    path: path.resolve(__dirname, "public"),
    filename: "bundle.js",
  },
  module: {
    rules: [
      {
        test: /\.js$/,
        exclude: /node_modules/,
        use: {
          loader: "babel-loader",
        },
        test: /\.scss$/,
        use: [
          { loader: "style-loader" },
          { loader: "css-loader" },
          { loader: "sass-loader" },
        ],
      },
    ],
  },
  watch: true,
  watchOptions: {
    aggregateTimeout: 200,
    ignored: /node_modules/,
    poll: 1000,
  },
};
