import peact from "../../../core/peact";
import Card from "../Card/Card";
import styles from "./cards.module.css";

const Cards = ({ todos }) => {
  const setTodosSortByLatest = (a, b) =>
    new Date(b.updatedAt) - new Date(a.updatedAt);

  const getCardsTemplate = (todo) => {
    return peact.createElement({
      tag: "div",
      className: styles.cardsArea,
      child: [Card({ todo })],
    });
  };

  const childElements = todos.sort(setTodosSortByLatest).map(getCardsTemplate);

  return peact.createElement({
    tag: "div",
    child: childElements,
  });
};

export default Cards;
