import React from "react";
import { StatusBar } from "expo-status-bar";
import { ApolloClient, ApolloProvider, InMemoryCache } from "@apollo/client";
import { StyleSheet, Text, View } from "react-native";
import CharacterList from "./src/components/character/CharacterList";
import Header from "./src/components/utils/Header";

const client = new ApolloClient({
  uri: "https://rickandmortyapi.com/graphql",
  cache: new InMemoryCache(),
});

export default function App() {
  return (
    <ApolloProvider client={client}>
      <View style={styles.container}>
        <StatusBar style="light" />
        <Header />
        <View style={styles.body}>
          <CharacterList />
        </View>
      </View>
    </ApolloProvider>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: "center",
    justifyContent: "center",
  },
  body: {
    flex: 1,
    margin: 0,
    width: "100%",
    height: "100%",
    backgroundColor: "#222222",
    alignItems: "center",
    justifyContent: "center",
  },
});
