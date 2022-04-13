import * as scheduleModel from "../model/scheduleModel.js";

export const changeCardNumber = (columnId) => {
    const $curColumn = document.querySelector(`[data-id="${columnId}"]`)
    const $countNumber = $curColumn.querySelector(".schedule-column__count-number")
    const curNumber = scheduleModel.getScheduleCardNumberInColumn(columnId)
    $countNumber.innerText = curNumber
}