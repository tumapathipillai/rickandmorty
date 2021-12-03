import React from "react";
import renderer from "react-test-renderer";
import { render } from "@testing-library/react-native";

import GenderFilter from "../../../src/components/filter/GenderFilter";

describe("Testing GenderFilter component", () => {
  it("has 1 child", () => {
    const tree = renderer.create(
      <GenderFilter filter={{}} setGender={() => {}} />,
    );

    // @ts-ignore
    expect(tree.toJSON()!.children.length).toBe(1);
  });

  it("has 5 Picker Item", () => {
    const { getByTestId } = render(
      <GenderFilter filter={{}} setGender={() => {}} />,
    );

    expect(getByTestId("Picker").props.items.length).toBe(5);
  });
});
