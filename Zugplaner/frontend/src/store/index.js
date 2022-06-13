import { createStore, applyMiddleware } from 'redux';
import { createEpicMiddleware } from 'redux-observable';
import { composeWithDevTools } from '@redux-devtools/extension';
import { rootReducer, rootEpic } from './root';

const configureStore = () => {
  const epicMiddleware = createEpicMiddleware();
  const store = createStore(
    rootReducer,
    { photosIds: [null, null, null, null, null] },
    composeWithDevTools(applyMiddleware(epicMiddleware)),
  );
  epicMiddleware.run(rootEpic);
  return store;
};

export default configureStore;
