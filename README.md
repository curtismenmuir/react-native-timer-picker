# react-native-timer-picker

## Getting started

`$ npm install react-native-timer-picker --save`
`$ react-native link react-native-timer-picker`

## Example Usage

```javascript
import TimerPickerModule from "react-native-timer-picker";

TimerPickerModule.showNumberPicker({
  title: "Some title",
  picker1Value: "01",
  picker2Value: "15",
  picker1Interval: "1",
  picker2Interval: "2",
  theme: "1",
  loop: "true"
}).then(id => {
  // id is the index of the chosen item, or -1 if the user cancelled.
  alert("Selected: " + id);
});
```

## Props

| Property        | Description                           | Type              | Default               | Note                                                                                                                |
| --------------- | ------------------------------------- | ----------------- | --------------------- | ------------------------------------------------------------------------------------------------------------------- |
| title           | Dialog Title                          | String            | Timer Selection Title |
| picker1Interval | Interval gap of first number spinner  | number as String  | 1                     | Must be a value (as string) from "1" (gap of 1), "2" (gap of 5) or "3" (gap of 10)                                  |
| picker2Interval | Interval gap of second number spinner | number as String  | 1                     | Must be a value (as string) from "1" (gap of 1), "2" (gap of 5) or "3" (gap of 10)                                  |
| picker1Value    | Initial value of first spinner        | number as String  | 0                     | Must be a value contained in the spinner.                                                                           |
| picker2Value    | Initial value of second spinner       | number as String  | 0                     | Must be a value contained in the spinner.                                                                           |
| theme           | AlertDialog theme                     | number as String  | 1                     | "1" = THEME_HOLO_DARK, "2" = THEME_DEVICE_DEFAULT_DARK, "3" = THEME_HOLO_LIGHT and "4" = THEME_DEVICE_DEFAULT_LIGHT |
| loop            | Number Spinner loops at end           | boolean as String | false                 | Must be a bool value as String                                                                                      |
