import styles from "./action.module.css";

const Action = () => {
  return `
    <div class="${styles.action}">
        <div class="${styles.icon}">🥳</div>
        <div class="content">
            <p class="author">@sam</p>
            <p class="content">
                <strong>뭐시기</strong>를 <strong>뿅뿅</strong>에서
                <strong>짠짠</strong>으로 <strong>룰루</strong>하였습니다.
                <strong>짠짠</strong>으로 <strong>룰루</strong>하였습니다.
                <strong>짠짠</strong>으로 <strong>룰루</strong>하였습니다.
            </p>
            <p class="${styles.time}">1분 전</p>
        </div>
    </div>
  `;
};

export default Action;
