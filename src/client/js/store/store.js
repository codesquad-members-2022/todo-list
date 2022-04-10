class Store {

  constructor() {
    this.state = {};
    this.subscribers = {};
  }

  subscribe(state, subscriber) {
    if (!this.subscribers[state]) {
      this.subscribers[state] = [];
      this.observe(state);
    }
    this.subscribers = {
      ...this.subscribers,
      [state]: [...this.subscribers[state], subscriber],
    };
  }

  unsubscribe(state) {
    this.subscribers[state].pop();
  }

  observe(state) {
    let _value = this.state[state];
    Object.defineProperty(this.state, state, {
      get() {
        return _value;
      },
      set: (value) => {
        _value = value;
        this.subscribers[state].forEach(subscriber => {
          subscriber.setState({ [state]: this.state[state] });
        });
      },
    });
  }

  getState(state) {
    return this.state[state];
  }

  setState(state, value) {
    this.state[state] = value;
  }

  clearState(state) {
    if (Array.isArray(this.state[state])) this.state[state] = [];
    else this.state[state] = null;
  }
}

export default Store;
