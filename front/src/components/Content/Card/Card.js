import peact from "../../../core/peact";
import styles from "./card.module.css";

const Card = ({ todo }) => {
  const cardInnerHTML = `
    <form class="${styles.card}">
      <div class="${styles.headerArea}">
        <label class="${styles.title}">${todo.title}</label>
        <div>
          <svg
            width="12"
            height="12"
            viewBox="0 0 12 12"
            fill="none"
            xmlns="http://www.w3.org/2000/svg"
          >
            <path
              d="M1.5 11.25L0.75 10.5L5.25 6L0.75 1.5L1.5 0.75L6 5.25L10.5 0.75L11.25 1.5L6.75 6L11.25 10.5L10.5 11.25L6 6.75L1.5 11.25Z"
              fill="#828282"
            />
          </svg>
        </div>
      </div>
      <div>
        <label class="${styles.cardContent}">${todo.desc}</label>
      </div>
      <div class="${styles.author}">${todo.author}</div>
    </form>
  `;

  return peact.createElement({
    tag: "form",
    className: styles.card,
    child: [cardInnerHTML],
  });
};

export default Card;
