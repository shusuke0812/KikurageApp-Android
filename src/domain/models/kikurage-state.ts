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
