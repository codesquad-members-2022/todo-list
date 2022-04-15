const path = require('path');
const MiniCssExtractPlugin = require('mini-css-extract-plugin');
const HtmlWebpackPlugin = require('html-webpack-plugin');

module.exports = {
  devtool: 'eval-cheap-source-map',
  resolve: {
    fallback: { fs: false, path: false },
  },
  entry: {
    main: './src/app.js',
  },
  module: {
    rules: [
      {
        test: /\.js$/,
        use: ['babel-loader'],
        exclude: /node_modules/,
      },
      {
        test: /\.(sc|c|sa)ss$/,
        use: [MiniCssExtractPlugin.loader, 'css-loader', 'sass-loader'],
      },
    ],
  },
  plugins: [
    new MiniCssExtractPlugin({ filename: 'css/style.css' }),
    new HtmlWebpackPlugin({
      template: path.join(__dirname, './index.html'),
    }),
  ],

  devServer: {
    devMiddleware: { publicPath: '/dist' },
    static: {
      directory: path.join(__dirname),
    },
    hot: true,
  },

  output: {
    clean: true,
    path: path.resolve('./dist'),
  },
};
