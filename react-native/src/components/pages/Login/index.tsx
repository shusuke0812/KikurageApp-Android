import React from 'react';
import {StyleSheet, View, TouchableWithoutFeedback} from 'react-native';
import {PrimaryButton, dismiss, TextField} from '../../atoms';
import {Context, Status} from '../../../contexts/ui';
import {useControlledComponent} from '../../../lib/hooks';

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 16,
  },
  textField: {
    marginVertical: 20,
  },
  button: {
    marginTop: 20,
  },
});

export default function Login() {
  const {setApplicationState} = React.useContext(Context);
  const mailAddress = useControlledComponent('');
  const password = useControlledComponent('');

  return (
    <TouchableWithoutFeedback onPress={dismiss}>
      <View style={styles.container}>
        <TextField
          label="email"
          value={mailAddress.value}
          onChangeText={mailAddress.onChangeText}
          style={styles.textField}
          autoCompleteType="email"
        />
        <TextField
          label="password"
          value={password.value}
          onChangeText={password.onChangeText}
          style={styles.textField}
          autoCompleteType="password"
          secureTextEntry={true}
        />
        <PrimaryButton
          onPress={() => setApplicationState(Status.AUTHORIZED)}
          style={styles.button}
          titleLabel="Login"
        />
      </View>
    </TouchableWithoutFeedback>
  );
}
