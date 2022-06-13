import React from 'react';
import { IntlProvider } from 'react-intl';
import { BrowserRouter as Router } from 'react-router-dom';
import { hotjar } from 'react-hotjar';
import ReactGA from 'react-ga';
import TagManager from 'react-gtm-module';
import { createBrowserHistory } from 'history';
import { ReactPlugin } from '@microsoft/applicationinsights-react-js';
import { ApplicationInsights } from '@microsoft/applicationinsights-web';

import './App.css';
import Login from './modules/common/pages/login/Login';
import messagesEn from './translations/en.json';
import config from './configs/config';

const messages = {
  en: messagesEn,
};

const browserHistory = createBrowserHistory({ basename: '' });
const reactPlugin = new ReactPlugin();
const appInsights = new ApplicationInsights({
  config: {
    instrumentationKey: config.appInsightsKey,
    extensions: [reactPlugin],
    extensionConfig: {
      [reactPlugin.identifier]: { history: browserHistory },
    },
  },
});
window.appInsights = appInsights.loadAppInsights();

const tagManagerArgs = {
  gtmId: config.gtmTrackingId,
};

if (process.env.REACT_APP_ENVIRONMENT === 'production') {
  hotjar.initialize(process.env.REACT_APP_HOTJAR_ID, '');
  ReactGA.initialize(config.gaTrackingId);
  ReactGA.pageview(window.location.pathname + window.location.search);
  TagManager.initialize(tagManagerArgs);
}

const App = () => (
  <Router>
    <IntlProvider locale="en" messages={messages.en}>
      <div className="App">
        <Login />
      </div>
    </IntlProvider>
  </Router>
);

export default App;
