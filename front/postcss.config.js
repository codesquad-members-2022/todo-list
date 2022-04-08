module.exports = {
  plugins: [
    "postcss-preset-env",
    require("postcss-import"),
    require("autoprefixer"),
    require("postcss-nested"),
  ],
};
