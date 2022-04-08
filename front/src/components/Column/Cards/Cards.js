import Card from "../Card/Card";
import CardWritable from "../CardWritable/CardWritable";
import styles from "./cards.module.css";

const Cards = () => {
  return `
  <div class="${styles.cardsArea}">
    ${Card()}
    ${CardWritable()}
  </div>
    `;
};

export default Cards;
