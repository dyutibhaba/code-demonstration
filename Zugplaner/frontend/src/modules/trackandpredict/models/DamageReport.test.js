import { parse } from 'date-fns';
import { deserialize } from '../../common/utils';
import ChecklistFreeBlock from './ChecklistFreeBlock';
import ChecklistMultiChoiceBlock from './ChecklistMultiChoiceBlock';
import DamageReport from './DamageReport';
import { damageStatusOptions } from './DamageReportBody';
import DateBlock from './DateBlock';

describe('test damage report model parsing from back end', () => {
  it('parses the damage report with an empty body from the back end', () => {
    const damageReportBlob = {
      id: '1',
      assetId: 'assetId',
      body: {
        damageDate: "2021-03-21",
        damageAcknowledgementDate: "2021-03-21",
        damageRepairDate: "2021-03-21",
      },
      type: 3,
      creator: { diplayedName: 'userId' },
      creationDate: null,
      lastUpdateDateTime: null,
    };
    const damageReport = DamageReport.fromBlob(damageReportBlob);

    expect(deserialize(damageReport)).toEqual(
      deserialize(DamageReport.fromBlob(damageReportBlob))
    );
  });

  it('parses the damage report with a body from the back end', () => {
    const damageReportBlob = {
      id: '1',
      assetId: 'assetId',
      body: {
        damageStatus: 1,
        damageDate: "22.02.2021",
        damageComment: "a comment",
        damageAcknowledgementDate: "2021-03-21",
        damageRepairDate: "2021-03-21",
      },
      type: 3,
      creator: { displayedName: 'userName' },
      creationDate: null,
      lastUpdateDateTime: null,
    };
    const damageReport = DamageReport.fromBlob(damageReportBlob);
    expect(deserialize(damageReport)).toEqual(
      deserialize(DamageReport.fromBlob(damageReportBlob))
    );
  });
});

describe('DamageReport model toTableFormat()', () => {
  it('returns the damage report table format properly', () => {
    const damageReport = DamageReport.empty()
      .withBlock({ damageDate: '2021-02-22' })
      .withBlock(
        new ChecklistMultiChoiceBlock(
          'damageStatus',
          true,
          1,
          'id',
          damageStatusOptions
        )
      )
      .withBlock({ damageComment: 'some comment' });
    const expected = {
      id: 'id',
      status: 'maintenance.damages.status.type1',
      comment: damageReport.damageComment,
      date: damageReport.damageDate,
      creator: 'userName',
      lastUpdateDateTime: null,
    };
    expect(damageReport.toTableFormat()).toEqual(expected);
  });
});

describe('DamageReport model isValid()', () => {
  it('returns false when the form is incomplete', () => {
    const damageReport = DamageReport.empty().withBlock({
      damageDate: '2021-02-22',
    });
    expect(damageReport.isValid()).toEqual(false);
  });

  it('returns true when the form is complete', () => {
    const damageReport = DamageReport.empty()
      .withBlock(new ChecklistFreeBlock('damageComment', true, 'some comment'))
      .withBlock(
        new DateBlock(
          'damageDate',
          true,
          parse('2021-02-22', 'yyyy-MM-dd', new Date())
        )
      )
      .withBlock(
        new ChecklistMultiChoiceBlock(
          'damageStatus',
          true,
          1,
          'id',
          damageStatusOptions
        )
      );
    expect(damageReport.isValid()).toEqual(true);
  });
});
