import React from 'react';
import {createStackNavigator} from '@react-navigation/stack';
import {TOP, LOGIN, SIGN_UP} from '../../constants/path';
import {Top, Login, SignUp} from '../../components/pages';

const Stack = createStackNavigator();
function TopNavigator() {
  return (
    <Stack.Navigator
      initialRouteName={TOP}
      screenOptions={{
        headerShown: false,
      }}>
      <Stack.Screen name={TOP} component={Top} />
      <Stack.Screen name={LOGIN} component={Login} />
      <Stack.Screen name={SIGN_UP} component={SignUp} />
    </Stack.Navigator>
  );
}

export default TopNavigator;
