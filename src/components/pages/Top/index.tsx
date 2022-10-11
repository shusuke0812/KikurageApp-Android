import React from 'react';
import {useNavigation} from '@react-navigation/native';
import {View, StyleSheet} from 'react-native';
import {PrimaryButton, SecondaryButton} from '../../atoms';

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
