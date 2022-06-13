import { parse } from 'date-fns';
import PropTypes from 'prop-types';
import ChecklistFreeBlock from './ChecklistFreeBlock';
import ChecklistMultiChoiceBlock from './ChecklistMultiChoiceBlock';
import DateBlock from './DateBlock';

export const damageFields = [
  'damageStatus',
  'damageComment',
  'damageDate',
  'damageAcknowledgementComment',
  'damageAcknowledgementDate',
  'damageRepairComment',
  'damageRepairDate',
];

export const initialBodyFields = [
  'damageStatus',
  'damageComment',
  'damageDate',
];

export const DAMAGE_TYPE_1 = 1;
export const DAMAGE_TYPE_2 = 2;
export const DAMAGE_TYPE_3 = 3;

export const damageStatusOptions = [
  {
    type: DAMAGE_TYPE_1,
    label: 'maintenance.damages.status.type1',
  }, {
    type: DAMAGE_TYPE_2,
    label: 'maintenance.damages.status.type2',
  }, {
    type: DAMAGE_TYPE_3,
    label: 'maintenance.damages.status.type3',
  },
];

export const findStatusLabel = (damageStatus) => {
  const result = damageStatusOptions.find((item) => item.type === parseInt(damageStatus, 10));
  return result ? result.label : '';
};

export default class DamageReportBody {
  static get shape() {
    return PropTypes.shape({
      damageStatus: ChecklistMultiChoiceBlock.shape,
      damageComment: ChecklistFreeBlock.shape,
      damageDate: DateBlock.shape,
      damageAcknowledgementComment: ChecklistFreeBlock.shape,
      damageAcknowledgementDate: DateBlock.shape,
      damageRepairComment: ChecklistFreeBlock.shape,
      damageRepairDate: DateBlock.shape,
    });
  }

  static empty() {
    return DamageReportBody.fromBlob({});
  }

  constructor(
    damageStatus,
    damageComment,
    damageDate,
    damageAcknowledgementComment,
    damageAcknowledgementDate,
    damageRepairComment,
    damageRepairDate,
  ) {
    this.damageStatus = damageStatus;
    this.damageComment = damageComment;
    this.damageDate = damageDate;
    this.damageAcknowledgementComment = damageAcknowledgementComment;
    this.damageAcknowledgementDate = damageAcknowledgementDate;
    this.damageRepairComment = damageRepairComment;
    this.damageRepairDate = damageRepairDate;
  }

  static fromBlob({
    damageStatus,
    damageComment,
    damageDate,
    damageAcknowledgementComment,
    damageAcknowledgementDate,
    damageRepairComment,
    damageRepairDate,
  }) {
    return new DamageReportBody(
      new ChecklistMultiChoiceBlock('damageStatus', true, damageStatus, findStatusLabel(damageStatus), damageStatusOptions),
      new ChecklistFreeBlock('damageComment', true, damageComment),
      new DateBlock(
        'damageDate',
        true,
        damageDate ? parse(damageDate, 'yyyy-MM-dd', new Date()) : new Date(),
      ),
      new ChecklistFreeBlock('damageAcknowledgementComment', false, damageAcknowledgementComment),
      new DateBlock(
        'damageAcknowledgementDate',
        false,
        damageAcknowledgementDate ? parse(damageAcknowledgementDate, 'yyyy-MM-dd', new Date()) : null,
      ),
      new ChecklistFreeBlock('damageRepairComment', false, damageRepairComment),
      new DateBlock(
        'damageRepairDate',
        false,
        damageRepairDate ? parse(damageRepairDate, 'yyyy-MM-dd', new Date()) : null,
      ),
    );
  }

  withBlock(block) {
    const fields = damageFields.map((item) => (block.name === item ? block : this[item]));
    return new DamageReportBody(...fields);
  }

  toBody(filters = damageFields) {
    return filters.reduce((acc, cv) => ({ ...acc, ...this[cv].toBody() }), {});
  }

  toString() {
    return `status=${this.damageStatus.selected},
     damageComment=${this.damageComment.comment},
     damageDate=${this.damageDate.date},
     damageAcknowledgementComment=${this.damageAcknowledgementComment},
     damageAcknowledgementDate=${this.damageAcknowledgementDate},
     damageRepairComment=${this.damageRepairComment},
     damageRepairDate=${this.damageRepairDate},
    `;
  }
}
