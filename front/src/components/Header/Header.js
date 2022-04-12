import peact from "../../core/peact";
import Button from "../../tagComponents/Button";
import styles from "./header.module.css";

const buttonInnerHTML = `
    <svg
        width="17"
        height="11"
        viewBox="0 0 17 11"
        fill="none"
        xmlns="http://www.w3.org/2000/svg"
    >
        <path
            d="M0 1V0H17V1H0ZM17 5V6H0V5H17ZM0 10H17V11H0V10Z"
            fill="black"
        />
    </svg>
`;

const Header = ({ onMenuClick }) => {
  const $title = peact.createElement({
    tag: "h1",
    className: styles.title,
    child: ["TO-DO LIST"],
  });
  return peact.createElement({
    tag: "header",
    className: styles.titleArea,
    child: [
      $title,
      Button({
        className: styles.menu,
        innerHTML: buttonInnerHTML,
        onClick: onMenuClick,
      }),
    ],
  });
};

export default Header;
