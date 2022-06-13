import { useDispatch, useSelector } from 'react-redux';
import React, { useCallback, useEffect, useState } from 'react';
import { useHistory } from 'react-router-dom';
import { useParams } from 'react-router';
import { ThemeProvider } from 'styled-components';
import Theme from '../../../../../../../../assets/themes/theme';
import GlobalStyle from '../../../../../../../../assets/css/GlobalStyle';

import { isAdmin } from '../../../../../../../common/store/selector/userSelector';
import { getCurrentMaintenancePlan } from '../../../../../../store/checklist/selectors/maintenancePlanSelectors';
import {
  askForMaintenancePlan,
  askForMaintenancePlanUpdation,
} from '../../../../../../store/checklist/actions/checklistActions';
import LoggedBackLayout from '../../common/navigation/LoggedBackLayout';
import StyledMaintenanceParametersForm from '../creation/StyledMaintenanceParametersForm';
import MaintenancePlanDetailsForm from './MaintenancePlanDetailsForm';
// eslint-disable-next-line import/no-named-as-default
import Loader from '../../../../../../../common/components/miscellaneous/Loader';

const MaintenancePlanDetailsPage = () => {
  const dispatch = useDispatch();
  const history = useHistory();
  const { assetId, maintenancePlanId } = useParams();
  const maintenancePlan = useSelector(getCurrentMaintenancePlan);
  const [maintenancePlanReport, setMaintenancePlanReport] = useState(maintenancePlan);
  const userIsAdmin = useSelector(isAdmin);

  useEffect(() => {
    dispatch(askForMaintenancePlan(assetId, maintenancePlanId));
  }, [maintenancePlanId]);

  const onClickBack = () => {
    history.push(`/track-and-predict/maintenance-asset/${assetId}/maintenance-parameters`);
  };

  const onClickValidate = useCallback(() => {
    dispatch(askForMaintenancePlanUpdation(
      assetId, maintenancePlanId, maintenancePlanReport.toUpdateBody(), history,
    ));
  }, [maintenancePlanReport]);

  return (
    <ThemeProvider theme={Theme}>
      <GlobalStyle />
      <LoggedBackLayout
        backgroundColor={Theme.colors.lightMain}
        cta={userIsAdmin}
        report={maintenancePlanReport}
        title="maintenance.maintenance-parameters.form.details.description"
        disabled={!(maintenancePlanReport && maintenancePlanReport.isValid())}
        onClickValidate={onClickValidate}
        onClickBack={onClickBack}
      >
        <main>
          {(() => {
            if (maintenancePlanReport) {
              return (
                <StyledMaintenanceParametersForm>
                  <MaintenancePlanDetailsForm
                    maintenancePlanReport={maintenancePlanReport}
                    readOnly={!userIsAdmin}
                    setMaintenancePlanReport={setMaintenancePlanReport}
                  />
                </StyledMaintenanceParametersForm>
              );
            }
            return <Loader />;
          })()}
        </main>
      </LoggedBackLayout>
    </ThemeProvider>
  );
};

export default MaintenancePlanDetailsPage;
