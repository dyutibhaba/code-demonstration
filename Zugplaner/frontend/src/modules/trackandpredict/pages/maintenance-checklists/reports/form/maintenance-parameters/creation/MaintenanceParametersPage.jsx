import { askForMaintenancePlanCreation } from '../../../../../../store/checklist/actions/checklistActions';
import ReportCreationPage from '../../common/ReportCreationPage';
import MaintenanceParametersForm from './MaintenanceParametersForm';
import MaintenanceParameters from '../../../../../../models/MaintenanceParameters';

const routeExtension = 'maintenance-parameters';
const pageHeaderTitleParam = 'maintenance.maintenance-parameters.form.title';

export default ReportCreationPage(
  MaintenanceParametersForm,
  MaintenanceParameters.empty(),
  routeExtension,
  pageHeaderTitleParam,
  askForMaintenancePlanCreation,
);
