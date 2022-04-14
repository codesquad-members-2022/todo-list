import peact from "../../core/peact";
import Columns from "./Column/Columns";

const Content = ({ columns, todos, handleRenderFlag }) => {
  return peact.createElement({
    tag: "div",
    child: [Columns({ columns, todos, handleRenderFlag })],
  });
};

export default Content;
