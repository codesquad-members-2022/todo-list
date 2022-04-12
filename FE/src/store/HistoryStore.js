export const historyStore = {
  state : {}, // 상태(데이터)
  subscriber: {}, // 옵저버가 등록된 요소가 키로 저장됨
  setState(key, newState) { // 옵저버가 등록된 요소의 상태(데이터)를 바꾸면서 알려줌
    this.state[key] = newState;
    this.notify(key);
  },
  initState(key) { // 옵저버 등록된 요소의 상태를 초기화(데이터를 넣을 준비)
    this.state[key] = {};
    this.subscriber[key] = [];
  },
  subscribe(key, listener) { // 옵저버를 구독하는 요소들 추가
    this.subscriber[key].push(listener);
  },
  unSubcribe(key, listener) { // 옵저버의 구독취소하는 요소들 제거
    this.subscriber[key].filter(x => x !== listener);
  },
  notify(key) { // 옵저버에 구독된 요소의 리스너들을 전부 실행
    this.subscriber[key].forEach(listener => listener());
  }
}

// initState(histories)
// setState('histories', columns에서 변경이 발생한 것)

// 이벤트 발생하는 곳은 Card 쪽
// 바뀐 데이터 = 서버한테 GET 요청 결과
// historyStore.setState('histories', '바뀐 데이터');

// 컬럼에서 변경이 발생한다. 