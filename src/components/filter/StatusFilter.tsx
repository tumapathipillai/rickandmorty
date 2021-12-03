import React from "react";
import { View } from "react-native";
import { Picker } from "@react-native-picker/picker";
import { FilterCharacter, Status } from "../..";
import FilterStylesheet from "./styles/Filter";

interface Props {
  setStatus: (newStatus: Status) => void;
  filter: FilterCharacter;
}

export default function StatusFilter(props: Props) {
  return (
    <View style={FilterStylesheet.View}>
      <Picker
        style={FilterStylesheet.Picker}
        selectedValue={props.filter.status}
        onValueChange={(value: Status) => props.setStatus(value)}
        testID="Picker"
      >
        <Picker.Item label="ALL" value="" />
        {Object.keys(Status).map((status) => (
          <Picker.Item key={status} label={status} value={status} />
        ))}
      </Picker>
    </View>
  );
}
