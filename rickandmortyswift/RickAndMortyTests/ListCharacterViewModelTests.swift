import XCTest
import Apollo
@testable import RickAndMorty
class ListCharacterViewModelTests: XCTestCase {
    
    override func setUpWithError() throws {
        // Put setup code here. This method is called before the invocation of each test method in the class.
    }
    
    override func tearDownWithError() throws {
        // Put teardown code here. This method is called after the invocation of each test method in the class.
    }
    
    func testGivenGraphqlCharactersWhenLoadingThenHaveAllCharacters() throws {
        // This is an example of a functional test case.
        // Use XCTAssert and related functions to verify your tests produce the correct results.
        // Any test you write for XCTest can be annotated as throws and async.
        // Mark your test throws to produce an unexpected failure when your test encounters an uncaught error.
        // Mark your test async to allow awaiting for asynchronous code to complete. Check the results with assertions afterwards.
        let listCharacter = ListCharacterViewModel(getCharacterService: ServiceMock(characters: (0..<5).map {
            .init(id: "frrg",
                  name: "name-\($0)",
                  status: Status.unknown.rawValue,
                  gender: Gender.female.rawValue,
                  image: "image-\($0)",
                  episode: [])
        }))
        
        XCTAssertEqual(listCharacter.characters.count, 0)
        listCharacter.loadCharacters()
        
        XCTAssertEqual(listCharacter.characters.count, 5)
        for (index, _) in listCharacter.characters.enumerated() {
            XCTAssertEqual(
                listCharacter.characters[index],
                CharacterViewModel(
                    id: "frrg",
                    name: "name-\(index)",
                    status: Status.unknown,
                    gender: Gender.female,
                    image: "image-\(index)",
                    episode: []
                )
            )
        }
    }
    
    func testGivenGraphCharactersWhenLoadingMoreThenHavingMoreCharacters() throws {
        let listCharacter = ListCharacterViewModel(getCharacterService: ServiceMock(characters: (0..<5).map {
            .init(id: "frrg",
                  name: "name-\($0)",
                  status: Status.unknown.rawValue,
                  gender: Gender.female.rawValue,
                  image: "image-\($0)",
                  episode: [])
        }))
        
        XCTAssertEqual(listCharacter.characters.count, 0)
        listCharacter.loadCharacters()
        XCTAssertEqual(listCharacter.characters.count, 5)
        listCharacter.loadCharacters()
        XCTAssertEqual(listCharacter.characters.count, 10)
        XCTAssertEqual(listCharacter.characters[0], listCharacter.characters[5])
        XCTAssertEqual(listCharacter.characters[1], listCharacter.characters[6])
        XCTAssertEqual(listCharacter.characters[2], listCharacter.characters[7])
        XCTAssertEqual(listCharacter.characters[3], listCharacter.characters[8])
        XCTAssertEqual(listCharacter.characters[4], listCharacter.characters[9])
    }
    
    func testGivenPageWhenLoadingMoreThenPagePlusOne() throws {
        let listCharacter = ListCharacterViewModel(getCharacterService: ServiceMock(characters: (0..<5).map {
            .init(id: "frrg",
                  name: "name-\($0)",
                  status: Status.unknown.rawValue,
                  gender: Gender.female.rawValue,
                  image: "image-\($0)",
                  episode: [])
        }))
        
        XCTAssertEqual(listCharacter.page, 0)
        listCharacter.loadCharacters()
        XCTAssertEqual(listCharacter.page, 1)
        listCharacter.loadCharacters()
        XCTAssertEqual(listCharacter.page, 2)
    }
    
    //    func testPerformanceExample() throws {
    //        // This is an example of a performance test case.
    //        self.measure {
    //            // Put the code you want to measure the time of here.
    //        }
    //    }
    
    struct ServiceMock: GetCharacterServiceProtocol {
        let characters: [GetCharactersQuery.Data.Character.Result]

        func fetch<Query>(query: Query, cachePolicy: CachePolicy, contextIdentifier: UUID?, queue: DispatchQueue, resultHandler: GraphQLResultHandler<Query.Data>?) -> Cancellable where Query : GraphQLQuery {
            let char = GetCharactersQuery.Data.Character(info: nil, results: characters)
            let data: GetCharactersQuery.Data = GetCharactersQuery.Data(characters: char)
            do {
                let queryData: Query.Data = try Query.Data(jsonObject: data.jsonObject)
                resultHandler?(.success(GraphQLResult<Query.Data>(data: queryData, extensions: nil, errors: nil, source: .cache, dependentKeys: nil)))
            } catch {
                fatalError("Error while creating data : \(error)")
            }
            
            return CancellableMock()
        }
    }
    
    class CancellableMock: Cancellable {
        func cancel() {}
    }
    
}
