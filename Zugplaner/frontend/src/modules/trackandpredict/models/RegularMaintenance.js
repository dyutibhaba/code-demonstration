import PropTypes from 'prop-types';
import RegularMaintenanceBody from './RegularMaintenanceBody';

export default class RegularMaintenance {
  static empty() {
    return new RegularMaintenance(
      'id', 'assetId',
      RegularMaintenanceBody.fromBlob({}),
      4, 'userName', 'maintenancePlanId', 'maintenanceType', null, null,
    );
  }

  static fromBlob({
    id, assetId, body, type, creator, maintenancePlanId, maintenanceType,
    creationDate, lastUpdateDateTime,
  }) {
    return new RegularMaintenance(
      id,
      assetId,
      RegularMaintenanceBody.fromBlob(body),
      type,
      creator.displayedName,
      maintenancePlanId,
      maintenanceType,
      creationDate,
      lastUpdateDateTime,
    );
  }

  static get shape() {
    return PropTypes.shape({
      id: PropTypes.string,
      assetId: PropTypes.string,
      body: RegularMaintenanceBody.shape,
      type: PropTypes.number,
      creator: PropTypes.string,
      maintenancePlanId: PropTypes.string,
      maintenanceType: PropTypes.string,
      maintenanceType2: PropTypes.string,
      creationDate: PropTypes.number,
      lastUpdateDateTime: PropTypes.number,
    });
  }

  isValid() {
    return this.maintainer !== undefined
      && this.maintainer !== null
      && this.regularMaintenanceDate !== undefined
      && this.regularMaintenanceDate !== null
      && this.maintenanceType !== undefined
      && this.maintenanceType !== null;
  }

  constructor(
    id, assetId, body, type, creator, maintenancePlanId, maintenanceType,
    creationDate, lastUpdateDateTime,
  ) {
    this.id = id;
    this.assetId = assetId;
    this.body = body;
    this.type = type;
    this.creator = creator;
    this.maintenancePlanId = maintenancePlanId;
    this.maintenanceType2 = maintenanceType;
    this.creationDate = creationDate;
    this.lastUpdateDateTime = lastUpdateDateTime;
  }

  withBlock(block) {
    return new RegularMaintenance(
      this.id,
      this.assetId,
      this.body.withBlock(block),
      this.type,
      this.creator,
      this.creationDate,
      this.lastUpdateDateTime,
    );
  }

  withMultipleBlock(block1, block2) {
    return new RegularMaintenance(
      this.id,
      this.assetId,
      this.body.withMultipleBlock(block1, block2),
      this.type,
      this.creator,
      this.creationDate,
      this.lastUpdateDateTime,
    );
  }

  get maintainer() {
    return this.body.maintainer.selectedType;
  }

  get regularMaintenanceComment() {
    return this.body.regularMaintenanceComment.comment;
  }

  get regularMaintenanceDate() {
    return this.body.regularMaintenanceDate.date;
  }

  get maintenanceType() {
    return this.body.maintenanceType.selectedType;
  }

  get frequency() {
    return this.body.frequency.comment;
  }

  toTableFormat() {
    return {
      id: this.id,
      maintenanceType: this.maintenanceType2,
      comment: this.regularMaintenanceComment,
      date: this.regularMaintenanceDate,
      maintainer: this.body.maintainer.selectedLabel,
      lastUpdateDateTime: this.creationDate,
    };
  }

  toActivityFormat() {
    return {
      id: this.id,
      reportType: this.type,
      status: 'maintenance.activity.table.status.na',
      date: this.regularMaintenanceDate,
      creator: this.body.maintainer.selectedLabel,
      lastUpdateDateTime: this.lastUpdateDateTime,
    };
  }

  withId(value) {
    return new RegularMaintenance(
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
