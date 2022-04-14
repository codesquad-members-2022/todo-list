import peact from "../../../core/peact";
import Button from "../../../tagComponents/Button";
import styles from "./cardWritable.module.css";

const CardWritable = ({ handleNewCardVisibility, ref }) => {
  const $inputDesc = peact.createElement({
    tag: "input",
    className: styles.cardDescInput,
    attrs: {
      value: "",
      type: "text",
      name: "card-content",
      placeholder: "내용을 입력하세요",
    },
    child: [],
  });

  const $inputTitle = peact.createElement({
    tag: "input",
    className: styles.cardTitleInput,
    attrs: {
      value: "",
      type: "text",
      name: "card-title",
      placeholder: "제목을 입력하세요",
    },
    child: [],
  });

  const $cardWritableHeader = peact.createElement({
    tag: "div",
    className: styles.headerArea,
    child: [$inputTitle],
  });

  const $cancelButton = Button({
    onClick: handleNewCardVisibility,
    className: [styles.button, styles.cancelButton],
    innerHTML: "취소",
  });

  const $confirmButton = Button({
    className: [styles.button, styles.confirmButton, styles.activeButton],
    innerHTML: "등록",
  });

  const $buttonArea = peact.createElement({
    tag: "div",
    className: styles.buttons,
    child: [$cancelButton, $confirmButton],
  });

  return peact.createElement({
    tag: "div",
    className: [styles.cardWritable],
    child: [$cardWritableHeader, $inputDesc, $buttonArea],
    ref,
  });
};

export default CardWritable;
