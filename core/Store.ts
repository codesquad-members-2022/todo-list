
export class Store {
<<<<<<< HEAD
    private _state;
    protected state;
    constructor(state) {
        this._state = state;
    }
    observer(state){
        const handler= {
            get:(target, name)=>{
                const prop = target[name];
                if(!prop)return undefined;

            }
        }
    }
}
=======
  private _state;
  protected state;
  constructor(state) {
    this._state = state;
  }
  observer(state) {
  }
}
>>>>>>> f6aa78c (FEAT: mockdata 렌더링)
