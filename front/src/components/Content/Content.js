import peact from "../../core/peact";

const Content = ({ content }) => {
  return peact.createElement({
    tag: "div",
    child: [content],
  });
};

export default Content;
