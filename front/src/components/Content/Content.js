import peact from "../../core/peact";
import Columns from "./Column/Columns";

const Content = ({ columns, todos }) => {
  return peact.createElement({
    tag: "div",
    child: [Columns({ columns, todos })],
  });
};

export default Content;
