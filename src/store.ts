import {applyMiddleware, legacy_createStore as create} from 'redux'; // TODO: replace deperecated `legacy_createStore`
import thunk from 'redux-thunk';

import appReducer, {createInitialState} from './modules';

export function createStore() {
  return create(appReducer, createInitialState(), applyMiddleware(thunk));
}

export default createStore();
