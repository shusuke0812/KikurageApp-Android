import 'package:flutter_test/flutter_test.dart';
import 'package:kikurage_bluetooth/kikurage_bluetooth.dart';
import 'package:kikurage_bluetooth/kikurage_bluetooth_platform_interface.dart';
import 'package:kikurage_bluetooth/kikurage_bluetooth_method_channel.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';

class MockKikurageBluetoothPlatform
    with MockPlatformInterfaceMixin
    implements KikurageBluetoothPlatform {

  @override
  Future<String?> getPlatformVersion() => Future.value('42');
}

void main() {
  final KikurageBluetoothPlatform initialPlatform = KikurageBluetoothPlatform.instance;

  test('$MethodChannelKikurageBluetooth is the default instance', () {
    expect(initialPlatform, isInstanceOf<MethodChannelKikurageBluetooth>());
  });

  test('getPlatformVersion', () async {
    KikurageBluetooth kikurageBluetoothPlugin = KikurageBluetooth();
    MockKikurageBluetoothPlatform fakePlatform = MockKikurageBluetoothPlatform();
    KikurageBluetoothPlatform.instance = fakePlatform;

    expect(await kikurageBluetoothPlugin.getPlatformVersion(), '42');
  });
}
