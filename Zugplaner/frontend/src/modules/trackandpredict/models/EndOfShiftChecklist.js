import PropTypes from 'prop-types';
import EndOfShiftBody from './EndOfShiftBody';

export default class EndOfShiftChecklist {
  static empty() {
    return new EndOfShiftChecklist('id', 'assetId', EndOfShiftBody.fromBlob({}), 2, 'userName', 0, '', null);
  }

  static fromBlob({
    id, assetId, body, type, creator, daysInOperation, creationDate, lastUpdateDateTime,
  }) {
    return new EndOfShiftChecklist(
      id,
      assetId,
      EndOfShiftBody.fromBlob(body),
      type,
      creator.displayedName,
      Math.max(daysInOperation, 0),
      creationDate,
      lastUpdateDateTime,
    );
  }

  static get shape() {
    return PropTypes.shape({
      id: PropTypes.string,
      assetId: PropTypes.string,
      body: EndOfShiftBody.shape,
      type: PropTypes.number,
      creator: PropTypes.string,
      daysInOperation: PropTypes.number,
      creationDate: PropTypes.string,
      lastUpdateDateTime: PropTypes.string,
    });
  }

  isValid = () => this.operatingDay !== undefined
    && this.operatingDay !== null
    && this.numberOfKilometers !== undefined
    && this.numberOfKilometers !== null;

  constructor(
    id, assetId, body, type, creator, daysInOperation, creationDate, lastUpdateDateTime,
  ) {
    this.id = id;
    this.assetId = assetId;
    this.body = body;
    this.type = type;
    this.creator = creator;
    this.daysInOperation = daysInOperation;
    this.creationDate = creationDate;
    this.lastUpdateDateTime = lastUpdateDateTime;
  }

  withBlock(block) {
    return new EndOfShiftChecklist(
      this.id,
      this.assetId,
      this.body.withBlock(block),
      this.type,
      this.creator,
      this.daysInOperation,
      this.creationDate,
      this.lastUpdateDateTime,
    );
  }

  // no logic involved here: EOS does not have any KO fields
  // eslint-disable-next-line class-methods-use-this
  buildKOs() {
    return [];
  }

  get numberOfKilometers() {
    return this.body.numberOfKilometers.comment;
  }

  get operatingDay() {
    return this.body.operatingDay.date;
  }

  toTableFormat() {
    return {
      id: this.id,
      type: this.type,
      ko: this.buildKOs(),
      km: this.numberOfKilometers,
      creator: this.creator,
      days: this.daysInOperation,
      lastUpdateDateTime: this.lastUpdateDateTime,
    };
  }

  toActivityFormat() {
    return {
      id: this.id,
      reportType: this.type,
      status: 'maintenance.activity.table.status.na',
      date: this.operatingDay,
      creator: this.creator,
      lastUpdateDateTime: this.lastUpdateDateTime,
    };
  }

  withId(value) {
    return new EndOfShiftChecklist(
      value,
      this.assetId,
      this.body,
      this.type,
      this.creator,
      this.daysInOperation,
      this.creationDate,
      this.lastUpdateDateTime,
    );
  }

  toString() {
    return `id = ${this.id} assetId = ${this.assetId} 
    body = ${this.body.toString()} type = ${this.type} creator = ${this.creator} daysInOperation = ${this.daysInOperation}
    creationDate = ${this.creationDate} lastUpdated = ${this.lastUpdateDateTime}`;
  }
}
