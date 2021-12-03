import React from "react";
import { render } from "@testing-library/react-native";
import { CharacterInterface, Gender, Status } from "../../../src";

import CharacterDetails from "../../../src/components/character/CharacterDetails";

const fakeCharacter: CharacterInterface = {
  id: 8,
  name: "Adjudicator Rick",
  status: Status.DEAD,
  gender: Gender.MALE,
  image: "https://rickandmortyapi.com/api/character/avatar/8.jpeg",
};

describe("Testing CharacterDetails component", () => {
  it("Card has 5 children", () => {
    const { getByTestId } = render(
      <CharacterDetails character={fakeCharacter} closeModal={() => {}} />,
    );

    expect(getByTestId("Card").children.length).toBe(5);
  });

  it("prompted Name is Character Name", () => {
    const { getByTestId } = render(
      <CharacterDetails character={fakeCharacter} closeModal={() => {}} />,
    );

    expect(getByTestId("Name").props.children).toBe(fakeCharacter.name);
  });

  it("prompted Status is Character Name", () => {
    const { getByTestId } = render(
      <CharacterDetails character={fakeCharacter} closeModal={() => {}} />,
    );

    expect(getByTestId("Status").props.children[1]).toBe(fakeCharacter.status);
  });

  it("prompted Gender is Character Name", () => {
    const { getByTestId } = render(
      <CharacterDetails character={fakeCharacter} closeModal={() => {}} />,
    );

    expect(getByTestId("Gender").props.children[1]).toBe(fakeCharacter.gender);
  });

  it("image source is Character Image URI", () => {
    const { getByTestId } = render(
      <CharacterDetails character={fakeCharacter} closeModal={() => {}} />,
    );

    expect(getByTestId("Image").props.source.uri).toBe(fakeCharacter.image);
  });
});
