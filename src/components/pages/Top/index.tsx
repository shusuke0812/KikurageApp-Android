import React from 'react';
import {useNavigation} from '@react-navigation/native';
import {View, StyleSheet} from 'react-native';
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
  const {navigate} = useNavigation();
  return (
    <View style={styles.container}>
      <Logo image={logoImage} />
      <PrimaryButton
        onPress={() => navigate()}
        style={styles.button}
        titleLabel="Login"
      />
      <SecondaryButton
        onPress={() => navigate()}
        style={styles.button}
        titleLabel="SignUp"
      />
    </View>
  );
}
