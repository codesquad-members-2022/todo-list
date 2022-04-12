import peact from "../../../core/peact";
import Cards from "../Cards/Cards";
import ColumnHeader from "../ColumnHeader/ColumnHeader";
import styles from "./columns.module.css";

const Columns = ({ columns, todos }) => {
  const getTodosByColumnId = (columnId) => {
    return todos.filter((todo) => todo.columnId === columnId);
  };

  const getColumnTemplate = (column) => {
    return peact.createElement({
      tag: "div",
      className: styles.column,
      child: [
        ColumnHeader({ column, todos: getTodosByColumnId(column._id) }),
        Cards({ todos: getTodosByColumnId(column._id) }),
      ],
    });
  };

  return peact.createElement({
    tag: "div",
    className: styles.content,
    child: columns.map(getColumnTemplate),
  });
};

export default Columns;
