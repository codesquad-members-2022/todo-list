import peact from "../../../core/peact";
import Card from "../Card/Card";
import styles from "./cards.module.css";

const Cards = ({ todos }) => {
  const getCardsTemplate = (todo) => {
    return peact.createElement({
      tag: "div",
      className: styles.cardsArea,
      child: [Card({ todo })],
    });
  };
  return peact.createElement({
    tag: "div",
    child: todos.map(getCardsTemplate),
  });
};

export default Cards;
