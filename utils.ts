// const tag = (el,props)=>{
//   if(el instanceof HTMLElement){el[]}
// }

export const delay = (time:number) =>
  new Promise((res) => setTimeout(res, time));
export type StateObj = Record<string|symbol, any>;
