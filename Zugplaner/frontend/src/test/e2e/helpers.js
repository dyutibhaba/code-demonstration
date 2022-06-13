import puppeteer from 'puppeteer';

require('dotenv').config();

const TARGET_URL = process.env.E2E_URL || 'http://localhost:3000';
const { E2E_ACCOUNT_USER, E2E_ACCOUNT_PASSWORD } = process.env;
const timeout = 60000;

export default class Puppet {
  async init() {
    this.browser = await puppeteer.launch({
      defaultViewport: { width: 1300, height: 800 },
      args: ['--no-sandbox', '--disable-setuid-sandbox', '--disable-dev-shm-usage'],
      headless: true,
    });
    this.context = this.browser.defaultBrowserContext();
    this.page = await this.browser.newPage();
    await this.page.goto(TARGET_URL);
  }

  async goTo(url) {
    console.log(`${TARGET_URL}${url}`)
    await this.page.goto(`${TARGET_URL}${url}`);
  }

  async cleanup() {
    await this.context.clearPermissionOverrides();
  }

  async click(selector) {
    await this.page.click(selector);
  }

  async clickXPathSelector(selector, text) {
    const clickableItems = await this.page.$x(`//*[@class='${selector}' and contains(., '${text}')]`);
    if (clickableItems.length > 0) {
      await clickableItems[0].click();
    } else {
      throw new Error(`Link not found for class ${selector} with text ${text}`);
    }
  }

  async closeBrowser() {
    await this.browser.close();
  }

  async getContent(selector) {
    return this.page.evaluate((select) => document.querySelector(select).textContent, selector);
  }

  async select(selector, value) {
    await this.page.select(selector, value);
  }

  async type(text) {
    await this.page.keyboard.type(text);
  }

  async clickOnElementWithText(element, text) {
    const [button] = await this.page.$x(`//${element}[contains(., '${text}')]`);
    if (button) {
      await button.click();
    }
  }

  /* When the component does not generate navigation, like in modals,
  it is not necessary to call waitForNavigation() */
  async waitAndClick(selector, options = { timeout }) {
    await this.page.waitForSelector(selector, options);
    await this.page.click(selector);
  }

  /* Timeout refers to the maximal amount of time the event will wait before failing.
  Setting it to 0 disables the timeout. Default is 30000 */
  async waitAndClickWithNavigation(selector, options = { timeout }) {
    await this.page.waitForSelector(selector, options);
    await Promise.all([
      this.page.waitForNavigation({ waitUntil: 'networkidle0' }),
      this.page.click(selector)]);
  }

  async waitForResponse(endpoint) {
    await this.page.on('response', (response) => {
      if (response.url().endsWith(endpoint) && response.status() === 200) {
        // console.log(`Endpoint ${endpoint} responded with 200`); // NOSONAR
      }
    });
  }

  async waitForSelector(selector, options = { timeout }) {
    await this.page.waitForSelector(selector, options);
  }
}

export const login = async (puppet) => {
  console.log('\nLogging in'); // NOSONAR
  try {
    await puppet.waitAndClickWithNavigation('button.tpz-btn-primary');
    await puppet.waitForSelector('.form-control.ltr_override');
    await puppet.type(E2E_ACCOUNT_USER);
    await Promise.all([puppet.page.waitForNavigation(), puppet.click('.row [type=submit]')]);
    await puppet.waitForSelector('[name=passwd]');
    await puppet.click('[name=passwd]');
    await puppet.page.waitForTimeout(1000);
    await puppet.type(E2E_ACCOUNT_PASSWORD);
    await Promise.all([puppet.page.waitForNavigation(), puppet.click('.row [type=submit]')]);
    await Promise.race([
      puppet.waitAndClickWithNavigation('#idBtn_Back'),
      puppet.waitForSelector('.content-container')]);
    await puppet.waitForResponse('/api/login');
    await puppet.page.waitForTimeout(500);
  } catch (err) {
    console.log('An error occured while loging in: ', err); // NOSONAR
    throw (err);
  }
};

