import Cards from "components/Content/Cards/Cards";
import CardWritable from "components/Content/CardWritable/CardWritable";
import ColumnHeader from "components/Content/ColumnHeader/ColumnHeader";
import peact from "core/peact";
import todoApi from "service/todoApi";

import styles from "./columns.module.css";

const Columns = ({ columns, todos, handlers }) => {
  const getTodosByColumnId = (columnId) => {
    return todos.filter((todo) => todo.columnId === columnId);
  };

  const createColumnHeaderElement = ({ column, toggleCardVisible }) =>
    ColumnHeader({
      column,
      todos: getTodosByColumnId(column._id),
      toggleCardVisible,
    });

  const createCardsElement = ({ $newCard, column }) =>
    Cards({
      $newCard,
      todos: getTodosByColumnId(column._id),
      handlers,
    });

  const handleSubmitForm = async (event) => {
    event.preventDefault();
    const {
      title,
      desc,
      author,
      dataset: { columnId },
    } = event.target;

    const requestBody = {
      title: title.value,
      desc: desc.value,
      author: author.value,
      columnId,
    };

    await todoApi.createTodo(requestBody);
    handlers.handleRenderFlag();
  };

  const createColumnElement = (column) => {
    const newCardRef = peact.useRef();
    const toggleCardVisible = () => {
      newCardRef.current.classList.toggle(styles.visible);
    };

    const $newCard = CardWritable({
      toggleCardVisible,
      handleSubmitForm,
      columnId: column._id,
      isVisible: false,
      ref: newCardRef,
    });

    return peact.createElement({
      tag: "div",
      className: styles.column,
      child: [
        createColumnHeaderElement({ column, toggleCardVisible }),
        createCardsElement({ $newCard, column }),
      ],
    });
  };

  return peact.createElement({
    tag: "div",
    className: styles.content,
    child: columns.map(createColumnElement),
  });
};

export default Columns;
