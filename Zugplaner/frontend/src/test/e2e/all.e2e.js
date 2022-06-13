import { isEqual } from 'date-fns';
import Puppet, {
  access_checklist_maintenance_tab_from_track_predict_map,
  access_damages_tab_checklist_tab,
  createCompany,
  deleteCompany,
  login,
  select_start_of_shift_form,
  select_end_of_shift_form,
  select_damage_report_form,
  fill_start_of_shift_checklist_with_no_for_motoroil_and_yes_for_others,
  fill_end_of_shift_checklist,
  validate_form,
  click_back_and_cancel_form,
  click_on_garbage_icon,
  validate_report_deletion_on_modal,
  fill_damage_report,
} from './helpers';

const waitingTime = 120000;
const puppet = new Puppet();
let initialNumberOfCompanies;
let updatedNumberOfCompanies;
const xPathAdminOld = "//*[@class='content-container']";
const xPathCompanies = "//*[@class='companies']";
const xPathCompanyCard = "//*[@class='content-wrapper']";

const when_a_company_is_added = async () => {
  await puppet.page.waitForTimeout(1000);
  await puppet.goTo('/admin/companies');
  await Promise.race([
    puppet.page.waitForXPath(xPathCompanies),
  ]);
  initialNumberOfCompanies = (await puppet.page.$x(xPathCompanyCard)).length;
  await puppet.goTo('/admin/old');
  await Promise.race([
    puppet.page.waitForXPath(xPathAdminOld),
  ]);
  await createCompany(puppet);
  await Promise.race([
    puppet.page.waitForXPath(xPathCompanyCard),
  ]);
  updatedNumberOfCompanies = (await puppet.page.$x(xPathCompanyCard)).length;
  console.log('Company added'); // NOSONAR
};

const then_the_company_is_added_to_the_list = async () => {
  await puppet.page.waitForTimeout(3000);
  expect(updatedNumberOfCompanies).toEqual(initialNumberOfCompanies + 1);
};

const and_she_should_be_able_to_delete_the_company = async () => {
  await deleteCompany(puppet);
  await puppet.page.waitForXPath(xPathCompanyCard);
  updatedNumberOfCompanies = (await puppet.page.$x(xPathCompanyCard)).length;
  expect(updatedNumberOfCompanies).toEqual(initialNumberOfCompanies);
  console.log('Deleted company'); // NOSONAR
};

let initialNumberOfChecklists;
let updatedNumberOfChecklists;
const xPathDetailsButton = "//*[@class='checklistModel-cell ']";
const given_user_accesses_maintenance_checklist_tab = async () => {
  await puppet.page.waitForTimeout(1000);
  await access_checklist_maintenance_tab_from_track_predict_map(puppet);
  await puppet.page.waitForTimeout(2000);
  initialNumberOfChecklists = (await puppet.page.$x(xPathDetailsButton)).length;
  console.log('Checklist tab reached'); // NOSONAR
};

let initialNumberOfDamageReports;
let updatedNumberOfDamageReports;
const xPathLastUpdate = "td.lastupdate-cell";
const given_user_accesses_maintenance_damages_tab = async () => {
  await puppet.page.waitForTimeout(1000);
  await access_checklist_maintenance_tab_from_track_predict_map(puppet);
  await puppet.page.waitForTimeout(1000);
  await access_damages_tab_checklist_tab(puppet);
  initialNumberOfDamageReports = (await puppet.page.$$(xPathLastUpdate)).length;
  console.log('Damages tab reached'); // NOSONAR
};

const randomLiters = Math.round(Math.random() * 100);
const when_user_fills_a_start_of_shift_report_and_validates = async () => {
await select_start_of_shift_form(puppet);
await fill_start_of_shift_checklist_with_no_for_motoroil_and_yes_for_others(puppet, randomLiters);
await puppet.page.waitForTimeout(1000);
await validate_form(puppet);
await puppet.page.waitForTimeout(2000);
updatedNumberOfChecklists = (await puppet.page.$x(xPathDetailsButton)).length;
console.log('Start of shift form validated'); // NOSONAR
};

const randomKilometers = Math.round(Math.random() * 100);
const when_user_fills_an_end_of_shift_report_and_validates = async () => {
  await select_end_of_shift_form(puppet);
  await fill_end_of_shift_checklist(puppet, randomKilometers);
  await puppet.page.waitForTimeout(1000);
  await validate_form(puppet);
  await puppet.page.waitForTimeout(2000);
  updatedNumberOfChecklists = (await puppet.page.$x(xPathDetailsButton)).length;
  console.log('End of shift form validated'); // NOSONAR
};

const when_user_fills_a_damage_report_and_validates = async () => {
  await select_damage_report_form(puppet);
  await fill_damage_report(puppet);
  await puppet.page.waitForTimeout(1000);
  await validate_form(puppet);
  await puppet.page.waitForTimeout(2000);
  updatedNumberOfDamageReports = (await puppet.page.$$(xPathLastUpdate)).length;
  console.log('Damage report form validated'); // NOSONAR
};

const when_user_creates_a_start_of_shift_form_and_clicks_back_to_cancel = async () => {
  await select_start_of_shift_form(puppet);
  await puppet.page.waitForTimeout(2000);
  await click_back_and_cancel_form(puppet);
  await puppet.page.waitForTimeout(2000);
  updatedNumberOfChecklists = (await puppet.page.$x(xPathDetailsButton)).length;
  console.log('Start of shift form cancelled'); // NOSONAR
};

const when_user_creates_a_end_of_shift_form_and_clicks_back_to_cancel = async () => {
  await select_end_of_shift_form(puppet);
  await puppet.page.waitForTimeout(2000);
  await click_back_and_cancel_form(puppet);
  await puppet.page.waitForTimeout(2000);
  updatedNumberOfChecklists = (await puppet.page.$x(xPathDetailsButton)).length;
  console.log('End of shift form cancelled'); // NOSONAR
};

