import PropTypes from 'prop-types';
import StartOfShiftBody from './StartOfShiftBody';
import DateBlock from './DateBlock';

export default class StartOfShiftChecklist {
  static empty() {
    return new StartOfShiftChecklist('id', 'assetId', StartOfShiftBody.fromBlob({}), 1, 'userName', 0, '', null);
  }

  static fromBlob({
    id, assetId, body, type, creator, daysInOperation, creationDate, lastUpdateDateTime,
  }) {
    return new StartOfShiftChecklist(
      id,
      assetId,
      StartOfShiftBody.fromBlob(body),
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
      body: StartOfShiftBody.shape,
      type: PropTypes.number,
      creator: PropTypes.string,
      daysInOperation: PropTypes.number,
      creationDate: PropTypes.string,
      lastUpdateDateTime: PropTypes.string,
    });
  }

  isValid = () => this.body.blocks.reduce((acc, block) => acc && block.isBlockValid(), true);

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
    return new StartOfShiftChecklist(
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

  buildKOs() {
    const result = [];
    const yesNoBody = this.body.blocks.slice(0, 4);
    yesNoBody.forEach((item) => result.push(item.status !== 'yes'));
    return result;
  }

  buildActivityStatus() {
    return this.buildKOs().reduce((acc, bool) => acc && !bool, true)
      ? 'maintenance.activity.table.status.ok'
      : 'maintenance.activity.table.status.ko';
  }

  findLiterFor(string) {
    return this.body.blocks.find((block) => block.name === string).liter;
  }

  findDate() {
    return this.body.blocks.filter((b) => b instanceof DateBlock).map((d) => d.date);
  }

  toTableFormat() {
    return {
      id: this.id,
      type: this.type,
      ko: this.buildKOs(),
      waterliter: this.findLiterFor('kuhlwasserstand'),
      motoroilliter: this.findLiterFor('motorolstand'),
      creator: this.creator,
      days: this.daysInOperation,
      lastUpdateDateTime: this.lastUpdateDateTime,
    };
  }

  toActivityFormat() {
    return {
      id: this.id,
      reportType: this.type,
      status: this.buildActivityStatus(),
      date: this.findDate(),
      creator: this.creator,
      lastUpdateDateTime: this.lastUpdateDateTime,
    };
  }

  withId(value) {
    return new StartOfShiftChecklist(
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
