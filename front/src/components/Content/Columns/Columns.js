import peact from "../../../core/peact";
import todoApi from "../../../service/todoApi";
import Cards from "../Cards/Cards";
import CardWritable from "../CardWritable/CardWritable";
import ColumnHeader from "../ColumnHeader/ColumnHeader";
import styles from "./columns.module.css";

const Columns = ({ columns, todos, handleRenderFlag }) => {
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
      handleRenderFlag,
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
    handleRenderFlag();
  };

  const createColumnElement = (column) => {
    const newCardRef = peact.useRef();
    const toggleCardVisible = () => {
      newCardRef.current.classList.toggle(styles.visible);
    };
    const $newCard = CardWritable({
      toggleCardVisible,
      ref: newCardRef,
      handleSubmitForm,
      columnId: column._id,
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
