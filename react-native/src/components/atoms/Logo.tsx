import React from 'react';
import {Image, ImageSourcePropType, StyleSheet, ImageStyle} from 'react-native';
import {LOGO_WIDTH} from '../../constants/component-size';

const styles = StyleSheet.create({
  image: {
    width: LOGO_WIDTH,
    height: LOGO_WIDTH * 0.6,
    margin: 30,
    borderRadius: 18,
  },
});

interface Props {
  image?: ImageSourcePropType;
  style?: ImageStyle | ImageStyle[];
}

export default function Logo(props: Props) {
  const {image, style} = props;
  return <Image source={image} style={[styles.image, style]} />;
}
