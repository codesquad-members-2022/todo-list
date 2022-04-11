import Cards from "./Cards/Cards";
import styles from "./column.module.css";
import Header from "./Header/Header";

const Column = () => {
  return `
    <div class="${styles.column}">
        ${Header()}
        ${Cards()}
    </div>
    `;
};

export default Column;
