import { ScheduleColumn } from "../js/components/schedule/scheduleColumn.js";
import { mouseDownEventHandler } from "../js/components/schedule/scheduleCardDrag.js";
import { init, getScheduleModel } from "./model/scheduleModel.js";
import {
    applyHistory2Server,
    applyHistory2ServerInterval,
} from "./model/history.js";

(async () => {
    await init();

    const $main = document.querySelector("#main");
    const scheduleModel = getScheduleModel();
    const scheduleColumns = [];

    scheduleModel.forEach((scheduleColumnData) => {
        const scheduleColumn = new ScheduleColumn($main, scheduleColumnData.id);
        scheduleColumns.push(scheduleColumn);
    });

    $main.addEventListener("mousedown", mouseDownEventHandler);
    window.addEventListener("beforeunload", applyHistory2Server);
    applyHistory2ServerInterval();
})();
