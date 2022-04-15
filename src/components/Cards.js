import { postCard, postHistories, deleteData } from "../api.js";
import Component from "../core/Component.js";
import myAlert from "./Alert.js";

export default class Cards extends Component {
  template() {
    const { cards } = this.$props;
    return `
    <ul>
      ${cards
        ?.map(
          ({ cardState, title, content, id }) =>
            `<li>
              <div class="card" data-card-state="${cardState}" data-card-id=${id}>
                <div class="card-title">
                  ${cardState === "create" ? `<input class="card-title-input" placeholder="제목을 입력하세요">` : title}
                </div>
                <div class="card-content">
                  ${cardState === "create" ? `<input class="card-content-input" placeholder="내용을 입력하세요"></textarea>` : content}
                </div>
                ${
                  cardState === "create" || cardState === "update"
                    ? `
                    <div class="card-button-wrapper">
                      <button class="card-button-normal">취소</button>
                      <button class="card-button-accent" ${cardState === "create" ? "disabled" : ""}>
                        ${cardState === "create" ? `등록` : `수정`}
                      </button> 
                    </div>`
                    : `
                    <div class="card-author">
                      author by web
                    </div>
                    <div class="card-button-delete"></div>
                    `
                }
                
              </div>
            </li>`
        )
        .join("")}
    </ul>
    `;
  }
  setEvent() {
    this.addEvent("click", ".card-button-normal", this.clickNormalButtonHandler.bind(this));
    this.addEvent("input", ".card", this.inputHandler.bind(this));
    this.addEvent("click", ".card-button-accent", this.update.bind(this));
    this.addEvent("mouseover", ".card-button-delete", ({ target }) => {
      this.setCardState(target, "delete");
    });
    this.addEvent("mouseout", ".card-button-delete", ({ target }) => {
      this.setCardState(target, "default");
    });
    this.addEvent("click", ".card-button-delete", this.clickDeleteButtonHandler.bind(this));
  }
  clickNormalButtonHandler() {
    const { cards, undoCreateCard } = this.$props;
    if (cards[0]?.cardState === "create") {
      undoCreateCard();
    }
  }
  clickDeleteButtonHandler({ target }) {
    myAlert(
      {
        message: "선택한 카드를 삭제할까요?",
        confirmText: "삭제",
      },
      async () => {
        const { setCards } = this.$props;
        const cardId = target.closest(".card").dataset.cardId;
        const columnId = target.closest(".column").dataset.index;

        await deleteData(`cards/${cardId}`);
        setCards(columnId);
      }
    );
  }
  inputHandler({ target }) {
    const $inputs = [...this.$target.querySelectorAll("input")];
    const isInputEvery = $inputs.every(($input) => $input.value !== "");
    const $button = this.$target.querySelector(".card-button-accent");

    $button.disabled = !isInputEvery;
  }

  async update({ target }) {
    this.addNewCard(target);
    await this.notifyNewHistory(target);
  }

  async notifyNewHistory(target) {
    const { notifyNewHistoryData } = this.$props;
    const user = "sam";
    const todo = target.closest(".card").querySelector(".card-title-input").value;
    const beforePosition = "";
    const afterPosition = target.closest(".column").dataset.index;
    const action = "등록";
    const newHistory = {
      user,
      todo,
      beforePosition,
      afterPosition,
      action,
    };
    await postHistories(newHistory);
    notifyNewHistoryData();
  }
  setCardState(target, newCardState) {
    const $card = target.closest(".card");
    $card.dataset.cardState = newCardState;
  }

  async addNewCard(target) {
    const { setCards } = this.$props;
    const $clickedCard = target.closest(".card");
    const columnId = target.closest(".column").dataset.index;
    const [title, content] = [".card-title-input", ".card-content-input"].map((selector) => $clickedCard.querySelector(selector).value);
    const newCard = {
      columnId,
      title,
      content,
      author: "나",
    };
    await postCard(newCard);
    setCards(columnId);
  }
}
