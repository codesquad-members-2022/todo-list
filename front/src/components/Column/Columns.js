import Cards from "./Cards/Cards";
import styles from "./columns.module.css";
import Header from "./Header/Header";

const Columns = ({ columns, todos }) => {
  const getTodosByColumnId = (columnId) => {
    return todos.filter((todo) => todo.columnId === columnId);
  };

  const getColumnTemplate = (column) => {
    return `<div class="${styles.column}">
      ${Header({ column, todos: getTodosByColumnId(column._id) })}
      ${Cards({ todos: getTodosByColumnId(column._id) })}
  </div>`;
  };

  return columns.map(getColumnTemplate).join("");
};

export default Columns;
