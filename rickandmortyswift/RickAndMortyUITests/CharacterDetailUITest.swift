import XCTest

class CharacterDetailUITest: XCTestCase {
    
    override func setUpWithError() throws {
        continueAfterFailure = false
    }
    
    override func tearDownWithError() throws {}
    
    func testWhenTapingOnCharacterThenDisplayCharacterDetails() throws {
        let app = XCUIApplication()
        app.launch()
        
        app.staticTexts["Rick Sanchez"].tap()
        
        XCTAssert(app.navigationBars["Rick Sanchez"].exists)
        XCTAssert(app.staticTexts["CharacterDetailName"].exists)
        XCTAssertEqual(app.staticTexts["CharacterDetailName"].label, "Rick Sanchez")
        XCTAssert(app.staticTexts["CharacterDetailStatus"].exists)
        XCTAssertEqual(app.staticTexts["CharacterDetailStatus"].label, "Status: Alive")
        XCTAssert(app.staticTexts["CharacterDetailGender"].exists)
        XCTAssertEqual(app.staticTexts["CharacterDetailGender"].label, "Gender: Male")
    }
}
