import React from 'react';
import {Image, ImageSourcePropType, StyleSheet, ImageStyle} from 'react-native';
import {width} from '../../lib/window';

const edgeNumber = 2;
const ratio = 3;
const imageRatio = edgeNumber / ratio;

const styles = StyleSheet.create({
  image: {
    width: width * imageRatio,
    flex: 1,
    resizeMode: 'contain',
  },
});

interface Props {
  image?: ImageSourcePropType;
  style?: ImageStyle | ImageStyle[];
}

export default function Logo(props: Props) {
  const {image, style} = props;
  return (
    <Image
      source={image}
      resizeMode={styles.image.resizeMode}
      style={[styles.image, style]}
    />
  );
}
