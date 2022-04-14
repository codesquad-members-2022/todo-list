import { SERVICE_DOMAIN } from "../common/constants";

const cors = (req, res, next) => {
  const isProduction = process.env.NODE_ENV === "production";
  const accessControls = [
    {
      key: "Access-Control-Allow-Origin",
      value: isProduction ? SERVICE_DOMAIN : "*",
    },
    {
      key: "Access-Control-Allow-Methods",
      value: isProduction ? SERVICE_DOMAIN : "*",
    },
    {
      key: "Access-Control-Allow-Headers",
      value: isProduction ? SERVICE_DOMAIN : "*",
    },
  ];
  accessControls.forEach((control) => {
    const { key, value } = control;
    res.setHeader(key, value);
  });
  next();
};

export default cors;
