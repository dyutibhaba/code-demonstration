import React from 'react';
import ReactDOM from 'react-dom';
import { Provider } from 'react-redux';
import { ReactKeycloakProvider } from '@react-keycloak/web';
import { FeatureFlagsProvider } from './components/FeatureFlagsProvider';
import keycloak, { initConfig } from './configs/config_keycloak';
import './index.css';
import App from './App';
import { AuthProvider } from './modules/common/components/authentication/AuthProvider';

import * as serviceWorker from './serviceWorker';
import configureStore from './store';
import DEVLOGGER from './configs/config_log';
import { Loader } from './modules/common/components/miscellaneous/Loader';

const tokenLogger = (tokens) => {
  DEVLOGGER.log('onKeycloakTokens', tokens);
};

const handleOnEvent = async (event, error) => {
  DEVLOGGER.log(`ENTER event = ${event} error = ${error}`);
};

ReactDOM.render(
  <React.StrictMode>
    <ReactKeycloakProvider // todo think about moving inside the authprovider
      authClient={keycloak}
      onEvent={handleOnEvent}
      onTokens={tokenLogger}
      initOptions={initConfig}
      LoadingComponent={<Loader />}
    >
      <AuthProvider>
        <Provider store={configureStore()}>
          <FeatureFlagsProvider>
            <App />
          </FeatureFlagsProvider>
        </Provider>
      </AuthProvider>
    </ReactKeycloakProvider>
  </React.StrictMode>,
  document.getElementById('root'),
);

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
