import React, { ReactElement, useEffect, useState } from "react";
import {
  ActivityIndicator,
  FlatList,
  TouchableOpacity,
  View,
  Text,
  Image,
} from "react-native";
import { useQuery } from "@apollo/client";
import GetCharacters from "../../queries/GetCharacters";
import { CharacterInterface, FilterCharacter, Gender, Status } from "../..";
import CharacterCard from "./CharacterCard";
import CharacterDetails from "./CharacterDetails";
import GenderFilter from "../filter/GenderFilter";
import StatusFilter from "../filter/StatusFilter";

export default function CharacterList() {
  const [page, setPage] = useState(1);
  const [characterDeatils, setCharacterDetails] =
    useState<CharacterInterface>();
  const [characters, setCharacters] = useState<CharacterInterface[]>([]);
  const [nextPage, setNextPage] = useState(false);
  const [filters, setFilter] = useState<FilterCharacter>({});
  const { loading, data, error, refetch } = useQuery(GetCharacters, {
    variables: {
      page: page,
      filter: filters,
    },
  });
  const ItemSeparator: ReactElement = <View style={{ height: 10 }} />;

  const closeCharacterDetails = () => {
    setCharacterDetails(undefined);
  };

  const changeStatus = (newStatus: Status) => {
    if (newStatus !== filters.status) {
      setFilter((previousFilter) => ({ ...previousFilter, status: newStatus }));
    }
  };

  const changeGender = (newGender: Gender) => {
    if (newGender !== filters.gender) {
      setFilter((previousFilter) => ({ ...previousFilter, gender: newGender }));
    }
  };

  useEffect(() => {
    if (!loading) {
      setCharacters((previousCharacter) => [
        ...previousCharacter,
        ...data.characters.results,
      ]);
      setNextPage(data.characters.info.next !== null);
    }
  }, [data]);

  useEffect(() => {
    setCharacters([]);
    setPage(1);
  }, [filters]);

  if (loading && !characters.length) {
    return <ActivityIndicator color="white" size="large" />;
  } else if (error) {
    return (
      <>
        <Text style={{ fontSize: 20, color: "white" }}>
          An error occured: {error}
        </Text>
        <TouchableOpacity
          style={{
            backgroundColor: "#aaaaaa",
            paddingHorizontal: 10,
            borderRadius: 10,
          }}
          onPress={() => {
            setCharacters([]);
            setPage(1);
          }}
        >
          <Text style={{ fontSize: 20, color: "white" }}>Reload</Text>
        </TouchableOpacity>
      </>
    );
  } else {
    return (
      <View style={{ height: "100%", width: "100%" }}>
        <View
          style={{
            flexDirection: "row",
            justifyContent: "space-around",
          }}
        >
          <GenderFilter filter={filters} setGender={changeGender} />
          <StatusFilter filter={filters} setStatus={changeStatus} />
        </View>
        <FlatList
          data={characters}
          ItemSeparatorComponent={() => ItemSeparator}
          ListHeaderComponent={ItemSeparator}
          ListFooterComponent={
            nextPage ? (
              <ActivityIndicator
                color="white"
                size="large"
                style={{ marginVertical: 10 }}
              />
            ) : (
              ItemSeparator
            )
          }
          onEndReached={() => {
            if (nextPage) {
              setPage(data.characters.info.next);
            }
          }}
          onEndReachedThreshold={0.1}
          renderItem={({ item }) => (
            <TouchableOpacity onPress={() => setCharacterDetails(item)}>
              <CharacterCard character={item} />
            </TouchableOpacity>
          )}
        />
        {characterDeatils ? (
          <CharacterDetails
            closeModal={closeCharacterDetails}
            character={characterDeatils!}
          />
        ) : (
          <></>
        )}
      </View>
    );
  }
}
