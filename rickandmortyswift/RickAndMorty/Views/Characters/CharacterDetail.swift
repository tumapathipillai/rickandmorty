import SwiftUI

struct CharacterDetail: View {
    var character: CharacterViewModel
    
    var body: some View {
        VStack(alignment: .center) {
            CharacterImage(url: character.image)
                .frame(width: 250, height: 250)
            Text(character.name)
                .font(.title)
                .bold()
                .accessibilityIdentifier("CharacterDetailName")
            Text("Status: \(character.status.rawValue)")
                .font(.headline)
                .accessibilityIdentifier("CharacterDetailStatus")
            Text("Gender: \(character.gender.rawValue)")
                .font(.headline)
                .accessibilityIdentifier("CharacterDetailGender")
            EpisodesList(episodes: character.episode)
            Spacer()
        }
        .navigationBarTitle(Text(character.name), displayMode: .inline)
        .padding()
    }
}

struct CharacterDetail_Previews: PreviewProvider {
    static var previews: some View {
        CharacterDetail(character: CharacterViewModel(
            id: "1",
            name: "",
            status: Status.unknown,
            gender: Gender.unknown,
            image: "",
            episode: []
        ))
    }
}
