import {Action} from "../../types";
import {Dto} from "./dto";

export class UpdateDto implements Dto<Action.UPDATE> {
    constructor(public readonly payload: {}) {
    }
}