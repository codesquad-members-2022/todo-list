import {Action} from "../../types";

export class SelectDto {
    constructor(
        public readonly selected: boolean, public readonly idx: number, public readonly listIdx: number) {
    }
}