import { deserialize } from '../../common/utils';
import ChecklistFreeBlock from './ChecklistFreeBlock';
import ChecklistMultiChoiceBlock from './ChecklistMultiChoiceBlock';
import DateBlock from './DateBlock';
import RegularMaintenance from './RegularMaintenance';

describe('test regular maintenance model parsing from back end', () => {
  it('parses the regular maintenance with an empty body from the back end', () => {
    const regularMaintenanceBlob = {
      id: '1',
      assetId: 'assetId',
      body: {
        regularMaintenanceDate: '2021-03-21',
      },
      type: 4,
      creator: { diplayedName: 'userId' },
      creationDate: null,
      lastUpdateDateTime: null,
    };
    const regularMaintenance = RegularMaintenance.fromBlob(regularMaintenanceBlob);

    expect(deserialize(regularMaintenance)).toEqual(
      deserialize(RegularMaintenance.fromBlob(regularMaintenanceBlob)),
    );
  });

  it('parses the regular maintenance with a body from the back end', () => {
    const regularMaintenanceBlob = {
      id: '1',
      assetId: 'assetId',
      body: {
        regularMaintenanceDate: '2021-03-21',
        regularMaintenanceComment: 'some comment',
        maintainerDisplayedName: 'Maintainer Name',
        maintainerId: '1234',
      },
      type: 4,
      creator: { diplayedName: 'userId' },
      creationDate: null,
      lastUpdateDateTime: null,
    };
    const regularMaintenance = RegularMaintenance.fromBlob(regularMaintenanceBlob);

    expect(deserialize(regularMaintenance)).toEqual(
      deserialize(RegularMaintenance.fromBlob(regularMaintenanceBlob)),
    );
  });
});

describe('RegularMaintenance', () => {
  it('model toTableFormat() returns the regular maintenance table format properly', () => {
    const regularMaintenance = RegularMaintenance.empty()
      .withBlock(new DateBlock(
        'regularMaintenanceDate',
        true,
        new Date('2021-03-12'),
      ))
      .withBlock(new ChecklistFreeBlock(
        'regularMaintenanceComment',
        true,
        'regular maintenance comment',
      ))
      .withBlock(new ChecklistMultiChoiceBlock(
        'maintainer',
        true,
        null,
        null,
        [],
      ));

    const expected = {
      id: 'id',
      maintenanceType: regularMaintenance.maintenanceType,
      comment: regularMaintenance.regularMaintenanceComment,
      date: regularMaintenance.regularMaintenanceDate,
      maintainer: regularMaintenance.maintainer,
    };

    expect(regularMaintenance.toTableFormat()).toEqual(expected);
  });

  it('isValid() returns false when the form is incomplete', () => {
    const regularMaintenance = RegularMaintenance.empty()
      .withBlock(new DateBlock(
        'regularMaintenanceDate',
        true,
        new Date('2021-03-12'),
      ));

    expect(regularMaintenance.isValid()).toEqual(false);
  });

  it('isValid() returns true when the form is complete', () => {
    const regularMaintenance = RegularMaintenance.empty()
      .withBlock(new DateBlock(
        'regularMaintenanceDate',
        true,
        new Date('2021-03-12'),
      ))
      .withBlock(new ChecklistMultiChoiceBlock(
        'maintainer',
        true,
        'maintainerId',
        'Maintainer Name',
        [],
      ))
      .withBlock(new ChecklistMultiChoiceBlock(
        'maintenanceType',
        true,
        'maintenancePlanId',
        'Maintenance Type',
        [],
      ));

    expect(regularMaintenance.isValid()).toEqual(true);
  });
});
