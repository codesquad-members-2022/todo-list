export default class HistoryCard {
  init() {
    
  }
  
  template(state) {
    return `
      <div class="history__container">
        <div class="history__icon"></div>
        <div class="history__contents">
          <div class="history__author">${state.author}</div>
          <div class="history__desc">${state.desc}</div>
          <div class="history__date">${toLastTime(state.date)}</div>
        </div>
      </div>
    `
  }
  toLastTime(date) {
    // n분전 or n시간전으로 리턴
    return date;
  }
}
