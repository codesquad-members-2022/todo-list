/*
 * ATTENTION: The "eval" devtool has been used (maybe by default in mode: "development").
 * This devtool is neither made for production nor for readable output files.
 * It uses "eval()" calls to create a separate source file in the browser devtools.
 * If you are trying to read the output file, select a different devtool (https://webpack.js.org/configuration/devtool/)
 * or disable the default devtool with "devtool: false".
 * If you are looking for production-ready output files, see mode: "production" (https://webpack.js.org/configuration/mode/).
 */
/******/ (() => { // webpackBootstrap
/******/ 	"use strict";
/******/ 	var __webpack_modules__ = ({

/***/ "../client/src/js/app.js":
/*!*******************************!*\
  !*** ../client/src/js/app.js ***!
  \*******************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

eval("__webpack_require__.r(__webpack_exports__);\n/* harmony import */ var _utils_utils_js__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./utils/utils.js */ \"../client/src/js/utils/utils.js\");\n/* harmony import */ var _header_view_js__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./header/view.js */ \"../client/src/js/header/view.js\");\n/* harmony import */ var _sidebar_view_js__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./sidebar/view.js */ \"../client/src/js/sidebar/view.js\");\n\n\n\nconst header = (0,_utils_utils_js__WEBPACK_IMPORTED_MODULE_0__.$)(\"header\");\nconst aside = (0,_utils_utils_js__WEBPACK_IMPORTED_MODULE_0__.$)(\"aside\");\n\nconst init = () => {\n  (0,_header_view_js__WEBPACK_IMPORTED_MODULE_1__.headInit)(header);\n  (0,_sidebar_view_js__WEBPACK_IMPORTED_MODULE_2__.sidebarInit)(aside);\n};\n\ninit();\n\n//# sourceURL=webpack://builder/../client/src/js/app.js?");

/***/ }),

/***/ "../client/src/js/header/view.js":
/*!***************************************!*\
  !*** ../client/src/js/header/view.js ***!
  \***************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

eval("__webpack_require__.r(__webpack_exports__);\n/* harmony export */ __webpack_require__.d(__webpack_exports__, {\n/* harmony export */   \"headInit\": () => (/* binding */ headInit)\n/* harmony export */ });\n/* harmony import */ var _utils_utils_js__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ../utils/utils.js */ \"../client/src/js/utils/utils.js\");\n/* harmony import */ var _store_model_js__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ../store/model.js */ \"../client/src/js/store/model.js\");\n\n\n\nconst createHTML = () => {\n  return `<h1 class=\"header__title\">TO-DO LIST</h1>\n      <button class=\"sidebar__menu-button\">\n        <img src=\"./svg/icon-menu.svg\" alt=\"icon-delete\" />\n      </button>`;\n};\n\nconst render = parent => {\n  parent.innerHTML = createHTML();\n};\n\nconst handleSideBarMenuBtn = () => {\n  (0,_store_model_js__WEBPACK_IMPORTED_MODULE_1__.toggleActivation)().set();\n};\n\nconst setEvents = () => {\n  const sidebarMenuBtn = (0,_utils_utils_js__WEBPACK_IMPORTED_MODULE_0__.$)(\".sidebar__menu-button\");\n  sidebarMenuBtn.addEventListener(\"click\", handleSideBarMenuBtn);\n};\n\nconst headInit = parent => {\n  render(parent);\n  setEvents();\n};\n\n//# sourceURL=webpack://builder/../client/src/js/header/view.js?");

/***/ }),

