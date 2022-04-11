const headerTemplate = () => {
  return `
  <header class="header">
    <h1 class="header_title">TO-DO LIST</h1>
    <button class="menu_btn">
      <img src="./src/img/menu_icon.png" alt="활동기록 메뉴" />
    </button>
  </header>
  `;
};

const historyTemplate = () => {
  return `
  <article class="history_container">
    <ul class="history_list">
      <li class="history_card">
        <div
          class="user_img"
          style="background-image: url(./src/img/user.svg)"
        ></div>
        <div class="history_text">
          <span class="user_id">@이든</span>
          <div class="history_content">
            <strong>해야할 일</strong>에 <strong>TIL작성하기</strong>를
            <strong>등록</strong>하였습니다.
          </div>
          <span class="history_time">1분전</span>
        </div>
      </li>
      <li class="history_card">
        <div
          class="user_img"
          style="background-image: url(./src/img/user.svg)"
        ></div>
        <div class="history_text">
          <span class="user_id">@J</span>
          <div class="history_content">
            <strong>해야할 일</strong>에 <strong>HTML/CSS 작성</strong>를
            <strong>등록</strong>하였습니다.
          </div>
          <span class="history_time">1분전</span>
        </div>
      </li>
    </ul>
    <button class="close_btn">
      <img src="./src/img/close.png" alt="close icon" />
    </button>
  </article>
  `;
};

const alertBoxTemplate = ({ title, cancel, accept }) => {
  return `
  <div class="alert_box">
      <p class="alert_message">${title}</p>
      <div class="alert_btn_box">
          <button type="button" class="btn normal_btn">${cancel}</button>
          <button type="button" class="btn accent_btn">${accept}</button>
      </div>
  </div>
  `;
};

export { headerTemplate, historyTemplate, alertBoxTemplate };
