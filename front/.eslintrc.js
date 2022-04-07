module.exports = {
  parser: "@babel/eslint-parser",
  parserOptions: {
    requireConfigFile: false,
  },
  extends: ["airbnb-base", "prettier"],
  rules: {
    "prettier/prettier": "error",
  },
};
