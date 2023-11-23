
import 'kikurage_bluetooth_platform_interface.dart';

class KikurageBluetooth {
  Future<String?> getPlatformVersion() {
    return KikurageBluetoothPlatform.instance.getPlatformVersion();
  }
}
