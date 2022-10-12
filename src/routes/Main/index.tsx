import React from 'react';
import {
  createStackNavigator,
  StackCardInterpolationProps,
} from '@react-navigation/stack';
import {TOP, LOGIN, HOME, RECIPE, LOADING} from '../../constants/path';
import {Top, Login, Home, Recipe} from '../../components/pages';
import {Loading} from '../../components/pages/utility';
import * as UiContext from '../../contexts/ui';

const Stack = createStackNavigator();
const forFade = ({current}: StackCardInterpolationProps) => ({
  cardStyle: {
    opacity: current.progress,
  },
});

function switchingAuthStatus(status: UiContext.Status) {
  switch (status) {
    case UiContext.Status.UN_AUTHORIZED:
      return <Stack.Screen name={TOP} component={Top} />;
    case UiContext.Status.AUTHORIZED:
      return <Stack.Screen name={HOME} component={Home} />;
    case UiContext.Status.FIRST_OPEN:
    default:
      return <Stack.Screen name={TOP} component={Top} />;
  }
}

function AuthWithRoutes() {
  const uiContext = React.useContext(UiContext.Context);
  return (
    <Stack.Navigator
      initialRouteName={LOADING}
      screenOptions={{
        headerShown: true,
        cardStyleInterpolator: forFade,
        headerTitleAlign: 'center',
      }}>
      {uiContext.applicationState !== UiContext.Status.LOADING ? (
        switchingAuthStatus(uiContext.applicationState)
      ) : (
        <Stack.Screen name={LOADING} component={Loading} />
      )}
    </Stack.Navigator>
  );
}

export default AuthWithRoutes;
