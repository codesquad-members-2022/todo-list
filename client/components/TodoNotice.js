export default class TodoNotice {
  constructor(notice) {
    this.title = notice.title;
    this.firstStatus = notice.firstStatus;
    this.status = notice.status;
    this.time = notice.time;
    this.user = notice.userId;
    this.action = notice.action;
  }

  render = () => {
    return /*html*/ `
        <div class="alarm__inner">
            <div class="alarm__icon">😀</div>
            <div class="alarm__contents">
                <p class="alarm__writer">${this.user}</p>
                <p class="alarm__content">
                ${
                  !this.firstStatus
                    ? `<strong>${this.status}</strong>에 <strong>${this.title}</strong>를 ${this.action}하였습니다.`
                    : `<strong>${this.title}</strong>를 <strong>${this.firstStatus}</strong>에서 <strong>${this.status}</strong>로 ${this.action}하였습니다.`
                }
                </p>
                <p class="alarm__time">${this.time}전</p>
            </div>
        </div>
    `;
  };
}
