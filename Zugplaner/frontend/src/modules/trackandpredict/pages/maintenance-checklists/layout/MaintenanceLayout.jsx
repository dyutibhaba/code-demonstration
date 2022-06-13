import React from 'react';
import PropTypes from 'prop-types';

import MainLayout from '../../../../common/components/layout/main-layout/MainLayout';
import Navigation, { breadCrumbShape, linkShape } from '../../../../admin/pages/Navigation';
import { anyNumberOfChildren } from '../../../../common/models/miscellaneousProps';

const MaintenanceLayout = ({
  children,
  links,
  breadcrumb,
  index,
}) => (
  <MainLayout moduleName="Track&Predict" moduleHome="/track-and-predict" breadcrumb={breadcrumb}>
    <Navigation
      links={links}
      selectedIndex={index}
      setTabIndex={() => {}}
    />
    {children}
  </MainLayout>
);

MaintenanceLayout.propTypes = {
  children: anyNumberOfChildren.isRequired,
  links: PropTypes.arrayOf(linkShape).isRequired,
  breadcrumb: PropTypes.arrayOf(breadCrumbShape),
  index: PropTypes.number.isRequired,
};

MaintenanceLayout.defaultProps = {
  breadcrumb: null,
};

export default MaintenanceLayout;
