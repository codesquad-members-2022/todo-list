module.exports = {
  parser: "@babel/eslint-parser",
  parserOptions: {
    requireConfigFile: false,
  },
  env: {
    browser: true,
  },
  extends: [
    "airbnb-base",
    "plugin:prettier/recommended",
    "plugin:import/recommended",
  ],
  rules: {
    "prettier/prettier": [
      "error",
      {
        endOfLine: "auto",
      },
    ],
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
    "no-bitwise": "off",
    "consistent-return": "off",
    "import/prefer-default-export": "off",
    "func-names": "off",
    "no-underscore-dangle": ["error", { allow: ["_id"] }],
  },
};
