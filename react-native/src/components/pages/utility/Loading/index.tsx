import React from 'react';
import {View, Text, StyleSheet, TouchableOpacity} from 'react-native';
import * as UiContext from '../../../../contexts/ui';

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
});

interface Props {
  state: UiContext.Status;
}

function ChangeStateButton(props: Props) {
  const {setApplicationState} = React.useContext(UiContext.Context);
  const {state} = props;
  return (
    <TouchableOpacity onPress={() => setApplicationState(state)}>
      <Text>change state to {state}</Text>
    </TouchableOpacity>
  );
}

export default function () {
  return (
    <View style={styles.container}>
      <ChangeStateButton state={UiContext.Status.AUTHORIZED} />
      <ChangeStateButton state={UiContext.Status.UN_AUTHORIZED} />
      <ChangeStateButton state={UiContext.Status.FIRST_OPEN} />
    </View>
  );
}
