import peact from "core/peact";
import Button from "tagComponents/Button";

import styles from "./cardWritable.module.css";

const activateButton = ($button, deactiveClassName) => {
  $button.removeAttribute("disabled");
  $button.classList.remove(deactiveClassName);
};

const deactivateButton = ($button, deactiveClassName) => {
  $button.setAttribute("disabled", "");
  $button.classList.add(deactiveClassName);
};

const CardWritable = ({
  toggleCardVisible,
  handleSubmitForm,
  inputValues,
  columnId,
  isVisible,
  ref,
}) => {
  const addButtonRef = peact.useRef();
  const inputTitleRef = peact.useRef();
  const inputDescRef = peact.useRef();

  const handleKeyUpInput = () => {
    const $addButton = addButtonRef.current;
    const $inputTitle = inputTitleRef.current;
    const $inputDesc = inputDescRef.current;
    const isInputEmpty = $inputTitle.value === "" || $inputDesc.value === "";
    const isInputActive = !$addButton.classList.contains(styles.deactiveButton);
    if (isInputEmpty && isInputActive) {
      deactivateButton($addButton, styles.deactiveButton);
    } else if (!isInputEmpty && !isInputActive) {
      activateButton($addButton, styles.deactiveButton);
    }
  };

  const $inputAuthor = peact.createElement({
    tag: "input",
    className: styles.author,
    attrs: {
      value: inputValues ? inputValues.author : "비발디파크",
      type: "hidden",
      name: "author",
    },
    child: [],
  });

  const $inputDesc = peact.createElement({
    tag: "input",
    className: styles.cardDescInput,
    attrs: {
      value: inputValues ? inputValues.desc : "",
      type: "text",
      name: "desc",
      placeholder: "내용을 입력하세요",
      onKeyUp: handleKeyUpInput,
    },
    child: [],
    ref: inputDescRef,
  });

  const $inputTitle = peact.createElement({
    tag: "input",
    className: styles.cardTitleInput,
    attrs: {
      value: inputValues ? inputValues.title : "",
      type: "text",
      name: "title",
      placeholder: "제목을 입력하세요",
      onKeyUp: handleKeyUpInput,
    },
    child: [],
    ref: inputTitleRef,
  });

  const $cardWritableHeader = peact.createElement({
    tag: "div",
    className: styles.headerArea,
    child: [$inputTitle],
  });

  const $cancelButton = Button({
    onClick: toggleCardVisible,
    className: [styles.button, styles.cancelButton],
    innerHTML: "취소",
    type: "button",
  });

  const addButtonProps = {
    className: [styles.button, styles.addButton],
    innerHTML: "등록",
    type: "submit",
    ref: addButtonRef,
  };

  if (!inputValues) {
    addButtonProps.className = [
      ...addButtonProps.className,
      styles.deactiveButton,
    ];
    addButtonProps.disabled = "";
  }

  const $addButton = Button(addButtonProps);

  const $buttonArea = peact.createElement({
    tag: "div",
    className: styles.buttons,
    child: [$cancelButton, $addButton],
  });

  return peact.createElement({
    tag: "form",
    className: [styles.cardWritable, ...(isVisible ? [styles.show] : [])],
    attrs: {
      "data-column-id": columnId,
      onSubmit: handleSubmitForm,
    },
    child: [$inputAuthor, $cardWritableHeader, $inputDesc, $buttonArea],
    ref,
  });
};

export default CardWritable;
