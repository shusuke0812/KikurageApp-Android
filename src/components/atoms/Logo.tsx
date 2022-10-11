import React from 'react';
import {Image, ImageSourcePropType, StyleSheet, ImageStyle} from 'react-native';
import {LOGO_WIDTH} from '../../constants/component-size';

const styles = StyleSheet.create({
  image: {
    width: LOGO_WIDTH,
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
