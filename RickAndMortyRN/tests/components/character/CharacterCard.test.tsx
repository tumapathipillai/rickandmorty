import React from "react";
import renderer from "react-test-renderer";
import { render } from "@testing-library/react-native";
import { CharacterInterface, Gender, Status } from "../../../src";

import CharacterCard from "../../../src/components/character/CharacterCard";

const fakeCharacter: CharacterInterface = {
  id: 8,
  name: "Adjudicator Rick",
  status: Status.DEAD,
  gender: Gender.MALE,
  image: "https://rickandmortyapi.com/api/character/avatar/8.jpeg",
};

describe("Testing CharacterCard component", () => {
  it("has 2 children", () => {
    const tree = renderer.create(<CharacterCard character={fakeCharacter} />);

    // @ts-ignore
    expect(tree.toJSON()!.children.length).toBe(2);
  });

  it("prompted text is Character Name", () => {
    const { getByTestId } = render(<CharacterCard character={fakeCharacter} />);

    expect(getByTestId("CharacterName").props.children).toBe(
      fakeCharacter.name,
    );
  });

  it("image source is Character Image URI", () => {
    const { getByTestId } = render(<CharacterCard character={fakeCharacter} />);

    expect(getByTestId("CharacterImage").props.source.uri).toBe(
      fakeCharacter.image,
    );
  });
});
