import peact from "../../core/peact";
import styles from "./header.module.css";

const Header = () => {
  const $title = peact.createElement({
    tag: "h1",
    className: styles.title,
    child: ["TO-DO LIST"],
  });
  return peact.createElement({
    tag: "header",
    className: styles.titleArea,
    child: [$title],
  });
};

export default Header;
