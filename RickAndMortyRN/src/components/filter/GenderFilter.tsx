import React from "react";
import { View } from "react-native";
import { Picker } from "@react-native-picker/picker";
import { FilterCharacter, Gender } from "../..";
import FilterStylesheet from "./styles/Filter";

interface Props {
  filter: FilterCharacter;
  setGender: (newGender: Gender) => void;
}

export default function GenderFilter(props: Props) {
  return (
    <View style={FilterStylesheet.View}>
      <Picker
        style={FilterStylesheet.Picker}
        selectedValue={props.filter.gender}
        onValueChange={(value: Gender) => props.setGender(value)}
        testID="Picker"
      >
        <Picker.Item label="ALL" value="" />
        {Object.keys(Gender).map((gender) => (
          <Picker.Item key={gender} label={gender} value={gender} />
        ))}
      </Picker>
    </View>
  );
}
