import {Action, StateObj} from "../../types";


export class AddDto {


    constructor(public readonly listIdx: number,
                public readonly title: string,
                public readonly content: string) {

    }
}

