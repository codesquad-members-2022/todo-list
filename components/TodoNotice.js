export default class TodoNotice {
  constructor(notice) {
    this.parentTarget = document.querySelector('.alarm');
    this.title = notice.title;
    this.status = notice.status;
    this.moveStatus = notice.moveStatus;
    this.time = notice.time;
    this.user = notice.userId;
    this.action = notice.action;
    // this.setNotices(notice);
  }

  setNotices = notices => {
    if (notices) {
      // localStorage
    }
  };

  render = () => {
    const alarmHTML = /*html*/ `
        <div class="alarm__inner">
            <div class="alarm__icon">😀</div>
            <div class="alarm__contents">
                <p class="alarm__writer">${this.user}</p>
                <p class="alarm__content">
                <strong>${this.title}</strong>를 <strong>${this.content}</strong>에서 ${this.afterStatus}로
                <strong>${this.action}</strong>하였습니다.
                </p>
                <p class="alarm__time">${this.time}전</p>
            </div>
        </div>
    `;
    this.parentTarget.insertAdjacentHTML('afterbegin', alarmHTML);
    return alarmHTML;
  };
}
