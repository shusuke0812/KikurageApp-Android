import React from 'react';
import * as UiContext from './contexts/ui';
import {View, Text, StyleSheet} from 'react-native';

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
});

function App() {
  const [applicationState, setApplicationState] = React.useState(
    UiContext.createApplicationInitialState(),
  );
  return (
    <UiContext.Context.Provider value={{applicationState, setApplicationState}}>
      <View style={styles.container}>
        <Text>Kikurage</Text>
      </View>
    </UiContext.Context.Provider>
  );
}

export default App;
