import React from 'react';
import {createStackNavigator} from '@react-navigation/stack';
import {CALENDAR} from '../../constants/path';
import {Calendar} from '../../components/pages/sidemenu';

const Stack = createStackNavigator();
function CalendarNavigator() {
  return (
    <Stack.Navigator
      initialRouteName={CALENDAR}
      screenOptions={{
        headerTitleAlign: 'center',
      }}>
      <Stack.Screen name={CALENDAR} component={Calendar} />
    </Stack.Navigator>
  );
}

export default CalendarNavigator;
