import PropTypes from 'prop-types';
import { anyNumberOfChildren } from '../../common/models/miscellaneousProps';
import Roles from './Roles';

export default class Member {
  static empty() {
    return new Member('id', 'user@thalesdigital.io', 'User Name', new Roles({}));
  }

  static fromBlob({
    id, email, displayedName, roles,
  }) {
    return new Member(id, email, displayedName, Roles.fromBlob(roles));
  }

  static fromUpdatedData(member) {
    return new Member(
      member.id,
      member.email,
      member.displayedName,
      member.roles,
    );
  }

  static get shape() {
    return PropTypes.shape({
      id: PropTypes.string,
      email: PropTypes.string,
      displayedName: PropTypes.string,
      roles: PropTypes.shape({
        children: anyNumberOfChildren,
      }),
    });
  }

  constructor(id, email, displayedName, roles) {
    this.id = id;
    this.email = email;
    this.displayedName = displayedName;
    this.roles = roles;
  }

  withId(value) {
    return new Member(
      value,
      this.email,
      this.displayedName,
      this.roles,
    );
  }
}
