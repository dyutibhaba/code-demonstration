export default class Block {
  constructor(name) {
    this.name = name;
  }

  toBody() {
    return `Not implemented ${this.name}`;
  }

  isBlockValid() {
    return `Not implemented ${this.name}`;
  }
}
