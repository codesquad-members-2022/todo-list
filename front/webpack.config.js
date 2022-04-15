const path = require("path");

const { CleanWebpackPlugin } = require("clean-webpack-plugin");
const ErrorOverlayPlugin = require("error-overlay-webpack-plugin");
const ESLintPlugin = require("eslint-webpack-plugin");
const HtmlWebpackPlugin = require("html-webpack-plugin");

const API_URL = {
  LOCAL: "http://localhost:3000/api",
  HEROKU: "https://bbpark-todolist.herokuapp.com/api",
};

module.exports = (env) => {
  const { DEPLOY } = env;
  const isDeploy = JSON.parse(DEPLOY);

  return {
    mode: isDeploy ? "production" : "development",
    entry: ["./src/index.js"],
    output: {
      path: path.resolve(__dirname, "dist"),
      filename: "bundle.js",
    },
    plugins: [
      new ErrorOverlayPlugin(),
      new ESLintPlugin(),
      new CleanWebpackPlugin(),
      new HtmlWebpackPlugin({
        template: "./src/index.html",
      }),
    ],
    module: {
      rules: [
        {
          test: /\.css$/,
          use: ["style-loader", "css-loader", "postcss-loader"],
        },
        {
          test: /\.js$/,
          exclude: /node_modules/,
          use: [
            "babel-loader",
            {
              loader: "string-replace-loader",
              options: {
                search: "__API_END_POINT__",
                replace: isDeploy ? API_URL.HEROKU : API_URL.LOCAL,
                flags: "g",
              },
            },
          ],
        },
      ],
    },
    resolve: {
      alias: {
        common: path.resolve(__dirname, "./src/common/"),
        components: path.resolve(__dirname, "./src/components/"),
        core: path.resolve(__dirname, "./src/core/"),
        hooks: path.resolve(__dirname, "./src/hooks/"),
        service: path.resolve(__dirname, "./src/service/"),
        tagComponents: path.resolve(__dirname, "./src/tagComponents/"),
      },
    },
    ...(isDeploy ? {} : { devtool: "source-map" }),
  };
};
