import PropTypes from 'prop-types';
import { parse } from 'date-fns';
import ChecklistFreeBlock from './ChecklistFreeBlock';
import ChecklistYesNoBlock from './ChecklistYesNoBlock';
import DateBlock from './DateBlock';

export default class StartOfShiftBody {
  static get shape() {
    return PropTypes.shape({
      blocks: PropTypes.arrayOf(
        PropTypes.oneOfType([
          ChecklistYesNoBlock.shape,
          ChecklistFreeBlock.shape,
          DateBlock.shape,
        ]),
      ),
    });
  }

  static empty() {
    return StartOfShiftBody.fromBlob({});
  }

  constructor(blocks) {
    this.blocks = blocks;
  }

  static fromBlob(object) {
    return new StartOfShiftBody([
      StartOfShiftBody.createMotorOilBlock(object),
      StartOfShiftBody.createKuhlwasserstand(object),
      StartOfShiftBody.createFahrwerk(object),
      StartOfShiftBody.createTransmissionOilControl(object),
      StartOfShiftBody.createBetriebstage(object),
      StartOfShiftBody.createStangenlagerPrufen(object),
    ]);
  }

  static createMotorOilBlock(object) {
    return new ChecklistYesNoBlock(
      'motorolstand',
      true,
      object.motorolstand,
      true,
      object.comment_motorolstand,
      true,
      object.liter_motorolstand,
    );
  }

  static createKuhlwasserstand(object) {
    return new ChecklistYesNoBlock(
      'kuhlwasserstand',
      true,
      object.kuhlwasserstand,
      true,
      object.comment_kuhlwasserstand,
      true,
      object.liter_kuhlwasserstand,
    );
  }

  static createFahrwerk(object) {
    return new ChecklistYesNoBlock(
      'fahrwerk',
      true,
      object.fahrwerk,
      true,
      object.comment_fahrwerk,
      false,
      null,
    );
  }

  static createTransmissionOilControl(object) {
    return new ChecklistYesNoBlock(
      'transmissionOilControl',
      true,
      object.transmissionOilControl,
      true,
      object.comment_transmissionOilControl,
      true,
      object.liter_transmissionOilControl,
    );
  }

  static createStangenlagerPrufen(object) {
    return new ChecklistFreeBlock(
      'stangenlagerPrufen',
      false,
      object.stangenlagerPrufen,
    );
  }

  static createBetriebstage(object) {
    return new DateBlock(
      'operatingDay',
      true,
      object.operatingDay ? parse(object.operatingDay, 'yyyy-MM-dd', new Date()) : null,
    );
  }

  withBlock(block) {
    return new StartOfShiftBody(this.blocks.map((b) => (b.name === block.name ? block : b)));
  }

  toBody() {
    let newBody = {};
    this.blocks.forEach((block) => {
      newBody = { ...newBody, ...block.toBody() };
    });
    return newBody;
  }

  toString() {
    return `blocks = ${this.blocks.toString()}`;
  }
}
