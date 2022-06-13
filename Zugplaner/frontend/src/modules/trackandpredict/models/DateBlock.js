import { format } from 'date-fns';
import _ from 'lodash';
import PropTypes from 'prop-types';
import Block from './Block';

export default class DateBlock extends Block {
  static get shape() {
    return PropTypes.shape({
      name: PropTypes.string,
      required: PropTypes.bool,
      date: PropTypes.instanceOf(Date),
    });
  }

  constructor(name, required, date) {
    if (typeof date === 'string') {
      throw new Error('DateBlock can only work with Date object');
    }
    super(name);
    this.required = required;
    this.date = date;
  }

  isBlockValid() {
    return !this.required || !_.isNil(this.date);
  }

  toBody() {
    return this.date
      ? ({
        [this.name]: format(this.date, 'yyyy-MM-dd'),
      })
      : {};
  }

  withDate(date) {
    if (typeof date === 'string') {
      throw new Error('DateBlock can only work with Date object');
    }
    return new DateBlock(this.name, this.required, date);
  }
}
