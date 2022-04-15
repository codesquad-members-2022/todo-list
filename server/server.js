const jsonServer = require("json-server");
const path = require("path");

const server = jsonServer.create();
const router = jsonServer.router(path.join(__dirname, "/db.json"));
const middlewares = jsonServer.defaults({
    static: path.join(__dirname, "..", "app"),
});

const port = process.env.PORT || 3000;

server.use(middlewares);
server.use(router);
server.listen(port, () => {
    console.log(path.join(__dirname, "..", "app"));
    console.log("JSON Server is running");
});
