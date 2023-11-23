import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'kikurage_bluetooth_method_channel.dart';

abstract class KikurageBluetoothPlatform extends PlatformInterface {
  /// Constructs a KikurageBluetoothPlatform.
  KikurageBluetoothPlatform() : super(token: _token);

  static final Object _token = Object();

  static KikurageBluetoothPlatform _instance = MethodChannelKikurageBluetooth();

  /// The default instance of [KikurageBluetoothPlatform] to use.
  ///
  /// Defaults to [MethodChannelKikurageBluetooth].
  static KikurageBluetoothPlatform get instance => _instance;

  /// Platform-specific implementations should set this with their own
  /// platform-specific class that extends [KikurageBluetoothPlatform] when
  /// they register themselves.
  static set instance(KikurageBluetoothPlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  Future<String?> getPlatformVersion() {
    throw UnimplementedError('platformVersion() has not been implemented.');
  }
}
