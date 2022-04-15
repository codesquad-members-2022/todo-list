const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const MiniCssExtractPlugin = require('mini-css-extract-plugin');
const webpack = require('webpack');
const dotenv = require('dotenv');

module.exports = env => {
  const { DEV } = env;

  if (DEV) {
    dotenv.config({ path: './dev.env' });
  } else {
    dotenv.config({ path: './.env' });
  }

  return {
    mode: 'development',
    entry: './src/client/js/app.js',
    output: {
      path: path.resolve(__dirname, 'public'),
      filename: 'js/bundle.js',
    },
    module: {
      rules: [
        {
          test: /\.js$/,
          use: 'babel-loader',
          exclude: /node_modules/,
        },
        {
          test: /\.scss$/,
          use: [MiniCssExtractPlugin.loader, 'css-loader', 'sass-loader'],
        },
        {
          test: /\.m?js/,
          resolve: {
            fullySpecified: false,
          },
        },
      ],
    },
    plugins: [
      new HtmlWebpackPlugin({
        template: 'index.html',
      }),
      new MiniCssExtractPlugin({
        filename: 'css/style.css',
      }),
      new webpack.ProvidePlugin({
        process: 'process/browser',
      }),
      new webpack.EnvironmentPlugin(['BASE_URL']),
    ],
  };
};
