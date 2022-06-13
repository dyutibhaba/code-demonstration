import PropTypes from 'prop-types';
import MaintenanceParametersBody, { findFrequencyUnitLabel } from './MaintenanceParametersBody';

export default class MaintenanceParameters {
  static empty() {
    return new MaintenanceParameters('id', 'assetId', MaintenanceParametersBody.fromBlob({}), 3, 'userName', null, null);
  }

  static fromBlob({
    id, assetId, body, type, creator, creationDate, lastUpdateDateTime,
  }) {
    return new MaintenanceParameters(
      id,
      assetId,
      MaintenanceParametersBody.fromBlob(body),
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
      body: MaintenanceParametersBody.shape,
      type: PropTypes.number,
      creator: PropTypes.string,
      creationDate: PropTypes.number,
      lastUpdateDateTime: PropTypes.number,
    });
  }

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
    return new MaintenanceParameters(
      this.id,
      this.assetId,
      this.body.withBlock(block),
      this.type,
      this.creator,
      this.creationDate,
      this.lastUpdateDateTime,
    );
  }

  get maintenanceType() {
    return this.body.maintenanceType.comment;
  }

  get frequencyValue() {
    return this.body.frequencyValue.comment;
  }

  get frequencyUnit() {
    return this.body.frequencyType.selectedType;
  }

  get comment() {
    return this.body.comment.comment;
  }

  isValid = () => this.maintenanceType !== undefined
    && this.maintenanceType !== null
    && this.frequencyValue !== undefined
    && this.frequencyValue !== null
    && this.frequencyUnit !== undefined
    && this.frequencyUnit !== null;

  toMaintenancePlanFormat() {
    return {
      id: this.id,
      maintenanceType: this.maintenanceType,
      frequencyValue: this.frequencyValue,
      frequencyUnit: findFrequencyUnitLabel(this.frequencyUnit),
      alarmIsSet: this.body.alarmIsSet.comment,
      alarmIsDue: this.body.alarmIsDue.comment,
      comment: this.body.comment.comment,
      lastUpdateDateTime: this.lastUpdateDateTime,
    };
  }

  prepareForRegularMaintenanceFormat() {
    return {
      id: this.id,
      maintenanceType: this.maintenanceType,
      frequencyUnit: findFrequencyUnitLabel(this.frequencyUnit),
    };
  }

  toFrequency() {
    return {
      value: this.frequencyValue,
      type: this.frequencyUnit,
    };
  }

  toUpdateBody() {
    return {
      maintenanceType: this.body.maintenanceType.comment,
      alarm: { isSet: this.body.alarmIsSet.comment },
      frequency: this.toFrequency(),
      assetId: this.assetId,
      comment: this.comment,
    };
  }
}
