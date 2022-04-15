import { initColumnContainer } from "./column-container/ColumnContainer";
import { pipe } from "@/common/util";

const makeMainDOM = () => {
  const mainDOM = document.createElement("div");
  mainDOM.className = "main";
  return mainDOM;
};

const renderMain = (mainDOM) => {
  document.body.append(mainDOM);
  return mainDOM;
};

const mountColumnContainer = (mainDOM) => {
  initColumnContainer(mainDOM);
};

export const initMain = pipe(makeMainDOM, renderMain, mountColumnContainer);
