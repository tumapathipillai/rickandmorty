import React from "react";
import { View } from "react-native";
import { Picker } from "@react-native-picker/picker";
import { FilterCharacter, Gender } from "../..";
import { printIntrospectionSchema } from "graphql";

interface Props {
  filter: FilterCharacter;
  setGender: (newGender: Gender) => void;
}

export default function GenderFilter(props: Props) {
  return (
    <View style={{ backgroundColor: "#666666", width: "49%" }}>
      <Picker
        style={{ color: "white" }}
        selectedValue={props.filter.gender}
        onValueChange={(value: Gender) => props.setGender(value)}
      >
        <Picker.Item label="ALL" value="" />
        {Object.keys(Gender).map((gender) => (
          <Picker.Item key={gender} label={gender} value={gender} />
        ))}
      </Picker>
    </View>
  );
}
