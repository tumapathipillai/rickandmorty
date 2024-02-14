import XCTest

@testable import RickAndMorty
class EpisodeViewModelTests: XCTestCase {
    var sut: EpisodeViewModel!

    struct EpisodeMock: EpisodeProtocol {
        var id: String
        var name: String
        var saison: Int
        var numero: Int
    }

    override func setUpWithError() throws {}

    override func tearDownWithError() throws {}

    func testGivenEpisodeProtocolWhenInitThenHavCorrectProperties() throws {
        let episode = EpisodeMock(id: "1", name: "EpisodeName", saison: 0, numero: 0)
        
        let sut = EpisodeViewModel(model: episode)
        
        XCTAssertEqual(sut.id, episode.id)
        XCTAssertEqual(sut.name, episode.name)
        XCTAssertEqual(sut.saison, episode.saison)
        XCTAssertEqual(sut.numero, episode.numero)
    }

    func testPerformanceExample() throws {
        self.measure {}
    }
}
