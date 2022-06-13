import { parse } from 'date-fns';
import PropTypes from 'prop-types';
import ChecklistFreeBlock from './ChecklistFreeBlock';
import DateBlock from './DateBlock';

export default class EndOfShiftBody {
  static get shape() {
    return PropTypes.shape({
      operatingDay: DateBlock.shape,
      numberOfKilometers: ChecklistFreeBlock.shape,
    });
  }

  static empty() {
    return EndOfShiftBody.fromBlob({});
  }

  constructor(operatingDay, numberOfKilometers) {
    this.operatingDay = operatingDay;
    this.numberOfKilometers = numberOfKilometers;
  }

  static fromBlob({ operatingDay, numberOfKilometers }) {
    return new EndOfShiftBody(
      new DateBlock(
        'operatingDay',
        true,
        operatingDay ? parse(operatingDay, 'yyyy-MM-dd', new Date()) : null,
      ),
      new ChecklistFreeBlock('numberOfKilometers', true, numberOfKilometers),
    );
  }

  withBlock(block) {
    if (block instanceof DateBlock) {
      return new EndOfShiftBody(block, this.numberOfKilometers);
    }
    if (block instanceof ChecklistFreeBlock) {
      return new EndOfShiftBody(this.operatingDay, block);
    }
    return this;
  }

  toBody() {
    let newBody = {};
    Object.values(this).forEach((block) => {
      newBody = { ...newBody, ...block.toBody() };
    });
    return newBody;
  }

  toString() {
    return `operatingDay=${this.operatingDay.operatingDay}, numberOfKilometers=${this.numberOfKilometers.comment}`;
  }
}
