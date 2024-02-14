import Foundation
import Apollo

extension ApolloClient: GetCharacterServiceProtocol {}

final class ListCharacterViewModel: ObservableObject {
    @Published var characters: [CharacterViewModel] = []
    @Published var loading: Bool = true
    @Published var page: Int = 0

    let getCharacterService: GetCharacterServiceProtocol
    
    init(getCharacterService: GetCharacterServiceProtocol) {
        self.getCharacterService = getCharacterService
    }
    
    func loadCharacters() {
        page += 1
        getCharacterService.fetch(query: GetCharactersQuery(page: page), cachePolicy: .default, contextIdentifier: nil, queue: .main) { [weak self] result in
            guard let wself = self else {
                return
            }
            switch result {
            case .success(let graphqlResult):
                guard let results = graphqlResult.data?.characters?.results else {
                    return
                }
                wself.characters.append(
                    contentsOf: results.compactMap { character in
                        guard let character = character else {
                            return nil
                        }
                        return CharacterViewModel(model: CharacterAdaptor(graphqlObject: character))
                    })
                wself.loading = false
            case .failure(let error):
                fatalError("Error while fetching characters : \(error)")
            }
        }
    }
}
