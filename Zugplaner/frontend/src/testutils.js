import React from 'react';
import { IntlProvider } from 'react-intl';
import { Provider } from 'react-redux';
import { MemoryRouter, Route, StaticRouter } from 'react-router';
import renderer from 'react-test-renderer';
import { createStore } from 'redux';
import { ThemeProvider } from 'styled-components';

import testStrings from './translations/testStrings';
import './modules/common/components/navigation/__mocks__/SideNavigation';
import theme from './assets/themes/theme';

function renderWithState(initialState = {}) {
  return (toTest) => {
    const json = renderer.create(
      <ThemeProvider theme={theme}>
        <IntlProvider locale="en" messages={testStrings}>
          <Provider
            store={createStore(() => initialState, initialState)}
          >
            <StaticRouter>
              {toTest}
            </StaticRouter>
          </Provider>
        </IntlProvider>
      </ThemeProvider>,
    ).toJSON();
    return json;
  };
}

export function renderWithStateAndUrl(initialState = {}, url = { base: '/test', paramName: 'testId', paramValue: '1234' }) {
  return (toTest) => {
    const json = renderer.create(
      <ThemeProvider theme={theme}>
        <IntlProvider locale="en" messages={testStrings}>
          <Provider
            store={createStore(() => initialState, initialState)}
          >
            <MemoryRouter initialEntries={[`${url.base}/${url.paramValue}`]}>
              <Route path={`${url.base}/:${url.paramName}`}>
                {toTest}
              </Route>
            </MemoryRouter>
          </Provider>
        </IntlProvider>
      </ThemeProvider>,
    ).toJSON();
    return json;
  };
}

export default renderWithState;
