import Component from "../core/Component.js";

export default class Cards extends Component {
  template() {
    const { cards } = this.$props;
    return `
    <ul>
      ${cards
        ?.map(
          ({ cardState, title, content }) =>
            `<li>
              <div class="card" data-card-state="${cardState}">
                <div class="card-title">
                  ${cardState === "create" ? `<input placeholder="제목을 입력하세요">` : title}
                </div>
                <div class="card-content">
                  ${cardState === "create" ? `<input placeholder="내용을 입력하세요"></textarea>` : content}
                </div>
                ${
                  cardState === "create" || cardState === "update"
                    ? `
                    <div class="card-button-wrapper">
                      <button class="card-button-normal">취소</button>
                      <button class="card-button-accent">
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
}
