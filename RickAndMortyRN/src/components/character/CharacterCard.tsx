import React from "react";
import { Image, Text, View } from "react-native";
import { CharacterInterface } from "../..";
import CharacterCardStylesheet from "./styles/CharacterCard";

interface Props {
  character: CharacterInterface;
}

export default function CharacterCard(props: Props) {
  return (
    <View style={CharacterCardStylesheet.Container}>
      <Image
        style={CharacterCardStylesheet.Image}
        source={{ uri: props.character.image }}
        onError={(err) => console.log(err)}
        testID="CharacterImage"
      />
      <View style={CharacterCardStylesheet.TextContainer}>
        <Text style={CharacterCardStylesheet.Text} testID="CharacterName">
          {props.character.name}
        </Text>
      </View>
    </View>
  );
}
