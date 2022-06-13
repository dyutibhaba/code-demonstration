import { useHistory, useParams } from 'react-router';
import { FormattedMessage } from 'react-intl';
import React from 'react';
import { isAssetIdTemporary } from '../../../../../../common/utils';
import Button from '../../../../../../common/components/miscellaneous/Button';

const AddMaintenanceParametersButton = () => {
  const history = useHistory();
  const { assetId } = useParams();

  const handleBtnClick = () => {
    history.push(`/track-and-predict/maintenance-asset/${assetId}/maintenance-parameters/new`);
  };

  return (
    !isAssetIdTemporary(assetId)
    && (
      <div className="add-entry-button">
        <div className="tpz-menu-options tpz-header-mobile-menu-options">
          <div className="tpz-menu">
            <Button
              onClick={handleBtnClick}
              onKeyDown={handleBtnClick}
            >
              <FormattedMessage id="maintenance.maintenance-parameters.table.add.button" />
            </Button>
          </div>
        </div>
      </div>
    )
  );
};

export default AddMaintenanceParametersButton;
