import { StyleSheet } from "react-native";

export default StyleSheet.create({
  Container: {
    backgroundColor: "#aaaaaa",
    borderRadius: 10,
    height: 100,
    flexDirection: "row",
    paddingHorizontal: 10,
    marginHorizontal: 10,
    alignItems: "center",
  },
  Image: {
    height: 80,
    width: 80,
    resizeMode: "contain",
  },
  TextContainer: {
    flexDirection: "row",
    flex: 1,
    justifyContent: "center",
    marginLeft: 10,
  },
  Text: {
    textAlign: "center",
    color: "black",
    fontWeight: "bold",
    fontSize: 20,
  },
});
