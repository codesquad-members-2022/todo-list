import { Task } from "./task.js";
let timer;

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
  const [header, comment, author] = [...sectionElement.children];
  const [title] = [...header.children];
  const listTitle = taskElement.closest(".column__item").dataset.title;
  const taskData = {
    id: taskElement.dataset.id,
    title: title.value,
    comment: comment.value,
    author: author.value,
  };

  const copyTask = new Task(listTitle, taskData); //wow....

  let copyTaskElement = document.createElement("div");
  copyTaskElement.innerHTML = copyTask.createHTML();
  copyTaskElement = copyTaskElement.querySelector("li");
  copyTaskElement.style.width = `${taskElement.clientWidth}px`;
  copyTaskElement.ondragstart = () => false;
  copyTask.target = copyTaskElement;
  copyTask.setEvents();

  copyTaskElement.addEventListener("mouseup", mouseUpHandler);

  if (!isMoved) {
    copyTaskElement.classList.add("dragging");
  } else {
    copyTaskElement.classList.add("blur");
  }
  return copyTaskElement;
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

  const { shiftX, shiftY } = getShiftCoordinate(event, copyElement);
  const centerX = event.pageX - shiftX + copyElement.offsetWidth / 2;
  const centerY = event.pageY - shiftY + copyElement.offsetHeight / 2;
  const [belowElement, subBelowElement] = getElementByCoordinate(copyElement, { x: centerX, y: centerY });
  if (belowElement === null) {
    copyElement.dispatchEvent(new Event("mouseup"));
    return;
  }

  const standardCard = getStandardCard([belowElement, subBelowElement], event.pageY);
  const movingTaskList = belowElement.closest(`.${taskListClassName}`);
  movingTaskList && setTaskList(movingTaskList, standardCard);
};

const handleDragCard = () => {
  let standardCard = null;
  let prevY = null;
  let movingDirection = null;
  let movedTaskList = null;

  return ([belowElement, subBelowElement], curY) => {
    const movingTaskList = belowElement.closest(`.${taskListClassName}`);
    const isNewList = movedTaskList !== movingTaskList;
    if (prevY !== curY) movingDirection = prevY > curY ? "up" : "down";
    if (isTask(belowElement)) {
      standardCard =
        movingDirection === "up"
          ? isTask(belowElement)
          : isNewList
          ? isTask(belowElement)
          : isTask(belowElement).nextSibling;
    } else if (isTask(subBelowElement) === null) {
      standardCard = null;
    }

    movedTaskList = movingTaskList;
    prevY = curY;
    return standardCard;
  };
};

const getStandardCard = handleDragCard();

const isTask = (element) => element.closest(".column__task--item");

const getElementByCoordinate = (element, { x, y }) => {
  element.classList.add("hidden");
  const belowElement = document.elementFromPoint(x, y);
  const marginBottom = 16;
  const subBelowElement = document.elementFromPoint(x, y + marginBottom);
  element.classList.remove("hidden");
  return [belowElement, subBelowElement];
};

const setTaskList = (movingList, standardCard) => {
  const taskObject = document.getTaskObject();
  document.getTaskObject("origin").style.display = "none";

  const movingCard = taskObject["moved"];
  movingList.insertBefore(movingCard, standardCard || null);
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