/***/ "../client/src/js/sidebar/view.js":
/*!****************************************!*\
  !*** ../client/src/js/sidebar/view.js ***!
  \****************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

eval("__webpack_require__.r(__webpack_exports__);\n/* harmony export */ __webpack_require__.d(__webpack_exports__, {\n/* harmony export */   \"sidebarInit\": () => (/* binding */ sidebarInit)\n/* harmony export */ });\n/* harmony import */ var _utils_utils_js__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ../utils/utils.js */ \"../client/src/js/utils/utils.js\");\n/* harmony import */ var _store_model_js__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ../store/model.js */ \"../client/src/js/store/model.js\");\n/* harmony import */ var _server_data_sidebar_js__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../../../../server/data/sidebar.js */ \"../server/data/sidebar.js\");\n\n\n\n\nconst getFinalConsonant = object => {\n  const finalWord = object.length - 1;\n  const fianlCharCode = object.charCodeAt(finalWord);\n  const finalConsonantCode = (fianlCharCode - 44032) % 28;\n  return finalConsonantCode;\n};\n\nconst hasFinalConsonant = object => {\n  const finalConsonantCode = getFinalConsonant(object);\n  return finalConsonantCode !== 0;\n};\n\nconst appendEulReul = object => {\n  return hasFinalConsonant(object) ? \"을\" : \"를\";\n};\n\nconst appenRoEro = object => {\n  const rieul = 8;\n  return hasFinalConsonant(object) //\n  ? getFinalConsonant(object) === rieul ? \"로\" : \"으로\" : \"로\";\n};\n\nconst calcTimeForToday = writeTimeValue => {\n  const today = new Date();\n  const writeTime = new Date(writeTimeValue);\n  const timeDifference = today.getTime() - writeTime.getTime();\n  const minuteDifference = Math.floor(timeDifference / 1000 / 60);\n  if (minuteDifference < 1) return \"방금전\";else if (minuteDifference < 60) return `${minuteDifference}분전`;\n  const HourDifference = Math.floor(minuteDifference / 60);\n  if (HourDifference < 24) return `${HourDifference}시간전`;\n  const DayDifference = Math.floor(HourDifference / 60 / 24);\n  if (DayDifference < 365) return `${DayDifference}일전`;\n};\n\nconst identifyCategory = activity => {\n  switch (activity.action) {\n    case \"등록\":\n      return `${activity.category[0]}에 ${activity.title}${appendEulReul(activity.title)} \n      ${activity.action}하였습니다.`;\n\n    case \"이동\":\n      return `${activity.title}${appendEulReul(activity.title)} ${activity.category[0]}에서 \n      ${activity.category[1]}${appenRoEro(activity.category[1])} ${activity.action}하였습니다.`;\n\n    case \"삭제\":\n      return `${activity.category}에서 ${activity.title}${appendEulReul(activity.title)} \n      ${activity.action}하였습니다.`;\n\n    case \"변경\":\n      return `${activity.category}에서 ${activity.title}이 ${action}되었습니다.`;\n\n    default:\n      return \"동작이 잘못 되었습니다.\";\n  }\n};\n\nconst createHTML = () => {\n  const sidebarList = _server_data_sidebar_js__WEBPACK_IMPORTED_MODULE_2__.sidebarData.reduce((acc, cur) => acc + `<li class=\"sidebar__item\">\n          <img class=\"sidebar__item--profile\" src=\"./svg/profile.svg\" alt=\"profile\" />\n          <section>\n            <h3 class=\"sidebar__item--nickname\">@sam</h3>\n            <p class=\"sidebar__item--activity\">\n               ${identifyCategory(cur)}\n            </p>\n            <p class=\"sidebar__item--time-stamp\">${calcTimeForToday(cur.writeTime)}</p>\n          </section>\n        </li>`, \"\");\n  return `<ul class=\"sidebar__list\">${sidebarList}</ul>`;\n};\n\nconst render = parent => {\n  parent.innerHTML = createHTML();\n};\n\nconst toggleSidebar = () => {\n  const sidebar = (0,_utils_utils_js__WEBPACK_IMPORTED_MODULE_0__.$)(\"aside\");\n  const sidebarMenuBtn = (0,_utils_utils_js__WEBPACK_IMPORTED_MODULE_0__.$)(\".sidebar__menu-button img\");\n  sidebar.classList.toggle(\"show\");\n  sidebarMenuBtn.src = sidebar.classList.contains(\"show\") ? \"./svg/icon-delete.svg\" : \"./svg/icon-menu.svg\";\n};\n\nconst bindModel = () => {\n  _store_model_js__WEBPACK_IMPORTED_MODULE_1__.toggleActivation.toggleSidebar = toggleSidebar;\n};\n\nconst sidebarInit = parent => {\n  render(parent);\n  bindModel();\n};\n\n//# sourceURL=webpack://builder/../client/src/js/sidebar/view.js?");

/***/ }),

/***/ "../client/src/js/store/model.js":
/*!***************************************!*\
  !*** ../client/src/js/store/model.js ***!
  \***************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

eval("__webpack_require__.r(__webpack_exports__);\n/* harmony export */ __webpack_require__.d(__webpack_exports__, {\n/* harmony export */   \"toggleActivation\": () => (/* binding */ toggleActivation)\n/* harmony export */ });\nconst toggleActivation = () => {\n  let activation = false;\n  return {\n    get: () => {\n      return activation;\n    },\n    set: () => {\n      activation = !activation;\n      toggleActivation.toggleSidebar();\n    }\n  };\n};\n\n//# sourceURL=webpack://builder/../client/src/js/store/model.js?");

/***/ }),

