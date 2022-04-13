import peact from "../../core/peact";
import Button from "../../tagComponents/Button";
import Action from "./Action/Action";
import styles from "./sideContent.module.css";

const menuBtnTag = `
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

const closeBtnTag = `
  <svg
    width="12"
    height="12"
    viewBox="0 0 12 12"
    fill="none"
    xmlns="http://www.w3.org/2000/svg"
  >
    <path
      d="M1.5 11.25L0.75 10.5L5.25 6L0.75 1.5L1.5 0.750004L6 5.25L10.5 0.750004L11.25 1.5L6.75 6L11.25 10.5L10.5 11.25L6 6.75L1.5 11.25Z"
      fill="#010101"
    />
  </svg>
`;

const SideContent = ({ todoLogs, columns }) => {
  const newTodoLogs = todoLogs.map((todoLog) => {
    const column = columns?.find((col) => col._id === todoLog.columnId);
    return { ...todoLog, columnTitle: column?.title };
  });

  const $actionsWrap = peact.createElement({
    tag: "div",
    className: styles.actionWrap,
    child: newTodoLogs.map((todoLog) => Action({ todoLog })),
  });

  const toggleSideContent = (elements) => {
    elements.forEach(({ element, className }) => {
      element.classList.toggle(className);
    });
  };

  const handleCloseBtn = ({ target }) => {
    const $closeBtn = target.closest(`.${styles.closeBtn}`);
    const $actions = $closeBtn.parentNode;
    const $menuBtn = $actions.parentNode.querySelector(`.${styles.menuBtn}`);
    const elements = [
      { element: $actions, className: styles.active },
      { element: $menuBtn, className: styles.btnActive },
    ];
    toggleSideContent(elements);
  };

  const $closeBtn = Button({
    className: styles.closeBtn,
    onClick: handleCloseBtn,
    innerHTML: closeBtnTag,
  });

  const $actions = peact.createElement({
    tag: "div",
    className: styles.actions,
    child: [$actionsWrap, $closeBtn],
  });

  const handleMenuBtn = ({ target }) => {
    const $menuBtn = target.closest(`.${styles.menuBtn}`);
    const elements = [
      { element: $actions, className: styles.active },
      { element: $menuBtn, className: styles.btnActive },
    ];
    toggleSideContent(elements);
  };

  const $menuBtn = Button({
    className: [styles.menuBtn, styles.btnActive],
    onClick: handleMenuBtn,
    innerHTML: menuBtnTag,
  });

  return peact.createElement({
    tag: "div",
    className: styles.sideContent,
    child: [$menuBtn, $actions],
  });
};

export default SideContent;
