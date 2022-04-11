import swaggerJSdoc from "swagger-jsdoc";
import swaggerUi from "swagger-ui-express";

import swaggerOptions from "./options";

const swaggerSpecs = swaggerJSdoc(swaggerOptions);

const swagger = [swaggerUi.serve, swaggerUi.setup(swaggerSpecs)];

export default swagger;
