import Card from "../Card/Card";
import CardWritable from "../CardWritable/CardWritable";
import styles from "./cards.module.css";

const Cards = ({ todos }) => {
  const getCardsTemplate = (todo) => {
    return `
    <div class="${styles.cardsArea}">
      ${Card({ todo })}
    </div>
      `;
  };

  return todos.map(getCardsTemplate).join("");
};

export default Cards;
