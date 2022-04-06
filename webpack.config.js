const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');
__webpack_base_uri__ = 'http://localhost:5501';

const config = {
    entry: "./src/app.js",
    output: {
        path: path.resolve(__dirname, "dist"),
        filename: "bundle.js",
    }, module: {
        rules: [
            {
                test: /\.js$/,
                use: 'babel-loader',
                exclude: /node_modules/
            },
            {
              test:/\.css$/,
              use:['style-loader','css-loader'],
            },
        ]
    },
    plugins: [new HtmlWebpackPlugin(
        {
            template: 'src/app.html',
        }
    )]
}

module.exports = (env, argv) => {
  if (argv.mode === 'development') {
    config.mode = 'development';
    config.devtool = 'inline-source-map';
  }
  else {
    config.mode = 'production';
  }
  return config;
}
