import React from 'react';
import {View, Text, StyleSheet} from 'react-native';

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
});

function Index() {
  return (
    <View style={styles.container}>
      <Text>Kikurage</Text>
    </View>
  );
}

export default Index;
