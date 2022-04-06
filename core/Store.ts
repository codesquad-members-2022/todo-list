export class Store {
  private _state;
  protected state;
  constructor(state) {
    this._state = state;
  }
  observer(state) {}
}
