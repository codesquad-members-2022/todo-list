const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');

module.exports = {
    entry: "./app.ts",
    output: {
        path: path.resolve(__dirname, 'dist'),
        filename: 'bundle.js',
    },
    resolve: {
        extensions: ['', '.js', '.ts']
    },
    module: {
        rules: [
            {
                test: /\.tsx?$/, use: ['babel-loader', 'ts-loader'], exclude: /node_modules/
            },

            {
                test: /\.s[ac]ss$/i, // .css 확장자로 끝나는 모든 파일
                use: ['style-loader', 'css-loader', 'sass-loader'], // css-loader를 적용한다
            },
            {
                test: /\.png$/, // .png 확장자로 마치는 모든 파일
                loader: 'file-loader',
                options: {
                    publicPath: './dist/', // prefix를 아웃풋 경로로 지정
                    name: '[name].[ext]?[hash]', // 파일명 형식
                },
            },
        ],
    },
    plugins: [
        new HtmlWebpackPlugin({
            template: './index.html',
        }),
    ],
};
