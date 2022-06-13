import PropTypes from 'prop-types';
import DamageReportBody, { findStatusLabel } from './DamageReportBody';

export default class DamageReport {
  static empty() {
    return new DamageReport('id', 'assetId', DamageReportBody.fromBlob({}), 3, 'userName', null, null);
  }

  static fromBlob({
    id, assetId, body, type, creator, creationDate, lastUpdateDateTime,
  }) {
    return new DamageReport(
      id,
      assetId,
      DamageReportBody.fromBlob(body),
      type,
      creator.displayedName,
      creationDate,
      lastUpdateDateTime,
    );
  }

  static get shape() {
    return PropTypes.shape({
      id: PropTypes.string,
      assetId: PropTypes.string,
      body: DamageReportBody.shape,
      type: PropTypes.number,
      creator: PropTypes.string,
      creationDate: PropTypes.number,
      lastUpdateDateTime: PropTypes.number,
    });
  }

  isValid = () => this.damageStatus !== undefined
    && this.damageStatus !== null
    && this.damageComment !== undefined
    && this.damageComment !== null
    && this.damageDate !== undefined
    && this.damageDate !== null;

  constructor(
    id, assetId, body, type, creator, creationDate, lastUpdateDateTime,
  ) {
    this.id = id;
    this.assetId = assetId;
    this.body = body;
    this.type = type;
    this.creator = creator;
    this.creationDate = creationDate;
    this.lastUpdateDateTime = lastUpdateDateTime;
  }

  withBlock(block) {
    return new DamageReport(
      this.id,
      this.assetId,
      this.body.withBlock(block),
      this.type,
      this.creator,
      this.creationDate,
      this.lastUpdateDateTime,
    );
  }

  get damageStatus() {
    return this.body.damageStatus.selectedType;
  }

  get damageComment() {
    return this.body.damageComment.comment;
  }

  get damageDate() {
    return this.body.damageDate.date;
  }

  get damageAcknowledgementComment() {
    return this.body.damageAcknowledgementComment.comment;
  }

  get damageAcknowledgementDate() {
    return this.body.damageAcknowledgementDate.date;
  }

  get damageRepairComment() {
    return this.body.damageRepairComment.comment;
  }

  get damageRepairDate() {
    return this.body.damageRepairDate.date;
  }

  toTableFormat() {
    return {
      id: this.id,
      status: findStatusLabel(this.damageStatus),
      comment: this.damageComment,
      date: this.damageDate,
      creator: this.creator,
      lastUpdateDateTime: this.creationDate,
    };
  }

  toActivityFormat() {
    return {
      id: this.id,
      reportType: this.type,
      status: findStatusLabel(this.damageStatus),
      date: this.damageDate,
      creator: this.creator,
      lastUpdateDateTime: this.lastUpdateDateTime,
    };
  }

  withId(value) {
    return new DamageReport(
      value,
      this.assetId,
      this.body,
      this.type,
      this.creator,
      this.creationDate,
      this.lastUpdateDateTime,
    );
  }

  toString() {
    return `id = ${this.id} assetId = ${this.assetId} 
    body = ${this.body.toString()} type = ${this.type} creator = ${this.creator}
    creationDate = ${this.creationDate} lastUpdated = ${this.lastUpdateDateTime}`;
  }
}
