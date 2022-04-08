import { ScheduleColumn } from "./components/schedule/scheduleColumn.js";

const $main = document.querySelector("#main");

const columns = [
    {
        title: "해야할 일",
        cards: [
            {
                title: "제목",
                body: "내용",
                caption: "author by web",
                id: "0",
            },
        ],
    },
    {
        title: "하고 있는 일",
        cards: [
            {
                title: "제목",
                body: "내용",
                caption: "author by web",
                id: "0",
            },
        ],
    },
];
columns.forEach((scheduleColumn) => {
    new ScheduleColumn($main, scheduleColumn);
});
