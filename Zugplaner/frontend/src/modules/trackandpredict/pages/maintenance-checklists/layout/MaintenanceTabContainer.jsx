import React from 'react';
import PropTypes from 'prop-types';
import MaintenanceLayout from './MaintenanceLayout';
import Asset from '../../../models/Asset';

const MaintenanceTabContainer = ({ children, asset, index }) => {
  const maintenanceLinks = [
    {
      to: () => `/track-and-predict/maintenance-asset/${asset.id}/checklists`,
      value: 'maintenance.tabs.checklists',
    },
    {
      to: () => `/track-and-predict/maintenance-asset/${asset.id}/damages`,
      value: 'maintenance.tabs.damages',
    },
    {
      to: () => `/track-and-predict/maintenance-asset/${asset.id}/maintenance-parameters`,
      value: 'maintenance.tabs.parameters',
    },
    {
      to: () => `/track-and-predict/maintenance-asset/${asset.id}/maintenance-log`,
      value: 'maintenance.tabs.regular',
    },
    {
      to: () => `/track-and-predict/maintenance-asset/${asset.id}/activity`,
      value: 'maintenance.tabs.activity',
    },
    {
      to: () => `/track-and-predict/maintenance-asset/${asset.id}/mileage`,
      value: 'maintenance.tabs.mileage',
    },
  ];

  return (
    <MaintenanceLayout
      links={maintenanceLinks.map((link) => ({
        ...link,
        to: link.to(),
      }))}
      breadcrumb={[
        {
          link: `/track-and-predict/maintenance-asset/${asset.id}/overview`,
          label: asset && asset.name ? asset.name : 'unknown',
        },
      ]}
      index={index}
    >
      {children}
    </MaintenanceLayout>
  );
};

MaintenanceTabContainer.defaultProps = {
  children: null,
};

MaintenanceTabContainer.propTypes = {
  children: PropTypes.node,
  asset: Asset.shape.isRequired,
  index: PropTypes.number.isRequired,
};

export default MaintenanceTabContainer;
