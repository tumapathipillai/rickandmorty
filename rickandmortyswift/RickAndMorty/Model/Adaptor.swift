import Foundation

struct CharacterAdaptor: CharacterProtocol {
    var id: String
    var name: String
    var status: Status
    var gender: Gender
    var image: String
    var episode: [EpisodeProtocol]
    
    init(graphqlObject: GetCharactersQuery.Data.Character.Result) {
        self.id = graphqlObject.id ?? "0"
        self.name = graphqlObject.name ?? "unkown name"
        self.status = Status(rawValue: graphqlObject.status ?? "unknown") ?? Status.unknown
        self.gender = Gender(rawValue: graphqlObject.gender ?? "unknown") ?? Gender.unknown
        self.image = graphqlObject.image ?? ""
        self.episode = graphqlObject.episode.map {
            EpisodeAdaptator(graphqlObject: $0 ?? GetCharactersQuery.Data.Character.Result.Episode(
                id: "0",
                name: "Unknown Name",
                episode: "S00E00"
            ))
        }
    }
}

struct EpisodeAdaptator: EpisodeProtocol {
    var id: String
    var name: String
    var saison: Int
    var numero: Int
    
    init(graphqlObject: GetCharactersQuery.Data.Character.Result.Episode) {
        self.id = graphqlObject.id ?? "0"
        self.name = graphqlObject.name ?? "Unknown Name"
        
        let startSaison = graphqlObject.episode?.index(graphqlObject.episode?.startIndex ?? "".startIndex, offsetBy: 1)
        let endSaison = graphqlObject.episode?.index(graphqlObject.episode?.endIndex ?? "".endIndex, offsetBy: -3)
        self.saison = Int(
            graphqlObject.episode?[
                (startSaison ?? "".startIndex)..<(endSaison ?? "".endIndex)
            ] ?? "0"
        ) ?? 0
        
        let startNumero = graphqlObject.episode?.index(graphqlObject.episode?.startIndex ?? "".startIndex, offsetBy: 4)
        let endNumero = graphqlObject.episode?.endIndex
        self.numero = Int(
            graphqlObject.episode?[
                (startNumero ?? "".startIndex)..<(endNumero ?? "".endIndex)
            ] ?? "0"
        ) ?? 0
    }
}
