import PropTypes from 'prop-types';
import Block from './Block';

export default class ChecklistYesNoBlock extends Block {
  static get shape() {
    return PropTypes.shape({
      name: PropTypes.string,
      required: PropTypes.bool,
      status: PropTypes.string,
      hasComment: PropTypes.bool,
      comment: PropTypes.string,
      hasLiter: PropTypes.bool,
      liter: PropTypes.string,
    });
  }

  constructor(name, required, status, hasComment, comment, hasLiter, liter) {
    super(name);
    this.required = required;
    this.status = status;
    this.hasComment = hasComment;
    this.comment = comment;
    this.hasLiter = hasLiter;
    this.liter = liter;
  }

  isBlockValid() {
    return !this.required || this.status === 'yes'
    || (this.status === 'no'
    && (this.hasComment ? this.comment !== '' && this.comment !== undefined : !this.hasComment)
    && (this.hasLiter ? this.liter !== '' && this.liter !== undefined : !this.hasLiter));
  }

  getOptions() {
    const result = [
      {
        as: 'radiobutton',
        label: `maintenance.form.${this.name}.status.label`,
        name: this.name,
        required: this.required,
        yesChecked: this.status === 'yes',
        noChecked: this.status === 'no',
      },
      {
        as: 'textarea',
        label: 'maintenance.form.comment.label',
        name: `comment_${this.name}`,
        required: this.required,
        value: this.comment,
      },
    ];
    if (this.hasLiter) {
      result.push({
        as: 'input',
        label: `maintenance.form.${this.name}.liter.label`,
        name: `liter_${this.name}`,
        required: this.required,
        value: this.liter,
      });
    }
    return result;
  }

  toBody() {
    return {
      [this.name]: this.status,
      [`comment_${this.name}`]: this.comment,
      [`liter_${this.name}`]: this.liter,
    };
  }

  withStatus(value) {
    return new ChecklistYesNoBlock(
      this.name,
      this.required,
      value,
      this.hasComment,
      this.comment,
      this.hasLiter,
      this.liter,
    );
  }

  withComment(value) {
    return new ChecklistYesNoBlock(
      this.name,
      this.required,
      this.status,
      this.hasComment,
      value,
      this.hasLiter,
      this.liter,
    );
  }

  withLiter(value) {
    return new ChecklistYesNoBlock(
      this.name,
      this.required,
      this.status,
      this.hasComment,
      this.comment,
      this.hasLiter,
      value,
    );
  }

  toString() {
    return `name = ${this.name}, required = ${this.required}, 
    status = ${this.status}, hasComment = ${this.hasComment}, 
    comment = ${this.comment}, hasLiter = ${this.hasLiter}, 
    liter= ${this.liter}`;
  }
}