/***/ "../client/src/js/utils/utils.js":
/*!***************************************!*\
  !*** ../client/src/js/utils/utils.js ***!
  \***************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

eval("__webpack_require__.r(__webpack_exports__);\n/* harmony export */ __webpack_require__.d(__webpack_exports__, {\n/* harmony export */   \"$\": () => (/* binding */ $)\n/* harmony export */ });\nconst $ = selector => document.querySelector(selector);\n\n//# sourceURL=webpack://builder/../client/src/js/utils/utils.js?");

/***/ }),

/***/ "../server/data/sidebar.js":
/*!*********************************!*\
  !*** ../server/data/sidebar.js ***!
  \*********************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

eval("__webpack_require__.r(__webpack_exports__);\n/* harmony export */ __webpack_require__.d(__webpack_exports__, {\n/* harmony export */   \"sidebarData\": () => (/* binding */ sidebarData)\n/* harmony export */ });\nconst sidebarData = [{\n  action: \"등록\",\n  category: [\"해야할 일\"],\n  title: \"GitHub 공부하기\",\n  writeTime: \"Wed Apr 06 2022 12:16:00 GMT+0900\"\n}, {\n  action: \"등록\",\n  category: [\"해야할 일\"],\n  title: \"블로그에 포스팅할 것\",\n  writeTime: \"Wed Apr 06 2022 12:16:00 GMT+0900\"\n}, {\n  action: \"등록\",\n  category: [\"해야할 일\"],\n  title: \"HTML/CSS공부하기\",\n  writeTime: \"Wed Apr 06 2022 12:16:00 GMT+0900\"\n}, {\n  action: \"이동\",\n  category: [\"해야할 일\", \"하고 있는 일\"],\n  title: \"HTML/CSS공부하기\",\n  writeTime: \"Wed Apr 06 2022 12:16:00 GMT+0900\"\n}];\n\n//# sourceURL=webpack://builder/../server/data/sidebar.js?");

/***/ })

/******/ 	});
/************************************************************************/
/******/ 	// The module cache
/******/ 	var __webpack_module_cache__ = {};
/******/ 	
/******/ 	// The require function
/******/ 	function __webpack_require__(moduleId) {
/******/ 		// Check if module is in cache
/******/ 		var cachedModule = __webpack_module_cache__[moduleId];
/******/ 		if (cachedModule !== undefined) {
/******/ 			return cachedModule.exports;
/******/ 		}
/******/ 		// Create a new module (and put it into the cache)
/******/ 		var module = __webpack_module_cache__[moduleId] = {
/******/ 			// no module.id needed
/******/ 			// no module.loaded needed
/******/ 			exports: {}
/******/ 		};
/******/ 	
/******/ 		// Execute the module function
/******/ 		__webpack_modules__[moduleId](module, module.exports, __webpack_require__);
/******/ 	
/******/ 		// Return the exports of the module
/******/ 		return module.exports;
/******/ 	}
/******/ 	
/************************************************************************/
/******/ 	/* webpack/runtime/define property getters */
/******/ 	(() => {
/******/ 		// define getter functions for harmony exports
/******/ 		__webpack_require__.d = (exports, definition) => {
/******/ 			for(var key in definition) {
/******/ 				if(__webpack_require__.o(definition, key) && !__webpack_require__.o(exports, key)) {
/******/ 					Object.defineProperty(exports, key, { enumerable: true, get: definition[key] });
/******/ 				}
/******/ 			}
/******/ 		};
/******/ 	})();
/******/ 	
/******/ 	/* webpack/runtime/hasOwnProperty shorthand */
/******/ 	(() => {
/******/ 		__webpack_require__.o = (obj, prop) => (Object.prototype.hasOwnProperty.call(obj, prop))
/******/ 	})();
/******/ 	
/******/ 	/* webpack/runtime/make namespace object */
/******/ 	(() => {
/******/ 		// define __esModule on exports
/******/ 		__webpack_require__.r = (exports) => {
/******/ 			if(typeof Symbol !== 'undefined' && Symbol.toStringTag) {
/******/ 				Object.defineProperty(exports, Symbol.toStringTag, { value: 'Module' });
/******/ 			}
/******/ 			Object.defineProperty(exports, '__esModule', { value: true });
/******/ 		};
/******/ 	})();
/******/ 	
/************************************************************************/
/******/ 	
/******/ 	// startup
/******/ 	// Load entry module and return exports
/******/ 	// This entry module can't be inlined because the eval devtool is used.
/******/ 	var __webpack_exports__ = __webpack_require__("../client/src/js/app.js");
/******/ 	
/******/ })()
;