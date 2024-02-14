import XCTest

class CharacterListUITest: XCTestCase {

    override func setUpWithError() throws {
        continueAfterFailure = false
    }

    override func tearDownWithError() throws {}

    func testWhenLoadingMoreThenHavingNewCharacters() throws {
        let app = XCUIApplication()
        app.launch()
        
        XCTAssertEqual(app.staticTexts.count, 20)
        XCTAssertFalse(app.staticTexts["Aqua Morty"].exists)
        
        app.swipeUp(velocity: .fast)
        app.swipeUp(velocity: .fast)
        app.buttons["LoadMore"].tap()
        app.swipeUp()
        
        XCTAssert(app.staticTexts["Aqua Morty"].exists)
        app.swipeUp()
        app.swipeUp()
        XCTAssert(app.staticTexts["Beth's Mytholog"].exists)
    }
}
