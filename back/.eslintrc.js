module.exports = {
  parser: "@babel/eslint-parser",
  parserOptions: {
    requireConfigFile: false,
  },
  extends: ["airbnb-base", "prettier"],
  rules: {
    "import/order": [
      "error",
      {
        groups: ["builtin", "external", "internal", "index"],
        alphabetize: {
          order: "asc",
          caseInsensitive: true,
        },
        "newlines-between": "always",
      },
    ],
  },
};
