import { $ } from "./utils/utils.js";
import { headInit } from "./header/view.js";
import { sidebarInit } from "./sidebar/view.js";
const header = $("header");
const aside = $("aside");

const init = () => {
  headInit(header);
  sidebarInit(aside);
};

init();
