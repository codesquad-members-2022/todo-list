import { iconDelete } from "../constants/imagePath.js";
import { $$ } from "../utils/utils.js";

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

export const setTaskDragEvent = () => {
  const taskList = [...$$(`.${taskListClassName}`)];
  taskList.forEach((taskList) => {
    const taskItems = [...taskList.children];
    taskItems.forEach(setMouseEvent);
  });
};

export const setMouseEvent = (taskItem) => {
  taskItem.addEventListener("mousedown", mouseDownHandler);
};

const mouseDownHandler = (event) => {
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
  taskElement.classList.add("blur");
};

const isDeleteButton = (target) => target.classList.contains("column__task--delete-button");

const createMoveAtFunc = (event, originElement, copyElement) => {
  const { shiftX, shiftY } = getShiftCoordinate(event, originElement);
  return (pageX, pageY) => {
    copyElement.style.left = pageX - shiftX + "px";
    copyElement.style.top = pageY - shiftY + "px";
  };
};

const createCopyTask = (taskElement, isMoved = false) => {
  const [sectionElement] = [...taskElement.children];
  const copyTaskElement = document.createElement("li");
  copyTaskElement.style.width = "308px";
  copyTaskElement.ondragstart = () => false;
  copyTaskElement.dataset.index = taskElement.dataset.index;
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

const getShiftCoordinate = (event, taskElement) => {
  const shiftX = event.clientX - taskElement.getBoundingClientRect().left;
  const shiftY = event.clientY - taskElement.getBoundingClientRect().top;
  return { shiftX: shiftX, shiftY: shiftY };
};

const removeDocumentEvent = () => {
  document.removeEventListener("mousemove", mouseMoveHandler);
};

const setDocumentEvent = (moveAtFunc, getTaskObject) => {
  document.moveAt = moveAtFunc;
  document.getTaskObject = getTaskObject;
  document.addEventListener("mousemove", mouseMoveHandler);
};

const mouseMoveHandler = (event) => {
  const target = event.currentTarget;
  const copyElement = target.getTaskObject("copy");
  target.moveAt(event.pageX, event.pageY);
  const element = getElementByCoordinate(copyElement, { x: event.clientX, y: event.clientY });
  if (element === null) {
    copyElement.dispatchEvent(new Event("mouseup"));
    return;
  }

  const newTaskList = element.closest(`.${taskListClassName}`);
  if (newTaskList !== null) {
    setTaskList(newTaskList);
  }
};

const getElementByCoordinate = (element, { x, y }) => {
  element.classList.add("hidden");
  const belowElement = document.elementFromPoint(x, y);
  element.classList.remove("hidden");
  return belowElement;
};

let movedTaskList = null;

const setTaskList = (newList) => {
  const taskObject = document.getTaskObject();
  const originList = taskObject["origin"].closest(`.${taskListClassName}`);
  const isNewList = originList !== newList;

  if (movedTaskList === newList) {
    return;
  }

  if (movedTaskList !== originList && movedTaskList !== null) {
    movedTaskList.removeChild(taskObject["moved"]);
  }

  movedTaskList = newList;

  if (isNewList) {
    movedTaskList.appendChild(taskObject["moved"]);
    return;
  }
};

const mouseUpHandler = (event) => {
  const target = event.target;

  if (isDeleteButton(target)) {
    return;
  }

  const taskElement = target.closest("li");
  removeDocumentEvent();
  taskElement.removeEventListener("mouseup", mouseUpHandler);
  taskElement.remove();

  if (isMoved(document.getTaskObject("origin"), document.getTaskObject("moved"))) {
    document.getTaskObject("origin").remove();
  } else {
    document.getTaskObject("origin").classList.remove("blur");
  }

  movedTaskList = null;
  delete document.getTaskObject;
};

const isMoved = (element, movedElement) => {
  const taskList = element.closest(`.${taskListClassName}`);
  const movedList = movedElement.closest(`.${taskListClassName}`);
  return movedList !== null && taskList !== movedList;
};
