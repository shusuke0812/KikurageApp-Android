import {combineReducers} from 'redux';

import * as KikurageState from './kikurage-state';

export function createInitialState() {
  return {
    kikurageState: KikurageState.createInitialState(),
  };
}

export type AppState = Readonly<ReturnType<typeof createInitialState>>;

export default combineReducers<AppState>({
  kikurageState: KikurageState.default,
});
