import React from "react";
import {
  Modal,
  Image,
  View,
  Text,
  Button,
  TouchableOpacity,
} from "react-native";
import { CharacterInterface } from "../..";
import CharacterDetailsStylesheet from "./styles/CharacterDetails";

interface Props {
  closeModal: () => void;
  character: CharacterInterface;
}

export default function CharacterDetails(props: Props) {
  return (
    <Modal transparent animationType="fade">
      <View style={CharacterDetailsStylesheet.Container}>
        <View style={CharacterDetailsStylesheet.Card}>
          <Image
            source={{ uri: props.character.image }}
            style={CharacterDetailsStylesheet.Image}
          />
          <Text style={CharacterDetailsStylesheet.Name}>
            {props.character.name}
          </Text>
          <Text style={CharacterDetailsStylesheet.Text}>
            Status : {props.character.status}
          </Text>
          <Text style={CharacterDetailsStylesheet.Text}>
            Gender : {props.character.gender}
          </Text>
          <TouchableOpacity
            onPress={props.closeModal}
            style={CharacterDetailsStylesheet.CloseButton}
          >
            <Text style={(CharacterDetailsStylesheet.Text, { color: "white" })}>
              CLOSE
            </Text>
          </TouchableOpacity>
        </View>
      </View>
    </Modal>
  );
}