export const createCompany = async (puppet) => {
  try {
    await puppet.waitForSelector('#name');
    await puppet.click('#name');
    await puppet.type('delete-me');
    await puppet.click('#users');
    await puppet.type(E2E_ACCOUNT_USER);
    await puppet.click('#submit-user-group');
    await puppet.page.waitForTimeout(5000);
    await puppet.waitForResponse('/api/usergroups');
    await puppet.goTo('/admin/companies');
    await puppet.page.waitForTimeout(5000);
    await puppet.waitForResponse('/api/usergroups');
  } catch (err) {
    console.log('An error occured while creating a new project: ', err); // NOSONAR
    throw (err);
  }
};

export const deleteCompany = async (puppet) => {
  try {
    await puppet.click('#delete-me');
    await puppet.click('#confirm-deletion-input');
    await puppet.type('delete-me');
    await puppet.page.waitForTimeout(1000);
    await puppet.waitAndClick('button.tpz-btn.tpz-btn-primary.confirm-deletion-button');
    await puppet.page.waitForTimeout(1000);
  } catch (err) {
    console.log('An error occured while deleting item: ', err); // NOSONAR
    throw (err);
  }
};

export const access_checklist_maintenance_tab_from_track_predict_map = async (puppet) => {
  try {
    await puppet.goTo('/track-and-predict');
    await Promise.race([
      puppet.waitForSelector('td'),
    ]);
    await puppet.click('td button');
    await puppet.page.waitForTimeout(1000);
  } catch (err) {
    console.log('An error occured while accessing maintenance checklist tab: ', err); // NOSONAR
    throw (err);
  }
};

export const access_damages_tab_checklist_tab = async (puppet) => {
  try {
    await puppet.waitForSelector('nav > a:nth-child(2)');
    await puppet.click('nav > a:nth-child(2)');
  } catch (err) {
    console.log('An error occured while accessing maintenance damages tab: ', err); // NOSONAR
    throw (err);
  }
};

const getAssetId = (url) => {
  const re = /^[0-9A-F]{8}-[0-9A-F]{4}-[4][0-9A-F]{3}-[89AB][0-9A-F]{3}-[0-9A-F]{12}$/i;
  const urlAsArray = url.split('/');
  const position = urlAsArray.length - 2;
  const assetId = urlAsArray[position]
  if (!re.test(assetId)) {
    throw Error(`The param at position ${position} is not a UUID`);
  }
  return assetId;
};

export const select_start_of_shift_form = async (puppet) => {
  try {
    await puppet.page.waitForTimeout(1000);
    await puppet.waitForSelector('.add-entry-button');
    await puppet.click('.add-entry-button');
    await puppet.page.waitForTimeout(1000);
    await puppet.waitForSelector('.start-of-shift');
    await puppet.goTo(`/track-and-predict/maintenance-asset/${getAssetId(puppet.page.url())}/checklists/startofshift`);
    await puppet.waitForSelector('.title');
    console.log('Start of shift form reached');
  } catch (err) {
    console.log('An error occured while selecting start of shift option: ', err); // NOSONAR
    throw (err);
  }
}

export const select_end_of_shift_form = async (puppet) => {
  try {
    await puppet.waitForSelector('.add-entry-button');
    await puppet.click('.add-entry-button');
    await puppet.page.waitForTimeout(1000);
    await puppet.waitForSelector('.end-of-shift');
    await puppet.goTo(`/track-and-predict/maintenance-asset/${getAssetId(puppet.page.url())}/checklists/endofshift`);
    await puppet.page.waitForTimeout(1000);
  } catch (err) {
    console.log('An error occured while selecting end of shift option: ', err); // NOSONAR
    throw (err);
  }
}

export const select_damage_report_form = async (puppet) => {
  try {
    await puppet.waitForSelector('.add-entry-button');
    await puppet.click('.add-entry-button');
    await puppet.page.waitForTimeout(1000);
  } catch (err) {
    console.log('An error occured while selecting add new damage report: ', err); // NOSONAR
    throw (err);
  }
}

