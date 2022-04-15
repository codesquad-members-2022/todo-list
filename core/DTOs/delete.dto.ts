import {Action} from "../../types";

export class DeleteDto {
    constructor(
        public readonly listIdx: number, public readonly idx: number) {
    }
}