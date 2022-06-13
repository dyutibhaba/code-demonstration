import React from 'react';
import renderWithState from '../../../../../testutils';
import MaintenanceLayout from './MaintenanceLayout';

describe('Maintenance Layout', () => {
  it('renders the maintenance checklist layout correctly', () => {
    expect(renderWithState()((
      <MaintenanceLayout
        links={[{
          to: '/link/to',
          value: 'maintenance.tabs.checklists',
        }]}
        index={0}
      >
        Children
      </MaintenanceLayout>
    ))).toMatchSnapshot();
  });
});
