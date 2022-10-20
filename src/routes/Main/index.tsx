import React from 'react';
import {
  createStackNavigator,
  StackCardInterpolationProps,
} from '@react-navigation/stack';
import {createDrawerNavigator} from '@react-navigation/drawer';
import {TOP, HOME, LOADING, CALENDAR} from '../../constants/path';
import Top from './Top';
import Home from './Home';
import Calendar from './Calendar';
import {Loading} from '../../components/pages/utility';
import * as UiContext from '../../contexts/ui';

const Stack = createStackNavigator();
const HomeDrawer = createDrawerNavigator();
const forFade = ({current}: StackCardInterpolationProps) => ({
  cardStyle: {
    opacity: current.progress,
  },
});

function HomeWithDrawer() {
  return (
    <HomeDrawer.Navigator
      initialRouteName={HOME}
      screenOptions={{
        headerShown: false,
      }}>
      <HomeDrawer.Screen name={HOME} component={Home} />
      <HomeDrawer.Screen name={CALENDAR} component={Calendar} />
    </HomeDrawer.Navigator>
  );
}

function switchingAuthStatus(status: UiContext.Status) {
  switch (status) {
    case UiContext.Status.UN_AUTHORIZED:
      return <Stack.Screen name={TOP} component={Top} />;
    case UiContext.Status.AUTHORIZED:
      return <Stack.Screen name={HOME} component={HomeWithDrawer} />;
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
        headerShown: false,
        cardStyleInterpolator: forFade,
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
