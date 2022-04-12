export default class HistoryCard {
  constructor(state) {
    this.state = state;
  }
  init() {
    
  }
  
  template() {
    return `
      <div class="history__container">
        <div class="history__icon"></div>
        <div class="history__contents">
          <div class="history__author">${this.state.userId}</div>
          <div class="history__desc">${this.state.cardTitle}를 ${this.state.cardStatusBefore}에서 ${this.state.cardStatus}로 이동하였습니다.</div>
          <div class="history__date">${this.toLastTime(this.state.createdAt)}</div>
        </div>
      </div>
    `
  }
  toLastTime(date) {
    const curDate = Date.now();
    const createdDate = Date.parse(date); 
    const diffDate = curDate - createdDate;
    const sec = diffDate / 1000;
    const min = Math.floor(sec / 60);
    const hour = Math.floor(min / 60);
    const day = Math.floor(hour / 24);
    if (min < 60) return `${min}분 전`;
    if (hour < 24) return `${hour}시간 전`;
    return `${day}일 전`;
  }
}
