import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'kikurage_bluetooth_platform_interface.dart';

/// An implementation of [KikurageBluetoothPlatform] that uses method channels.
class MethodChannelKikurageBluetooth extends KikurageBluetoothPlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('kikurage_bluetooth');

  @override
  Future<String?> getPlatformVersion() async {
    final version = await methodChannel.invokeMethod<String>('getPlatformVersion');
    return version;
  }
}
