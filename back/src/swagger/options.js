const options = {
  swaggerDefinition: {
    openapi: "3.0.0",
    info: {
      title: "Todo List API",
      version: "1.0.0",
      description: "Todo List API with express + mongoDB + mongoose",
    },
    servers: [
      {
        url: "http://localhost:3000/api",
      },
    ],
  },
  apis: ["./src/routers/*.js", "./src/router.js", "./src/swagger/schemas/*"],
};

export default options;
