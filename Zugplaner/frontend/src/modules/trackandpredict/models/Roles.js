export const modulesMap = {
  TRACK_AND_PREDICT: 1,
  DIGITAL_CHECKIN: 2,
};

export default class Roles {
  static fromBlob(roles) {
    return new Roles(Roles.parseRoleInteger(roles));
  }

  static parseRoleInteger(roles) {
    const newRoles = {};
    Object.entries(roles).forEach(([module, role]) => {
      if (roles[module] === 0) {
        newRoles[module] = 99;
      } else {
        newRoles[module] = role;
      }
    });
    return newRoles;
  }

  static fromUpdatedData(roles) {
    const updatedRoles = {};
    Object.keys(roles).forEach((m) => {
      if (typeof roles[m] === 'string') {
        updatedRoles[m] = parseInt(roles[m], 10);
      } else {
        updatedRoles[m] = roles[m];
      }
    });
    return new Roles(updatedRoles);
  }

  constructor(roles) {
    this.values = {};
    Object.keys(roles).forEach((m) => {
      this.values[m] = roles[m];
    });
  }

  findChangedModule(roles) {
    let result = 'NOT_VALID';
    Object.keys(this.values).forEach((m) => {
      if (this.values[m] !== roles.values[m]) {
        result = m;
      }
    });
    return modulesMap[result];
  }

  static getModuleAsString(value) {
    return Object.keys(modulesMap).find((name) => modulesMap[name] === value);
  }
}
