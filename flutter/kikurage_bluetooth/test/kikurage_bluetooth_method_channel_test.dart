import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:kikurage_bluetooth/kikurage_bluetooth_method_channel.dart';

void main() {
  TestWidgetsFlutterBinding.ensureInitialized();

  MethodChannelKikurageBluetooth platform = MethodChannelKikurageBluetooth();
  const MethodChannel channel = MethodChannel('kikurage_bluetooth');

  setUp(() {
    TestDefaultBinaryMessengerBinding.instance.defaultBinaryMessenger.setMockMethodCallHandler(
      channel,
      (MethodCall methodCall) async {
        return '42';
      },
    );
  });

  tearDown(() {
    TestDefaultBinaryMessengerBinding.instance.defaultBinaryMessenger.setMockMethodCallHandler(channel, null);
  });

  test('getPlatformVersion', () async {
    expect(await platform.getPlatformVersion(), '42');
  });
}
