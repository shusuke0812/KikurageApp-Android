import React from 'react';
import {useNavigation} from '@react-navigation/native';
import {DrawerActions} from '@react-navigation/routers';
import Icon from 'react-native-vector-icons/FontAwesome';
import {COLOR} from '../../constants/theme';

function HeaderLeft() {
  const {dispatch} = useNavigation();
  const onPress = React.useCallback(() => {
    dispatch(DrawerActions.openDrawer());
  }, [dispatch]);

  return (
    <Icon.Button
      name="bars"
      color={COLOR.BLACK}
      backgroundColor={COLOR.WHITE}
      onPress={onPress}
    />
  );
}

export default HeaderLeft;
