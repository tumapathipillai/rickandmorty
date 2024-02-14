import XCTest

@testable import RickAndMorty

class CharacterViewModelTests: XCTestCase {
    var sut: CharacterViewModel!
    
    struct CharacterMock: CharacterProtocol {
        var id: String
        var name: String
        var status: Status
        var gender: Gender
        var image: String
        var episode: [EpisodeProtocol]
    }
    
    struct EpisodeMock: EpisodeProtocol {
        var id: String
        var name: String
        var saison: Int
        var numero: Int
    }
    
    override func setUpWithError() throws {}
    
    override func tearDownWithError() throws {}
    
    func testGivenCharacterProtocolWhenInitThenHaveCorrectProperties() throws {
        let character = CharacterMock(
            id: "id",
            name: "name",
            status: .unknown,
            gender: .female,
            image: "image",
            episode: (0..<5).map {
                EpisodeMock(
                    id: "\($0)",
                    name: "name-\($0)",
                    saison: 1,
                    numero: $0
                )
            }
        )
        
        let sut = CharacterViewModel(model: character)
        
        XCTAssertEqual(sut.id, "id")
        XCTAssertEqual(sut.name, "name")
        XCTAssertEqual(sut.status, .unknown)
        XCTAssertEqual(sut.gender, .female)
        XCTAssertEqual(sut.image, "image")
        XCTAssertEqual(sut.episode, (0..<5).map {
            EpisodeViewModel(
                id: "\($0)",
                name: "name-\($0)",
                saison: 1,
                numero: $0
            )
        })
    }
    
    func testPerformanceExample() throws {
        self.measure {}
    }
}
