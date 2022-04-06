const path = require('path');
const MiniCssExtractPlugin = require('mini-css-extract-plugin');

module.exports = {
  mode: 'development',
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
  plugins: [new MiniCssExtractPlugin({ filename: 'css/style.css' })],

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
