import PropTypes from 'prop-types';
import ChecklistMultiChoiceBlock from './ChecklistMultiChoiceBlock';
import ChecklistFreeBlock from './ChecklistFreeBlock';

export const maintenanceParamFields = [
  'maintenanceType',
  'frequencyValue',
  'frequencyType',
  'alarmIsSet',
  'alarmIsDue',
  'comment',
];

export const FREQUENCY_UNIT_DAYS_CALENDER = 1;
export const FREQUENCY_UNIT_DAYS_IN_OPERATION = 2;
export const FREQUENCY_UNIT_KMS = 3;

export const frequencyUnitOptions = [
  {
    type: FREQUENCY_UNIT_DAYS_IN_OPERATION,
    label: 'maintenance.maintenance-parameters.frequency-unit.days.operation',
  }, {
    type: FREQUENCY_UNIT_DAYS_CALENDER,
    label: 'maintenance.maintenance-parameters.frequency-unit.days.calender',
  }, {
    type: FREQUENCY_UNIT_KMS,
    label: 'maintenance.maintenance-parameters.frequency-unit.kilometers',
  },
];

export const findFrequencyUnitLabel = (frequencyType) => {
  const result = frequencyUnitOptions.find((item) => item.type === parseInt(frequencyType, 10));
  return result ? result.label : '';
};

export default class MaintenanceParametersBody {
  constructor(
    maintenanceType,
    frequencyValue,
    frequencyType,
    alarmIsSet,
    alarmIsDue,
    comment,
  ) {
    this.maintenanceType = maintenanceType;
    this.frequencyValue = frequencyValue;
    this.frequencyType = frequencyType;
    this.alarmIsSet = alarmIsSet;
    this.alarmIsDue = alarmIsDue;
    this.comment = comment;
  }

  static get shape() {
    return PropTypes.shape({
      maintenanceType: ChecklistFreeBlock.shape,
      frequencyValue: ChecklistFreeBlock.shape,
      frequencyType: ChecklistMultiChoiceBlock.shape,
      alarmIsSet: ChecklistFreeBlock.shape,
      alarmIsDue: ChecklistFreeBlock.shape,
      comment: ChecklistFreeBlock.shape,
    });
  }

  static fromBlob({
    maintenanceType,
    frequencyValue,
    frequencyType,
    alarmIsSet,
    alarmIsDue,
    comment,
  }) {
    return new MaintenanceParametersBody(
      new ChecklistFreeBlock('maintenanceType', true, maintenanceType),
      new ChecklistFreeBlock('frequencyValue', true, frequencyValue),
      new ChecklistMultiChoiceBlock('frequencyType', true, frequencyType, findFrequencyUnitLabel(frequencyType), frequencyUnitOptions),
      new ChecklistFreeBlock('alarmIsSet', false, alarmIsSet),
      new ChecklistFreeBlock('alarmIsDue', false, alarmIsDue),
      new ChecklistFreeBlock('comment', false, comment),
    );
  }

  withBlock(block) {
    const fields = maintenanceParamFields.map((item) => (block.name === item ? block : this[item]));
    return new MaintenanceParametersBody(...fields);
  }

  toBody(filters = maintenanceParamFields) {
    return filters.reduce((acc, cv) => ({ ...acc, ...this[cv].toBody() }), {});
  }
}
