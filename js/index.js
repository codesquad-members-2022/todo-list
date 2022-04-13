import { ScheduleColumn } from "./components/schedule/scheduleColumn.js";
import { mouseDownEventHandler } from "./components/schedule/scheduleCardDrag.js";
import { scheduleModel } from "./components/model/scheduleModel.js";
import { scheduleEditCardInit } from "./components/schedule/scheduleEditCard.js";

const $main = document.querySelector("#main");
const scheduleColumns = [];

scheduleModel.forEach((scheduleColumnData) => {
    const scheduleColumn = new ScheduleColumn(
        $main,
        scheduleColumnData.columnId
    );
    scheduleColumns.push(scheduleColumn);
});

$main.addEventListener("mousedown", mouseDownEventHandler);
$main.addEventListener("dblclick", scheduleEditCardInit);
