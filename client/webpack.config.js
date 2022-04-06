const path = require("path");
const HtmlWebpackPlugin = require("html-webpack-plugin");
const MiniCssExtractPlugin = require("mini-css-extract-plugin");

module.exports = {
  mode: "development", // 실서비스에서는 "production"
  entry: {
    app: ["./public/js/app.js"],
  },

  devtool: "source-map",
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
      {
        test: /\.(sa|sc|c)ss$/,
        use: [
          /* devMode ? 'style-loader' : */
          MiniCssExtractPlugin.loader,
          "css-loader",
          "sass-loader",
        ],
      },
      {
        test: /\.(png|jpe?g|gif|svg|ico)$/,
        use: [
          {
            loader: "url-loader",
            options: {
              useRelativePath: true,
              limit: 10000,
            },
          },
        ],
      },
    ],
  },

  plugins: [
    new MiniCssExtractPlugin({
      filename: "[name].css",
    }),
    new HtmlWebpackPlugin({
      template: "public/index.html",
    }),
  ],

  devServer: {
    static: {
      directory: path.join(__dirname, "public"),
    },
    open: true,
    hot: true,
    port: "5000",
  },
};
