import React from "react";
import renderer from "react-test-renderer";
import { render } from "@testing-library/react-native";

import CharacterList from "../../../src/components/character/CharacterList";

let mocks = {};

jest.mock("@apollo/client", () => {
  return {
    ...jest.requireActual("@apollo/client"),
    useQuery: jest.fn(() => mocks),
  };
});

describe("Testing App component", () => {
  it("has no child when loading", () => {
    mocks = {
      loading: true,
    };

    const app = renderer.create(<CharacterList />);

    // @ts-ignore
    expect(app.toJSON().children).toBeNull();
  });

  it("is animated ActivityIndicator when loading", () => {
    mocks = {
      loading: true,
    };

    const app = renderer.create(<CharacterList />);

    // @ts-ignore
    expect(app.toJSON().type).toBe("ActivityIndicator");
    // @ts-ignore
    expect(app.toJSON().props.animating).toBeTruthy();
  });

  it("has 2 children when error", () => {
    mocks = {
      error: "Error",
    };

    const app = renderer.create(<CharacterList />);

    // @ts-ignore
    expect(app.toJSON().children.length).toBe(2);
  });

  it("Error message is prompted when error", () => {
    mocks = {
      error: "An error message",
    };

    const { getByTestId } = render(<CharacterList />);

    // @ts-ignore
    expect(getByTestId("ErrorText").props.children[1]).toBe(mocks.error);
  });

  it("has 2 children when success", () => {
    mocks = {
      data: {
        characters: {
          info: {
            next: 1,
          },
          results: [
            {
              id: 1,
              name: "Rick Sanchez",
              status: "Alive",
              gender: "Male",
              image: "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            },
            {
              id: 2,
              name: "Morty Smith",
              status: "Alive",
              gender: "Male",
              image: "https://rickandmortyapi.com/api/character/avatar/2.jpeg",
            },
          ],
        },
      },
    };

    const app = renderer.create(<CharacterList />);

    // @ts-ignore
    expect(app.toJSON().children.length).toBe(2);
  });

  it("FlatList has all items when success", () => {
    mocks = {
      data: {
        characters: {
          info: {
            next: 1,
          },
          results: [
            {
              id: 1,
              name: "Rick Sanchez",
              status: "Alive",
              gender: "Male",
              image: "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            },
            {
              id: 2,
              name: "Morty Smith",
              status: "Alive",
              gender: "Male",
              image: "https://rickandmortyapi.com/api/character/avatar/2.jpeg",
            },
          ],
        },
      },
    };

    const { getByTestId } = render(<CharacterList />);

    // @ts-ignore
    expect(getByTestId("FlatList").props.children.length).toBe(
      // @ts-ignore
      mocks.data.characters.results.length,
    );
  });
});
