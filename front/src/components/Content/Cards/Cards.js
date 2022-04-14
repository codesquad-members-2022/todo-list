import peact from "../../../core/peact";
import todoApi from "../../../service/todoApi";
import Card from "../Card/Card";
import cardStyles from "../Card/card.module.css";
import CardWritable from "../CardWritable/CardWritable";
import styles from "./cards.module.css";

const Cards = ({ $newCard, todos, handleRenderFlag }) => {
  const cardsRef = peact.useRef();
  const setTodosSortByLatest = (a, b) =>
    new Date(b.updatedAt) - new Date(a.updatedAt);

  const getCard = (todo) => {
    const cardRef = peact.useRef();
    const cardWritableRef = peact.useRef();

    const handleNewCardVisibility = () => {
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
        handleNewCardVisibility,
        handleSubmitForm,
        inputValues,
        ref: cardWritableRef,
      });
      // TODO: CardWritable 만들 때 display 속성 props 로 넘겨서 만드는게 좋을 듯
      $cardWritable.style.display = "flex";
      //
      cardsRef.current.insertBefore($cardWritable, $card);
      $card.classList.toggle(cardStyles.hide);
    };

    return Card({
      todo,
      handleRenderFlag,
      handleDoubleClickCard,
      ref: cardRef,
    });
  };

  const todoElements = todos.sort(setTodosSortByLatest).map(getCard);

  return peact.createElement({
    tag: "div",
    className: styles.cards,
    child: [$newCard, ...todoElements],
    ref: cardsRef,
  });
};

export default Cards;
