import { iconDelete } from "../constants/imagePath.js";
let timer;

const taskItemClassName = "column__task--item";
const taskListClassName = "column__task--list";

const taskCreator = (origin) => {
  const task = {
    origin: origin,
    copy: createCopyTask(origin),
    moved: createCopyTask(origin, true),
  };

  return (type = null) => {
    return type ? task[type] : task;
  };
};

const createCopyTask = (taskElement, isMoved = false) => {
  const [sectionElement] = [...taskElement.children];
  const copyTaskElement = document.createElement("li");
  copyTaskElement.style.width = "308px";
  copyTaskElement.ondragstart = () => false;
  copyTaskElement.dataset.title = taskElement.dataset.title;
  copyTaskElement.classList.add(taskItemClassName);
  copyTaskElement.innerHTML = createTaskHTML([...sectionElement.children]);
  copyTaskElement.addEventListener("mouseup", mouseUpHandler);

  if (!isMoved) {
    copyTaskElement.classList.add("dragging");
  }

  setMouseEvent(copyTaskElement);
  return copyTaskElement;
};

const createTaskHTML = ([header, comment, author]) => {
  const [title] = [...header.children];
  return /* html */ `
    <section>
      <div class="section__header">
        <input readonly type="text" class="column__task--title" value="${title.value}" />
        <img src="${iconDelete}" class="column__task--delete-button" />
      </div>
      <textarea readonly class="column__task--comment" spellcheck="false">${comment.value}</textarea>
      <span class="column__task--author">${author.innerText}</span>
    </section>`;
};

const setMouseEvent = (taskItem) => {
  if (isRegistrationCard(taskItem)) return;
  taskItem.addEventListener("mousedown", (event) => handleGrabbing(event));
  taskItem.addEventListener("mouseup", mouseUpHandler);
};

const isRegistrationCard = (taskItem) => taskItem.classList.contains("registration-card");

const handleGrabbing = (event) => {
  const minGrabbingTime = 250;
  if (timer) clearTimeout(timer);
  timer = setTimeout(() => mouseDownHandler(event), minGrabbingTime);
};

const mouseDownHandler = (event) => {
  if (timer) timer = null;
  const target = event.target;
  if (isDeleteButton(target)) {
    return;
  }
  const taskElement = target.closest("li");
  const getTaskObject = taskCreator(taskElement);
  const copyTaskElement = getTaskObject("copy");
  document.body.appendChild(copyTaskElement);

  const moveAt = createMoveAtFunc(event, taskElement, copyTaskElement);
  moveAt(event.pageX, event.pageY);

  setDocumentEvent(moveAt, getTaskObject);
};

const isDeleteButton = (target) => target.classList.contains("column__task--delete-button");

const createMoveAtFunc = (event, originElement, copyElement) => {
  const { shiftX, shiftY } = getShiftCoordinate(event, originElement);
  return (pageX, pageY) => {
    copyElement.style.left = pageX - shiftX + "px";
    copyElement.style.top = pageY - shiftY + "px";
  };
};

const getShiftCoordinate = (event, taskElement) => {
  const shiftX = event.clientX - taskElement.getBoundingClientRect().left;
  const shiftY = event.clientY - taskElement.getBoundingClientRect().top;
  return { shiftX: shiftX, shiftY: shiftY };
};

const setDocumentEvent = (moveAtFunc, getTaskObject) => {
  document.moveAt = moveAtFunc;
  document.getTaskObject = getTaskObject;
  document.addEventListener("mousemove", mouseMoveHandler);
};

const mouseMoveHandler = (event) => {
  const target = event.currentTarget;
  target.moveAt(event.pageX, event.pageY);

  const copyElement = target.getTaskObject("copy");
  copyElement.style.opacity = 0.8;
  const belowElement = getElementByCoordinate(copyElement, { x: event.clientX, y: event.clientY });

  if (belowElement === null) {
    copyElement.dispatchEvent(new Event("mouseup"));
    return;
  }

  const [standardCard, isNewPosition] = handleMovedCardUpDown(belowElement);
  const movingTaskList = belowElement.closest(`.${taskListClassName}`);
  movingTaskList && setTaskList(movingTaskList, [isNewPosition, standardCard]);
};

const handleMovedCardUpDown = (belowElement) => {
  let standardCard;
  if (!belowElement.closest(".dragging") && belowElement.closest(".column__task--item")) {
    standardCard = belowElement.closest(".column__task--item");
  }
  return standardCard ? [standardCard, true] : [null, false];
};

const getElementByCoordinate = (element, { x, y }) => {
  element.classList.add("hidden");
  const belowElement = document.elementFromPoint(x, y);
  element.classList.remove("hidden");
  return belowElement;
};

const setTaskList = (movingList, [isNewPosition, standardCard]) => {
  const taskObject = document.getTaskObject();
  document.getTaskObject("origin").style.display = "none";

  const movingCard = taskObject["moved"];
  movingCard.classList.add("blur");
  movingList.insertBefore(movingCard, isNewPosition ? standardCard : null);
};

const mouseUpHandler = ({ target }) => {
  if (timer) {
    clearTimeout(timer);
    return;
  }
  if (isDeleteButton(target)) {
    return;
  }

  const taskElement = target.closest("li");
  removeDocumentEvent();
  taskElement.removeEventListener("mouseup", mouseUpHandler);
  taskElement.remove();

  document.getTaskObject("moved").classList.remove("blur");
  document.getTaskObject("origin").remove();

  delete document.getTaskObject;
};

const removeDocumentEvent = () => {
  document.removeEventListener("mousemove", mouseMoveHandler);
};

export { setMouseEvent };
