import Column from "./Column/Column";
import styles from "./main.module.css";

const Main = ({ columns, todos }) => {
  console.log(columns);
  return `
        <div class="${styles.content}">
            ${Column({ columns, todos })}
        </div>
    `;
};

export default Main;
