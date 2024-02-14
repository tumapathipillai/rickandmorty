import { StyleSheet } from "react-native";

export default StyleSheet.create({
  Container: {
    position: "absolute",
    height: "100%",
    width: "100%",
    display: "flex",
    flexDirection: "row",
    justifyContent: "center",
    alignItems: "center",
    backgroundColor: "rgba(34,34,34, 0.75)",
  },
  Card: {
    width: 250,
    backgroundColor: "white",
    borderRadius: 25,
    flexDirection: "column",
    justifyContent: "flex-start",
    alignItems: "center",
  },
  Image: {
    height: 200,
    width: 200,
    marginTop: 25,
    marginBottom: 10,
  },
  Name: {
    fontSize: 25,
    fontWeight: "bold",
    textAlign: "center",
    maxWidth: 225,
  },
  Text: {
    fontSize: 15,
    fontWeight: "bold",
  },
  CloseButton: {
    backgroundColor: "#444444",
    marginVertical: 10,
    paddingVertical: 5,
    paddingHorizontal: 10,
    borderRadius: 5,
  },
});
