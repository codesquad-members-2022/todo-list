import { getISODateDiff } from "../../common/dateUtils";
import { pipe } from "../../common/utils";
import peact from "../../core/peact";
import Button from "../../tagComponents/Button";
import Action from "./Action/Action";
import styles from "./sideContent.module.css";

const menuBtnImageTemplate = `
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

const closeBtnImageTemplate = `
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

const toggleSideContent = (elements) => {
  elements.forEach(({ elementRef, className }) => {
    const element = elementRef.current;
    element.classList.toggle(className);
  });
};

const SideContent = ({ todoLogs, columns }) => {
  const actionsRef = peact.useRef();
  const menuBtnRef = peact.useRef();
  const closeBtnRef = peact.useRef();

  const insertColumnTitle = (todoLogs) => {
    return todoLogs.map((todoLog) => {
      const column = columns?.find((col) => col._id === todoLog.columnId);
      return { ...todoLog, columnTitle: column?.title };
    });
  };

  const sortTodoLogs = (todoLogs) => {
    return todoLogs.sort((aTodo, bTodo) =>
      getISODateDiff(bTodo.createdAt, aTodo.createdAt)
    );
  };

  const sortedTodoLogs = pipe(insertColumnTitle, sortTodoLogs)(todoLogs);

  const toggleElements = [
    { elementRef: actionsRef, className: styles.active },
    { elementRef: menuBtnRef, className: styles.btnActive },
  ];

  const handleClickMenuBtn = () => {
    toggleSideContent(toggleElements);
  };

  const handleClickCloseBtn = () => {
    toggleSideContent(toggleElements);
  };

  const $actionsWrap = peact.createElement({
    tag: "div",
    className: styles.actionWrap,
    child: sortedTodoLogs.map((todoLog) => Action({ todoLog })),
  });

  const $closeBtn = Button({
    className: styles.closeBtn,
    onClick: handleClickCloseBtn,
    innerHTML: closeBtnImageTemplate,
    ref: closeBtnRef,
  });

  const $menuBtn = Button({
    className: [styles.menuBtn, styles.btnActive],
    onClick: handleClickMenuBtn,
    innerHTML: menuBtnImageTemplate,
    ref: menuBtnRef,
  });

  const $actions = peact.createElement({
    tag: "div",
    className: styles.actions,
    child: [$actionsWrap, $closeBtn],
    ref: actionsRef,
  });

  return peact.createElement({
    tag: "div",
    className: styles.sideContent,
    child: [$menuBtn, $actions],
  });
};

export default SideContent;
