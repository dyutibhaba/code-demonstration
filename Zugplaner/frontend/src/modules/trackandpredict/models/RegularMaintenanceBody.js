import { parse } from 'date-fns';
import PropTypes from 'prop-types';
import ChecklistFreeBlock from './ChecklistFreeBlock';
import ChecklistMultiChoiceBlock from './ChecklistMultiChoiceBlock';
import DateBlock from './DateBlock';

export const regularMaintenanceFields = [
  'maintainer',
  'regularMaintenanceDate',
  'regularMaintenanceComment',
  'maintenanceType',
  'frequency',
];

export default class RegularMaintenanceBody {
  static get shape() {
    return PropTypes.shape({
      maintainer: ChecklistMultiChoiceBlock.shape,
      regularMaintenanceDate: DateBlock.shape,
      regularMaintenanceComment: ChecklistFreeBlock.shape,
      maintenanceType: ChecklistMultiChoiceBlock.shape,
      frequency: ChecklistFreeBlock.shape,
    });
  }

  static empty() {
    return RegularMaintenanceBody.fromBlob({});
  }

  constructor(
    maintainer,
    regularMaintenanceDate,
    regularMaintenanceComment,
    maintenanceType,
    frequency,
  ) {
    this.maintainer = maintainer;
    this.regularMaintenanceDate = regularMaintenanceDate;
    this.regularMaintenanceComment = regularMaintenanceComment;
    this.maintenanceType = maintenanceType;
    this.frequency = frequency;
  }

  static fromBlob({
    maintainerId,
    maintainerDisplayedName,
    regularMaintenanceDate,
    regularMaintenanceComment,
    maintenancePlanId,
    maintenanceType,
    frequency,
  }) {
    return new RegularMaintenanceBody(
      new ChecklistMultiChoiceBlock('maintainer', true, maintainerId, maintainerDisplayedName, []),
      new DateBlock(
        'regularMaintenanceDate',
        true,
        regularMaintenanceDate ? parse(regularMaintenanceDate, 'yyyy-MM-dd', new Date()) : new Date(),
      ),
      new ChecklistFreeBlock('regularMaintenanceComment', false, regularMaintenanceComment),
      new ChecklistMultiChoiceBlock('maintenanceType', true, maintenancePlanId, maintenanceType, []),
      new ChecklistFreeBlock('frequency', false, frequency),
    );
  }

  withBlock(block) {
    const fields = regularMaintenanceFields
      .map((item) => (block.name === item ? block : this[item]));
    return new RegularMaintenanceBody(...fields);
  }

  withMultipleBlock(block1, block2) {
    const fields1 = regularMaintenanceFields
      .map((item) => (block1.name === item ? block1 : this[item]));
    const fields2 = regularMaintenanceFields
      .map((item) => (block2.name === item ? block2 : this[item]));
    fields1[3] = fields2[3];
    return new RegularMaintenanceBody(...fields1);
  }

  toBody(filters = regularMaintenanceFields) {
    return filters.reduce((acc, cv) => ({ ...acc, ...this[cv].toBody() }), {});
  }

  toString() {
    return `maintainer=${this.maintainer.selected},
    regularMaintenanceDate=${this.regularMaintenanceDate.date},
    regularMaintenanceComment=${this.regularMaintenanceComment.comment},
    `;
  }
}
