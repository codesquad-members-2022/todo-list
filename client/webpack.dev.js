const { merge } = require("webpack-merge");
const common = require("./webpack.common.js");
const path = require("path");

module.exports = merge(common, {
  mode: "development",
  devtool: "source-map",
  module: {
    rules: [
      {
        test: /\.(sa|sc|c)ss$/,
        exclude: /node_modules/,
        use: ["style-loader", "css-loader", "sass-loader"],
      },
    ],
  },

  devServer: {
    static: {
      directory: path.join(__dirname, "public"),
    },
    open: true,
    hot: true,
    port: 5000,
    // proxy,
  },
});
