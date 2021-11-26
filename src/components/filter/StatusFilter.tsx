import React from "react";
import { View } from "react-native";
import { Picker } from "@react-native-picker/picker";
import { FilterCharacter, Status } from "../..";

interface Props {
  setStatus: (newStatus: Status) => void;
  filter: FilterCharacter;
}

export default function StatusFilter(props: Props) {
  return (
    <View style={{ backgroundColor: "#666666", width: "49%" }}>
      <Picker
        style={{ color: "white" }}
        selectedValue={props.filter.status}
        onValueChange={(value: Status) => props.setStatus(value)}
      >
        <Picker.Item label="ALL" value="" />
        {Object.keys(Status).map((status) => (
          <Picker.Item key={status} label={status} value={status} />
        ))}
      </Picker>
    </View>
  );
}
