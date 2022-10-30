import {assertIsDefined} from '../../lib/assert';

export interface Model {
  readonly temperature: number;
  readonly humidity: number;
  readonly message: string;
  readonly typeString: string;
  readonly advice: string;
}

export function factory(kikurageState: Model): Model {
  assertIsDefined(kikurageState.temperature);

  return {
    temperature: kikurageState.temperature,
    humidity: kikurageState.humidity,
    message: kikurageState.message,
    typeString: kikurageState.typeString,
    advice: kikurageState.advice,
  };
}

enum StateType {
  NORMAL = 'normal',
  WET = 'wet',
  DRY = 'dry',
}

export function type(kikurageState: Model): StateType {
  const typeString = kikurageState.typeString;
  if (typeString === StateType.NORMAL) {
    return StateType.NORMAL;
  } else if (typeString === StateType.WET) {
    return StateType.WET;
  } else {
    return StateType.DRY;
  }
}
