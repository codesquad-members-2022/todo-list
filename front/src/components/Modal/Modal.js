import peact from "../../core/peact";
import todoApi from "../../service/todoApi";
import styles from "./modal.module.css";

const Modal = ({ handlers, isModalVisible, selectedTodoId, ref }) => {
  const { handleRenderFlag, handleSelectedTodoId, handleModalVisibility } =
    handlers;

  const deleteTodo = async () => {
    const deletedTodo = await todoApi.deleteTodo(selectedTodoId);

    if (deletedTodo) {
      handleRenderFlag();
      handleSelectedTodoId(null);
    }
  };

  const onModalClick = () => {
    handleModalVisibility();
    handleSelectedTodoId(null);
  };

  const $modalPopupMessage = peact.createElement({
    tag: "p",
    className: styles.alertMessage,
    child: ["선택한 카드를 삭제할까요?"],
  });

  const $modalPopupCalcelButton = peact.createElement({
    tag: "button",
    className: styles.cancelButton,
    attrs: {
      onClick: handleModalVisibility,
    },
    child: ["취소"],
  });

  const $modalPopupDeleteButton = peact.createElement({
    tag: "button",
    className: styles.deleteButton,
    attrs: {
      onClick: deleteTodo,
    },
    child: ["삭제"],
  });

  const $modalPopupButtons = peact.createElement({
    tag: "div",
    className: styles.modalButtons,
    child: [$modalPopupCalcelButton, $modalPopupDeleteButton],
  });

  const $modalPopup = peact.createElement({
    tag: "div",
    className: styles.modalPopup,
    child: [$modalPopupMessage, $modalPopupButtons],
  });

  return peact.createElement({
    tag: "div",
    className: [styles.modalWrap, ...(isModalVisible ? [styles.visible] : [])],
    child: [$modalPopup],
    attrs: {
      onClick: onModalClick,
    },
    ref,
  });
};

export default Modal;
