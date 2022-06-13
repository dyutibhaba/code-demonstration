import { deserialize } from '../../common/utils';
import StartOfShiftBody from './StartOfShiftBody';
import StartOfShiftChecklist from './StartOfShiftChecklist';

const motorolstandBlob = {
  motorolstand:'yes',
  comment_motorolstand: undefined,
  liter_motorolstand: undefined,
};

const kuhlwasserstandBlob = {
  kuhlwasserstand:'yes',
  comment_kuhlwasserstand: undefined,
  liter_kuhlwasserstand: undefined,
};

const fahrwerkBlob = {
  fahrwerk:'yes',
  comment_fahrwerk: undefined,
  liter_fahrwerk: undefined,
};

const transmissionBlob = {
  transmissionOilControl:'yes',
  comment_transmissionOilControl: undefined,
  liter_transmissionOilControl: undefined,
};

const betriebstageBlob = {
  operatingDay: '12.12.2020',
};

describe('test checklist model parsing from back end', () => {
  it('parses the checklist with an empty body from the back end', () => {
    const checklistBlob = {
      id: 'id',
      assetId: 'assetId',
      body: {'operatingDay': '12.12.2020'},
      type: 1,
      creator: { displayedName: 'userName' },
      daysInOperation: 0,
      creationDate: '', 
      lastUpdateDateTime: null,
    }
    const checklist = StartOfShiftChecklist.fromBlob(checklistBlob);
    const expected = StartOfShiftChecklist.empty();
    expected.body.blocks[4].operatingDay = checklist.body.blocks[4].operatingDay;
    expect(deserialize(checklist))
    .toEqual(deserialize(expected));
  });

  it('parses the checklist with a body from the back end', () => {
    const checklistBlob = {
      id: '1',
      assetId: 'assetId',
      body: {'motorolstand': 'yes', 'kuhlwasserstand': 'no', 'operatingDay': '12.12.2020'},
      type: 1,
      creator: { displayedName: 'userName' },
      daysInOperation: 0,
      creationDate: null,
      lastUpdateDateTime: null
    }
    const checklist = StartOfShiftChecklist.fromBlob(checklistBlob);
    expect(deserialize(checklist))
    .toEqual(deserialize(StartOfShiftChecklist.fromBlob(checklistBlob)));
  });
});

describe('Checklist model KO builder', () => {
  it('returns one false and 3 true with one yes and 3 no', () => {
    const checklist = StartOfShiftChecklist.empty().withId('1').withBlock(StartOfShiftBody.createMotorOilBlock(motorolstandBlob));
    expect(checklist.buildKOs())
    .toEqual([false, true, true, true]);
  });
});

describe('Checklist model toTableFormat', () => {
  it('returns the table format properly', () => {
    const checklist = StartOfShiftChecklist.empty().withId('1').withBlock(StartOfShiftBody.createMotorOilBlock(motorolstandBlob));
    const expected = {
      id: '1',
      type: 1,
      ko: checklist.buildKOs(),
      waterliter: checklist.findLiterFor('kuhlwasserstand'),
      motoroilliter: checklist.findLiterFor('motorolstand'),
      creator: 'userName',
      days: 0,
      lastUpdateDateTime: null,
    }
    expect(checklist.toTableFormat())
    .toEqual(expected);
  });
});

describe('Checklist model isValid()', () => {
  it('returns false when the form is incomplete', () => {
    const checklist = StartOfShiftChecklist.empty()
      .withId('1')
      .withBlock(StartOfShiftBody.createMotorOilBlock(motorolstandBlob));
    expect(checklist.isValid())
    .toEqual(false);
  });

  it('returns true when the form is complete', () => {
    const checklist = StartOfShiftChecklist.empty()
      .withId('1')
      .withBlock(StartOfShiftBody.createMotorOilBlock(motorolstandBlob))
      .withBlock(StartOfShiftBody.createKuhlwasserstand(kuhlwasserstandBlob))
      .withBlock(StartOfShiftBody.createFahrwerk(fahrwerkBlob))
      .withBlock(StartOfShiftBody.createTransmissionOilControl(transmissionBlob))
      .withBlock(StartOfShiftBody.createBetriebstage(betriebstageBlob))

    expect(checklist.isValid())
    .toEqual(true);
  });
});
