import PropTypes from 'prop-types';
import Block from './Block';

export default class ChecklistFreeBlock extends Block {
  static get shape() {
    return PropTypes.shape({
      name: PropTypes.string,
      required: PropTypes.bool,
      comment: PropTypes.oneOfType([PropTypes.string, PropTypes.number]),
    });
  }

  constructor(name, required, comment) {
    super(name);
    this.required = required;
    this.comment = comment;
  }

  isBlockValid() {
    return !this.required || (this.comment !== '' && this.comment !== undefined);
  }

  toBody() {
    return {
      [this.name]: this.comment,
    };
  }

  withComment(value) {
    return new ChecklistFreeBlock(
      this.name,
      this.required,
      value,
    );
  }
}
