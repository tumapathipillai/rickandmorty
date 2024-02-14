import SwiftUI

struct EpisodeRow: View {
    var episode: EpisodeViewModel
    var body: some View {
        VStack(alignment: .center) {
            Text("Saison \(episode.saison) Episode \(episode.numero)")
            Text(episode.name)
                .font(.title3)
        }
    }
}

struct EpisodeRow_Previews: PreviewProvider {
    static var previews: some View {
        EpisodeRow(episode: EpisodeViewModel(id: "1", name: "", saison: 1, numero: 1))
    }
}
