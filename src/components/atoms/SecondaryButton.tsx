import React from 'react';
import {StyleSheet, Text, TextStyle, ViewStyle} from 'react-native';
import {Button as PaperButton} from 'react-native-paper';
import {COLOR} from '../../constants/theme';

const styles = StyleSheet.create({
  text: {
    fontSize: 17,
    fontWeight: '700',
    color: 'black',
  },
});

interface Props {
  onPress: () => void;
  style?: ViewStyle | ViewStyle[];
  textStyle?: TextStyle | TextStyle[];
  titleLabel: string;
  backgroundColor?: string;
  disabled?: boolean;
  disabledColor?: string;
  icon?: string;
}

export default function SecondaryButton(props: Props) {
  const {
    onPress,
    style,
    textStyle,
    titleLabel,
    backgroundColor = COLOR.WHITE,
    disabled,
    disabledColor = COLOR.DISABLED,
    icon,
  } = props;

  return (
    <PaperButton
      mode="contained"
      onPress={onPress}
      style={style}
      disabled={disabled}
      contentStyle={{
        backgroundColor: disabled ? disabledColor : backgroundColor,
      }}
      icon={icon}>
      {titleLabel && <Text style={[styles.text, textStyle]}>{titleLabel}</Text>}
    </PaperButton>
  );
}