export const fill_shart_of_shift_checklist_with_yes_choices = async (puppet) => {
  try {
    await puppet.waitForSelector('#motorolstandYes');
    await puppet.click('#motorolstandYes');
    await puppet.page.waitForTimeout(500);
    await puppet.waitForSelector('#kuhlwasserstandYes');
    await puppet.click('#kuhlwasserstandYes');
    await puppet.page.waitForTimeout(500);
    await puppet.waitForSelector('#fahrwerkYes');
    await puppet.click('#fahrwerkYes');
    await puppet.page.waitForTimeout(500);
    await puppet.waitForSelector('#transmissionOilControlYes');
    await puppet.click('#transmissionOilControlYes');
    await puppet.page.waitForTimeout(500);
  } catch (err) {
    console.log('An error occured while filling start of shift: ', err); // NOSONAR
    throw (err);
  }
};

export const fill_start_of_shift_checklist_with_no_for_motoroil_and_yes_for_others = async (puppet, liters) => {
  try {
    await puppet.page.waitForTimeout(1500);
    await puppet.waitForSelector('#motorolstandNo');
    await puppet.click('#motorolstandNo');
    await puppet.page.waitForTimeout(500);
    await puppet.waitForSelector('textarea');
    await puppet.click('textarea');
    await puppet.type('Motor Oil Comment');
    await puppet.page.waitForTimeout(500);
    await puppet.waitForSelector('.number');
    await puppet.page.click('.number');
    await puppet.type(liters.toString());
    await puppet.page.waitForTimeout(500);
    await puppet.waitForSelector('#kuhlwasserstandYes');
    await puppet.click('#kuhlwasserstandYes');
    await puppet.page.waitForTimeout(500);
    await puppet.waitForSelector('#fahrwerkYes');
    await puppet.click('#fahrwerkYes');
    await puppet.page.waitForTimeout(500);
    await puppet.waitForSelector('#transmissionOilControlYes');
    await puppet.click('#transmissionOilControlYes');
    await puppet.page.waitForTimeout(500);
  } catch (err) {
    console.log('An error occured while filling start of shift: ', err); // NOSONAR
    throw (err);
  }
};

export const fill_end_of_shift_checklist = async (puppet, km) => {
  try {
    await puppet.page.waitForTimeout(1000);
    await puppet.waitForSelector('.number');
    await puppet.click('.number');
    await puppet.type(km.toString());
  } catch (err) {
    console.log('An error occured while filling start of shift: ', err); // NOSONAR
    throw (err);
  }
};

export const fill_damage_report = async (puppet) => {
  try {
    await puppet.waitForSelector('.tpz-select-value');
    await puppet.click('.tpz-select-value');
    await puppet.page.waitForTimeout(500);
    await puppet.waitForSelector('.tpz-select-option');
    await puppet.click('.tpz-select-option');
    await puppet.page.waitForTimeout(500);
    await puppet.waitForSelector('textarea');
    await puppet.click('textarea');
    await puppet.type('Damage Report Comment');
    await puppet.page.waitForTimeout(500);
  } catch (err) {
    console.log('An error occured while filling damage report: ', err); // NOSONAR
    throw (err);
  }
};

export const click_back_and_cancel_form = async (puppet) => {
  try {
    await puppet.waitForSelector('#back-button');
    await puppet.click('#back-button');
  } catch (err) {
    console.log('An error occured while cancelling a form: ', err); // NOSONAR
    throw (err);
  }
};

export const validate_form = async (puppet) => {
  try {
    await puppet.waitForSelector('.cta');
    await puppet.click('.cta');
  } catch (err) {
    console.log('An error occured while validating form: ', err); // NOSONAR
    throw (err);
  }
};

export const click_on_garbage_icon = async (puppet) => {
  try {
    await puppet.page.waitForTimeout(1000);
    await puppet.waitForSelector('#delete');
    await puppet.click('#delete');
  } catch (err) {
    console.log('An error occured while clicking on garbage icon: ', err); // NOSONAR
    throw (err);
  }
};

export const validate_report_deletion_on_modal = async (puppet) => {
  try {
    await puppet.page.waitForTimeout(1000);
    await puppet.waitForSelector('#delete-modal');
    await puppet.click('#delete-modal');
  } catch (err) {
    console.log('An error occured while validating the checklist deletion on modal: ', err); // NOSONAR
    throw (err);
  }
};
