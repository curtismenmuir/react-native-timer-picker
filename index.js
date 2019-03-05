import ReactNative from "react-native";

const RNTimerPicker = ReactNative.NativeModules.TimerPickerModule;

export default {
  showNumberPicker: options => {
    return new Promise((resolve, reject) => {
      RNTimerPicker.showNumberPicker(options, resolve, reject);
    });
  }
};
