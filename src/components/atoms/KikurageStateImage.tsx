import React from 'react';
import {Image, ImageSourcePropType, StyleSheet, ImageStyle} from 'react-native';

const styles = StyleSheet.create({
  image: {
    width: 100,
    height: 100,
    margin: 30,
    borderRadius: 18,
  },
});

interface Props {
  image?: ImageSourcePropType;
  style?: ImageStyle | ImageStyle[];
}

export default function KikurageStateImage(props: Props) {
  const {image, style} = props;
  return <Image source={image} style={[styles.image, style]} />;
}
