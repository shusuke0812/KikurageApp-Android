import React from 'react';
import {StyleSheet, Text, View} from 'react-native';
import {COLOR} from '../../constants/theme';

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'flex-start',
    alignItems: 'center',
  },
  text: {
    fontSize: 26,
    fontWeight: 'bold',
    color: COLOR.BLACK,
  },
  subText: {
    fontSize: 20,
    fontWeight: 'normal',
    color: COLOR.BLACK,
  },
});

interface Props {
  titleLabel: string;
  subTitleLabel: string;
}

export default function TitleHeader(props: Props) {
  const {titleLabel, subTitleLabel} = props;

  return (
    <View style={styles.container}>
      <Text style={styles.text}>{titleLabel}</Text>
      <Text style={styles.subText}>{subTitleLabel}</Text>
    </View>
  );
}
