import {KikurageState} from '../domain/models';

export function createInitialState(): KikurageState.Model {
  const state: KikurageState.Model = {
    temperature: 20,
    humidity: 80,
    message: '',
    typeString: 'normal',
    advice: '',
  };
  return KikurageState.factory(state);
}

export type State = ReturnType<typeof createInitialState>;

// Action type
export const SET = 'kikurage-state/set' as const;

export function set(kikurageState: KikurageState.Model) {
  return {
    type: SET,
    payload: {
      kikurageState,
    },
  };
}

// Reducer
export type Action = Readonly<ReturnType<typeof set>>;

export default function reducer(state = createInitialState(), action: Action) {
  switch (action.type) {
    case SET:
      return action.payload.kikurageState;
    default:
      return state;
  }
}
