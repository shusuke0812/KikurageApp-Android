import React from 'react';
import {useNavigation} from '@react-navigation/native';
import {View, StyleSheet} from 'react-native';
import {LOGIN, SIGN_UP} from '../../../constants/path';
import {PrimaryButton, SecondaryButton, Logo} from '../../atoms';
import logoImage from '../../../../assets/kikurage_device.jpg';
import {BUTTON_WIDTH} from '../../../constants/component-size';

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'flex-start',
    alignItems: 'center',
  },
  button: {
    margin: 20,
    width: BUTTON_WIDTH,
  },
});

export default function Top() {
  const navigation = useNavigation();
  return (
    <View style={styles.container}>
      <Logo image={logoImage} />
      <PrimaryButton
        onPress={() => navigation.navigate(LOGIN)}
        style={styles.button}
        titleLabel="Login"
      />
      <SecondaryButton
        onPress={() => navigation.navigate(SIGN_UP)}
        style={styles.button}
        titleLabel="SignUp"
      />
    </View>
  );
}
