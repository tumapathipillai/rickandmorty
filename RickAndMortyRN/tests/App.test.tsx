import React from "react";
import renderer from "react-test-renderer";

import App from "../App";

describe("Testing App component", () => {
  it("has 2 children", () => {
    const app = renderer.create(<App />);

    // @ts-ignore
    expect(app.toJSON().children.length).toBe(2);
  });
});
