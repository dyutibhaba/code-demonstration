import React from 'react';
import renderWithState from '../../../../../testutils';
import Asset from '../../../models/Asset';
import MaintenanceTabContainer from './MaintenanceTabContainer';

describe('Maintenance Tab Container', () => {
  it('renders the maintenance tab container correctly', () => {
    expect(renderWithState({
      assetsWithState: [],
    })((
      <MaintenanceTabContainer asset={Asset.empty()} index={0}>
        Children
      </MaintenanceTabContainer>
    ))).toMatchSnapshot();
  });
});
