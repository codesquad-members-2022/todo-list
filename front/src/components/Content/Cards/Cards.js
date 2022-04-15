import { getISODateDiff } from "common/dateUtils";
import Card from "components/Content/Card/Card";
import cardStyles from "components/Content/Card/card.module.css";
import CardWritable from "components/Content/CardWritable/CardWritable";
import peact from "core/peact";
import todoApi from "service/todoApi";

import styles from "./cards.module.css";

const getSortedDatabyLatest = (data) => {
  return data.sort((a, b) => getISODateDiff(b.updatedAt, a.updatedAt));
};

const Cards = ({ $newCard, todos, handlers }) => {
  const { handleRenderFlag } = handlers;

  const cardsRef = peact.useRef();

  const createCard = (todo) => {
    const cardRef = peact.useRef();
    const cardWritableRef = peact.useRef();

    const toggleCardVisible = () => {
      cardsRef.current.removeChild(cardWritableRef.current);
      cardRef.current.classList.toggle(cardStyles.hide);
    };

    const handleSubmitForm = async (event) => {
      event.preventDefault();
      const { title, desc, author } = event.target;
      const requestBody = {
        title: title.value,
        desc: desc.value,
        author: author.value,
      };
      await todoApi.updateTodo(todo._id, requestBody);
      cardsRef.current.removeChild(cardWritableRef.current);
      handleRenderFlag();
    };

    const handleDoubleClickCard = () => {
      const $card = cardRef.current;
      const $title = $card.querySelector(`.${cardStyles.title}`);
      const $desc = $card.querySelector(`.${cardStyles.cardContent}`);
      const $author = $card.querySelector(`.${cardStyles.author}`);

      const inputValues = {
        title: $title.textContent,
        desc: $desc.textContent,
        author: $author.textContent,
      };

      const $cardWritable = CardWritable({
        toggleCardVisible,
        handleSubmitForm,
        inputValues,
        isVisible: true,
        ref: cardWritableRef,
      });
      cardsRef.current.insertBefore($cardWritable, $card);
      $card.classList.toggle(cardStyles.hide);
    };

    return Card({
      todo,
      ref: cardRef,
      handlers: { ...handlers, handleDoubleClickCard },
    });
  };

  const todoElements = getSortedDatabyLatest(todos).map(createCard);

  return peact.createElement({
    tag: "div",
    className: styles.cards,
    child: [$newCard, ...todoElements],
    ref: cardsRef,
  });
};

export default Cards;
