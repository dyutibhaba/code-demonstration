import { parse } from 'date-fns';
import { deserialize } from '../../common/utils';
import ChecklistFreeBlock from './ChecklistFreeBlock';
import DateBlock from './DateBlock';
import EndOfShiftChecklist from './EndOfShiftChecklist';

describe('test checklist model parsing from back end', () => {
  it('parses the checklist with an empty body from the back end', () => {
    const checklistBlob = {
      id: '1',
      assetId: 'assetId',
      body: {'operatingDay': '12.12.2020'},
      type: 2,
      creator: { displayedName: 'userName' },
      daysInOperation: 0,
      creationDate: null, 
      lastUpdateDateTime: null
    }
    const checklist = EndOfShiftChecklist.fromBlob(checklistBlob);
    
    expect(deserialize(checklist))
    .toEqual(deserialize(EndOfShiftChecklist.fromBlob(checklistBlob)));
  });

  it('parses the checklist with a body from the back end', () => {
    const checklistBlob = {
      id: '1',
      assetId: 'assetId',
      body: {'numberOfKilometers': 54, 'operatingDay': '22.02.2021'},
      type: 2,
      creator: { displayedName: 'userName' },
      creationDate: null, 
      lastUpdateDateTime: null
    }
    const checklist = EndOfShiftChecklist.fromBlob(checklistBlob);
    expect(deserialize(checklist))
    .toEqual(deserialize(EndOfShiftChecklist.fromBlob(checklistBlob)));
  });
});

describe('Checklist model toTableFormat', () => {
  it('returns the table format properly', () => {
    const checklist = EndOfShiftChecklist.empty().withBlock({operationDay: '22.02.2021'});
    const expected = {
      id: 'id',
      type: 2,
      ko: checklist.buildKOs(),
      km: checklist.numberOfKilometers,
      creator: 'userName',
      days: 0,
      lastUpdateDateTime: null,
    }
    expect(checklist.toTableFormat()).toEqual(expected);
  });
});

describe('Checklist model isValid()', () => {
  it('returns false when the form is incomplete', () => {
    const checklist = EndOfShiftChecklist.empty()
      .withBlock({ operatingDay: '2021-02-22'});
    expect(checklist.isValid())
    .toEqual(false);
  });

  it('returns true when the form is complete', () => {
    const checklist = EndOfShiftChecklist.empty()
    .withBlock(new ChecklistFreeBlock('numberOfKilometers', true, 45))
    .withBlock(new DateBlock('operatingDay', true, parse('2021-02-22', 'yyyy-MM-dd', new Date())));
    expect(checklist.isValid()).toEqual(true);
  });
});
