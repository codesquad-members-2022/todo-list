import Columns from "./Column/Columns";
import styles from "./main.module.css";

const Main = ({ columns, todos }) => {
  return `
        <div class="${styles.content}">
            ${Columns({ columns, todos })}
        </div>
    `;
};

export default Main;
