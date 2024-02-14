import XCTest

@testable import RickAndMorty
class EpisodeAdaptorTests: XCTestCase {

    override func setUpWithError() throws {}

    override func tearDownWithError() throws {}

    func testGivenGraphqlObjectWhenInitThenHavingCorrectProperties() throws {
        let graphqlObject = GetCharactersQuery.Data.Character.Result.Episode(
            id: "0",
            name: "EpisodeName",
            episode: "S00E00"
        )
        
        let stu = EpisodeAdaptator(graphqlObject: graphqlObject)
        
        XCTAssertEqual(stu.id, "0")
        XCTAssertEqual(stu.name, "EpisodeName")
        XCTAssertEqual(stu.saison, 0)
        XCTAssertEqual(stu.numero, 0)
    }

    func testPerformanceExample() throws {
        self.measure {}
    }
    
}
