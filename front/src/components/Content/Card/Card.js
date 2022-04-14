import peact from "../../../core/peact";
import Modal from "../../Modal/Modal";
import styles from "./card.module.css";

const Card = ({ todo, handleRenderFlag }) => {
  const showAlert = ({ todoId }) => {
    const $body = document.querySelector("body");
    $body.append(Modal({ todoId, handleRenderFlag }));
  };

  const handleXButton = (target) => {
    const $path = target.querySelector(`.${styles.path}`) || target;
    const $cardElement = target.closest(`.${styles.card}`);

    $path.classList.toggle(styles.pathMouseOver);
    $cardElement.classList.toggle(styles.cardMouseOver);
  };

  const onXButtonOver = ({ target }) => {
    handleXButton(target);
  };

  const onXButtonOut = ({ target }) => {
    handleXButton(target);
  };

  const onXButtonClick = ({ target }) => {
    const todoId = target.closest(`.${styles.card}`).id;
    showAlert({ todoId });
  };

  const xButtonInnerHTML = `
    <svg
    width="12"
    height="12"
    viewBox="0 0 12 12"
    fill="none"
    xmlns="http://www.w3.org/2000/svg"
    class="${styles.xButton}"
    >
      <path
        d="M1.5 11.25L0.75 10.5L5.25 6L0.75 1.5L1.5 0.75L6 5.25L10.5 0.75L11.25 1.5L6.75 6L11.25 10.5L10.5 11.25L6 6.75L1.5 11.25Z"
        class="${styles.path}"
      />
    </svg>
  `;

  const $cardHeaderTitle = peact.createElement({
    tag: "label",
    className: styles.title,
    child: [todo.title],
  });

  const $xButtonWrap = peact.createElement({
    tag: "div",
    attrs: {
      onClick: onXButtonClick,
      onMouseOver: onXButtonOver,
      onMouseOut: onXButtonOut,
    },
    child: [xButtonInnerHTML],
  });

  const $cardHeaderArea = peact.createElement({
    tag: "div",
    className: styles.headerArea,
    child: [$cardHeaderTitle, $xButtonWrap],
  });

  const $cardContent = peact.createElement({
    tag: "div",
    className: styles.cardContent,
    child: [`<label class="${styles.cardContent}">${todo.desc}</label>`],
  });

  const $cardAuthor = peact.createElement({
    tag: "div",
    className: styles.author,
    child: [todo.author],
  });

  const $card = peact.createElement({
    tag: "div",
    className: styles.card,
    attrs: {
      id: todo._id,
    },
    child: [$cardHeaderArea, $cardContent, $cardAuthor],
  });

  return $card;
};

export default Card;
