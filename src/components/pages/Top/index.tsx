import React from 'react';
import {useNavigation} from '@react-navigation/native';
import {View, StyleSheet} from 'react-native';
import {PrimaryButton, SecondaryButton, Logo} from '../../atoms';
import logoImage from '../../../../assets/kikurage_device.jpg';

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
  button: {
    marginTop: 40,
    width: 340,
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
        titleLabel="ログイン"
      />
      <SecondaryButton
        onPress={() => navigate()}
        style={styles.button}
        titleLabel="新規ユーザー登録"
      />
    </View>
  );
}
