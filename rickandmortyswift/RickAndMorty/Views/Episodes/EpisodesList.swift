import SwiftUI

struct EpisodesList: View {
    var episodes: [EpisodeViewModel]
    var body: some View {
        VStack(alignment: .center) {
            Text("List of Episodes")
                .fontWeight(.medium)
                .font(.title2)
                .accessibilityIdentifier("EpisodeListTitle")
            ScrollView {
                Divider()
                ForEach(episodes) { episode in
                    EpisodeRow(episode: episode)
                    Divider()
                }                
            }
        }
        .padding(.top)
    }
}

struct EpisodesList_Previews: PreviewProvider {
    static var previews: some View {
        EpisodesList(episodes: [])
    }
}
