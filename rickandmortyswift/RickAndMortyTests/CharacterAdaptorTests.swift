import XCTest

@testable import RickAndMorty
class CharacterAdaptorTests: XCTestCase {

    override func setUpWithError() throws {}

    override func tearDownWithError() throws {}

    func testGivenGraphqlObjectWhenInitThenHavingCorrectProperties() throws {
        let graphqlObject = GetCharactersQuery.Data.Character.Result(
            id: "0",
            name: "CharacterName",
            status: Status.unknown.rawValue,
            gender: Gender.unknown.rawValue,
            image: "CharacterImage",
            episode: (0..<5).map {
                GetCharactersQuery.Data.Character.Result.Episode(
                    id: "\($0)",
                    name: "Episode\($0)Name",
                    episode: "S01E0\($0)"
                )
            }
        )
        
        let stu = CharacterAdaptor(graphqlObject: graphqlObject)
        
        XCTAssertEqual(stu.id, "0")
        XCTAssertEqual(stu.name, "CharacterName")
        XCTAssertEqual(stu.status, Status.unknown)
        XCTAssertEqual(stu.gender, Gender.unknown)
        XCTAssertEqual(stu.image, "CharacterImage")
        XCTAssertEqual(stu.episode.count, 5)
        (0..<5).forEach {
            XCTAssertEqual(stu.episode[$0].id, "\($0)")
            XCTAssertEqual(stu.episode[$0].name, "Episode\($0)Name")
            XCTAssertEqual(stu.episode[$0].saison, 1)
            XCTAssertEqual(stu.episode[$0].numero, $0)
        }
    }

    func testPerformanceExample() throws {
        self.measure {}
    }

}
