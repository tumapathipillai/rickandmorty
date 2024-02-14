import Foundation

struct CharacterViewModel: Hashable, Identifiable {    
    var id: String
    var name: String
    var status: Status
    var gender: Gender
    var image: String
    var episode: [EpisodeViewModel]
}

extension CharacterViewModel {
    init(model: CharacterProtocol) {
        self.id = model.id
        self.name = model.name
        self.status = model.status
        self.gender = model.gender
        self.image = model.image
        self.episode = model.episode.map {
            EpisodeViewModel(model: $0)
        }
    }
}

struct EpisodeViewModel: Hashable, Identifiable {
    var id: String
    var name: String
    var saison: Int
    var numero: Int
}

extension EpisodeViewModel {
    init(model: EpisodeProtocol) {
        self.id = model.id
        self.name = model.name
        self.saison = model.saison
        self.numero = model.numero
    }
}
