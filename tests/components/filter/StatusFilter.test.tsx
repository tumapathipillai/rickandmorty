import React from "react";
import renderer from "react-test-renderer";
import { render } from "@testing-library/react-native";

import StatusFilter from "../../../src/components/filter/StatusFilter";

describe("Testing StatusFilter component", () => {
  it("has 1 child", () => {
    const tree = renderer.create(
      <StatusFilter filter={{}} setStatus={() => {}} />,
    );

    // @ts-ignore
    expect(tree.toJSON()!.children.length).toBe(1);
  });

  it("has 5 Picker Item", () => {
    const { getByTestId } = render(
      <StatusFilter filter={{}} setStatus={() => {}} />,
    );

    expect(getByTestId("Picker").props.items.length).toBe(4);
  });
});
