import {AppState} from '../modules';
import {createSelector} from 'reselect';

//import * as Domain from '../domain/models';

function selectKikurageState(state: AppState) {
  return state.kikurageState;
}

export const getKikurageState = createSelector(
  selectKikurageState,
  kikurageState =>
    Object.values(kikurageState).map(_kikurageState => ({
      temperature: _kikurageState.temperature,
      humidity: _kikurageState.humidity,
      message: _kikurageState.message,
      typeString: _kikurageState.typeString,
      advice: _kikurageState.advice,
    })),
);
