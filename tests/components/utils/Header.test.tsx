import React from "react";
import renderer from "react-test-renderer";

import Header from "../../../src/components/utils/Header";

describe("Testing Header component", () => {
  it("has 1 child", () => {
    const tree = renderer.create(<Header />);

    // @ts-ignore
    expect(tree.toJSON()!.children.length).toBe(1);
  });

  it("only child is Image", () => {
    const tree = renderer.create(<Header />);

    // @ts-ignore
    expect(tree.toJSON()!.children[0].type).toBe("Image");
  });
});
