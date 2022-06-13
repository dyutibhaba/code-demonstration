import Roles from './Roles';

describe('test role model', () => {
  it('parses the role from the back end', () => {
    const rolesBack = {
      TRACK_AND_PREDICT: 1,
      DIGITAL_CHECK_IN: 2
    }
    const roles = Roles.fromBlob(rolesBack);

    expect(roles.values)
    .toEqual(rolesBack);
  });

  it('parses the role from the updated role', () => {
    const roles = {
      TRACK_AND_PREDICT: '1',
      DIGITAL_CHECK_IN: 2
    }
    const updatedRoles = Roles.fromUpdatedData(roles);

    expect(updatedRoles.values)
    .toEqual({
      TRACK_AND_PREDICT: 1,
      DIGITAL_CHECK_IN: 2
    });
  });

  it('finds out which module role changed', () => {
    const oldRolesData = {
      TRACK_AND_PREDICT: 1,
      DIGITAL_CHECK_IN: 1
    }
    const newRolesData = {
      TRACK_AND_PREDICT: '2',
      DIGITAL_CHECK_IN: 1
    }
    const oldRoles = Roles.fromBlob(oldRolesData);
    const newRoles = Roles.fromUpdatedData(newRolesData);

    const changedModule = newRoles.findChangedModule(oldRoles);

    expect(changedModule)
    .toEqual(1);
  });
});
