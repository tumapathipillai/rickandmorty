import React from "react";
import { Image, View } from "react-native";
import HeaderStylesheet from "./styles/Header";

export default function Header() {
  return (
    <View style={HeaderStylesheet.Container}>
      <Image
        style={HeaderStylesheet.Image}
        source={require("../../../assets/title.png")}
      />
    </View>
  );
}
