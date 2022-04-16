import { SCHEDULE_COUNT_NUM } from "../../utils/styleNames.js";
import * as scheduleModel from "../../model/scheduleModel.js";

export const changeCardNumber = (columnId) => {
    const $curColumn = document.querySelector(`[data-id="${columnId}"]`);
    const $countNumber = $curColumn.querySelector(`.${SCHEDULE_COUNT_NUM}`);
    const curNumber = scheduleModel.getScheduleCardNumberInColumn(columnId);
    $countNumber.innerText = curNumber;
};
