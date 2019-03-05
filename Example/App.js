/**
 * Example usage of react-native-timer-picker
 */

import React, { Component } from "react";
import { Button, StyleSheet, Text, View } from "react-native";
import TimerPickerModule from "react-native-timer-picker";

const onButton1Press = () => {
  TimerPickerModule.showNumberPicker({
    //title: "High Intensity Duration",
    //picker1Value: "07",
    //picker2Value: "05",
    //picker1Interval: "1",
    //picker2Interval: "2",
    //theme: "1",
    //loop: "false"
  }).then(id => {
    // id is the index of the chosen item, or -1 if the user cancelled.
    alert("Selected: " + id);
  });
};
const onButton2Press = () => {
  TimerPickerModule.showNumberPicker({
    title: "High Intensity Duration",
    //picker1Value: "07",
    //picker2Value: "05",
    picker1Interval: "2",
    picker2Interval: "3",
    theme: "2",
    loop: "true"
  }).then(id => {
    // id is the index of the chosen item, or -1 if the user cancelled.
    alert("Selected: " + id);
  });
};
const onButton3Press = () => {
  TimerPickerModule.showNumberPicker({
    title: "Some title for Example",
    picker1Value: "07",
    picker2Value: "15",
    picker1Interval: "1",
    picker2Interval: "2",
    theme: "3",
    loop: "false"
  }).then(id => {
    // id is the index of the chosen item, or -1 if the user cancelled.
    alert("Selected: " + id);
  });
};
const onButton4Press = () => {
  TimerPickerModule.showNumberPicker({
    title: "Another Title",
    //picker1Value: "07",
    //picker2Value: "05",
    picker1Interval: "1",
    picker2Interval: "2",
    theme: "4",
    loop: "false"
  }).then(id => {
    // id is the index of the chosen item, or -1 if the user cancelled.
    alert("Selected: " + id);
  });
};

export default class App extends Component {
  render() {
    return (
      <View style={styles.container}>
        <Text style={styles.welcome}>
          Welcome to the react-native-timer-picker example app!
        </Text>
        <View style={{ paddingBottom: 20 }}>
          <Button title="Click for example 1" onPress={onButton1Press} />
        </View>
        <View style={{ paddingBottom: 20 }}>
          <Button title="Click for example 2" onPress={onButton2Press} />
        </View>
        <View style={{ paddingBottom: 20 }}>
          <Button title="Click for example 3" onPress={onButton3Press} />
        </View>
        <View style={{ paddingBottom: 20 }}>
          <Button title="Click for example 4" onPress={onButton4Press} />
        </View>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: "center",
    alignItems: "center",
    backgroundColor: "#F5FCFF"
  },
  welcome: {
    fontSize: 20,
    textAlign: "center",
    margin: 10
  },
  instructions: {
    textAlign: "center",
    color: "#333333",
    marginBottom: 5
  }
});
