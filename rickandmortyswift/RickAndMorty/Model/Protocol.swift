import Foundation
import Apollo

enum Status: String, Codable {
    case alive = "Alive"
    case dead = "Dead"
    case unknown = "unknown"
}

enum Gender: String, Codable {
    case female = "Female"
    case male = "Male"
    case genderless = "Genderless"
    case unknown = "unknown"
}

protocol EpisodeProtocol {
    var id: String {get}
    var name: String {get}
    var saison: Int {get}
    var numero: Int {get}
}

protocol CharacterProtocol {
    var id: String {get}
    var name: String {get}
    var status: Status {get}
    var gender: Gender {get}
    var image: String {get}
    var episode: [EpisodeProtocol] {get}
}

protocol GetCharacterServiceProtocol {
    @discardableResult func fetch<Query>(query: Query, cachePolicy: CachePolicy, contextIdentifier: UUID?, queue: DispatchQueue, resultHandler: GraphQLResultHandler<Query.Data>?) -> Cancellable where Query : GraphQLQuery
}
