import React from 'react';
import {createStackNavigator} from '@react-navigation/stack';
import {HOME, CULTIVATTION, RECIPE, COMMUNICATION} from '../../constants/path';
import {Home, Cultivation, Recipe, Communication} from '../../components/pages';
import {HeaderLeft} from '../Header';

const Stack = createStackNavigator();
function HomeNavigator() {
  return (
    <Stack.Navigator
      initialRouteName={HOME}
      screenOptions={{
        headerShown: true,
        headerTitleAlign: 'center',
      }}>
      <Stack.Screen
        name={HOME}
        component={Home}
        options={{
          headerLeft: () => <HeaderLeft />,
        }}
      />
      <Stack.Screen name={CULTIVATTION} component={Cultivation} />
      <Stack.Screen name={RECIPE} component={Recipe} />
      <Stack.Screen name={COMMUNICATION} component={Communication} />
    </Stack.Navigator>
  );
}

export default HomeNavigator;
