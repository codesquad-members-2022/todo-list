// const tag = (el,props)=>{
//   if(el instanceof HTMLElement){el[]}
// }


import { StateObj } from "./types";
const url = "http://127.0.0.1:3000";
export const load= async():Promise<StateObj>=> {

  try {
    return await (await fetch(url,{headers : {
      'Content-Type': 'application/json',
        'Accept': 'application/json'
    }})).json()
  }catch(e){throw e;}
}

export const delay = (time:number) =>
  new Promise((res) => setTimeout(res, time));