const when_user_creates_a_damage_report_and_clicks_back_to_cancel = async () => {
  await select_damage_report_form(puppet);
  await puppet.page.waitForTimeout(2000);
  await click_back_and_cancel_form(puppet);
  await puppet.page.waitForTimeout(2000);
  updatedNumberOfDamageReports = (await puppet.page.$$(xPathLastUpdate)).length;
  console.log('Damage report form cancelled'); // NOSONAR
};

const then_the_user_is_redirected_and_the_number_of_checklists_has_been_updated = async () => {
  await puppet.page.reload({ waitUntil: ["networkidle0", "domcontentloaded"] });
  expect(updatedNumberOfChecklists).toEqual(initialNumberOfChecklists + 1);
};

const then_the_user_is_redirected_and_the_number_of_checklists_is_the_same_as_before = async () => {
  await puppet.page.waitForTimeout(2000);
  expect(updatedNumberOfChecklists).toEqual(initialNumberOfChecklists);
};

const then_the_user_is_redirected_and_the_number_of_damage_reports_has_been_updated = async () => {
  await puppet.page.waitForTimeout(2000);
  expect(updatedNumberOfDamageReports).toEqual(initialNumberOfDamageReports + 1);
};

const then_the_user_is_redirected_and_the_number_of_damage_reports_is_the_same_as_before = async () => {
  await puppet.page.waitForTimeout(2000);
  expect(updatedNumberOfDamageReports).toEqual(initialNumberOfDamageReports);
};

const and_no_form_creation_has_failed = async () => {
  const lastUpdatedValues = await puppet.page.evaluate(
    () => Array.from(
      document.querySelectorAll('tbody > tr > td:last-child'),
      element => element.textContent.trim()
  ));
  const creationFailed = lastUpdatedValues.map(item => new Date(item)).some(date => isEqual(date, new Date('1970-01-01')));
  expect(creationFailed).toEqual(false);
  console.log('No creation errors'); // NOSONAR
};

const and_the_motoroil_liter_displayed_corresponds_to_the_input = async () => {
  const latestMotorOilValue = await puppet.page.evaluate(
    () => Array.from(
      document.querySelectorAll('tbody > tr:nth-child(1) > .motorolstand-cell'),
      element => element.textContent.trim()
  ));
  expect(latestMotorOilValue[0]).toEqual((randomLiters.toString()));
  console.log('Motor Oil input is displayed and correct'); // NOSONAR
};

const and_the_kilometers_displayed_correspond_to_the_input = async () => {
  const kilometersValues = await puppet.page.evaluate(
    () => Array.from(
      document.querySelectorAll('tbody > tr:nth-child(1) > .kilometer-cell'),
      element => element.textContent.trim()
  ));
  expect(kilometersValues[0]).toEqual(randomKilometers.toString());
  console.log('Kilometers input is displayed and correct'); // NOSONAR
};

const and_the_checklist_is_deleted = async () => {
  await click_on_garbage_icon(puppet);
  await validate_report_deletion_on_modal(puppet);
  console.log('Checklist has been deleted'); // NOSONAR
};

const and_the_damage_report_is_deleted = async () => {
  await click_on_garbage_icon(puppet);
  await validate_report_deletion_on_modal(puppet);
  console.log('Damage report has been deleted'); // NOSONAR
};

describe('A terminal operator using track and predict', () => {
  beforeAll(async (done) => {
    await puppet.init();
    await login(puppet);
    done();
  }, waitingTime);

  afterAll(async () => {
    await puppet.cleanup();
    await puppet.closeBrowser();
  }, waitingTime);

  it('scenario-1', async () => {
    await when_a_company_is_added();
    await then_the_company_is_added_to_the_list();
    await and_she_should_be_able_to_delete_the_company();
  }, waitingTime);

  it('scenario-2', async () => {
    // Start-of-shift
    await given_user_accesses_maintenance_checklist_tab();
    await when_user_creates_a_start_of_shift_form_and_clicks_back_to_cancel();
    await then_the_user_is_redirected_and_the_number_of_checklists_is_the_same_as_before();
    await when_user_fills_a_start_of_shift_report_and_validates();
    await then_the_user_is_redirected_and_the_number_of_checklists_has_been_updated();
    await and_no_form_creation_has_failed();
    await and_the_motoroil_liter_displayed_corresponds_to_the_input();
    await and_the_checklist_is_deleted();
  }, waitingTime);

  it('scenario-3', async () => {
    // End-of-shift
    await given_user_accesses_maintenance_checklist_tab();
    await when_user_creates_a_end_of_shift_form_and_clicks_back_to_cancel();
    await then_the_user_is_redirected_and_the_number_of_checklists_is_the_same_as_before();
    await when_user_fills_an_end_of_shift_report_and_validates();
    await then_the_user_is_redirected_and_the_number_of_checklists_has_been_updated();
    await and_no_form_creation_has_failed();
    await and_the_kilometers_displayed_correspond_to_the_input();
    await and_the_checklist_is_deleted();
  }, waitingTime);

  it('scenario-4', async () => {
    // Damage Report
    await given_user_accesses_maintenance_damages_tab();
    await when_user_creates_a_damage_report_and_clicks_back_to_cancel();
    await then_the_user_is_redirected_and_the_number_of_damage_reports_is_the_same_as_before();
    await when_user_fills_a_damage_report_and_validates();
    await then_the_user_is_redirected_and_the_number_of_damage_reports_has_been_updated();
    await and_the_damage_report_is_deleted();
  }, waitingTime);
});
