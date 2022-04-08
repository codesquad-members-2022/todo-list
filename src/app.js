import * as domUtil from "./util/domUtil.js";

function initService() {
  서버에서_데이터_가져와서;
  domUtil.$(".+버튼").addEventListener("click", createCard);
  domUtil.$(".메뉴버튼").addEventListener("click", 메뉴토글함수);
}

const handleModel = createModel(getTodoData());

function createModel(initState) {
  let state = initState;
  return function (newState) {
    const { CRUD } = newState;
    if (CRUD === "create") state.push(newState);
    return state;
  };
}

function createCard() {
  const newCardState = createCardState(handleModel());
  renderCard(this, newCardState);
}

function createRenderFunc(templatingFunc) {
  return function (target, state) {
    const template = templatingFunc(state);
    target.append(template);
  };
}

const renderCard = createRenderFunc(getCardTemplate);

function getCardTemplate() {
  // reuturn 템플릿
}

function createCardState(data) {
  return {
    id: createID(data),
    title: null,
    contents: null,
    taskState: "to-do",
    CRUD: "create",
  };
}

function createID(storage) {
  const states = storage();
  const idValues = states.map((state) => state.id);
  // 여기 부분을 이제 random하게 수를 생성하고 ids가 그 랜덤한 수를 포함할경우 createId를 (storage)를 넣어서 재귀하거 나? 그런식으로 해결하기
  // return 생성된 아이디
}

function getTodoData() {
  //fetch로 서버에서 데이터 받아옴
  //return fetch 된 데이터
}
