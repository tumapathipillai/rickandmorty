import SwiftUI

struct CharacterRow: View {
    var character: CharacterViewModel
    @State var imageLoaded: Bool = false
    
    var body: some View {
        HStack {
            CharacterImage(url: character.image)
                .scaledToFit()
                .frame(width: 70, height: 70)
            Spacer()
            Text(character.name)
                .font(.headline)
            Spacer()
        }
    }
}

struct CharacterRow_Previews: PreviewProvider {
    static var previews: some View {
        CharacterRow(character: CharacterViewModel(
            id: "1",
            name: "",
            status: Status.unknown,
            gender: Gender.unknown,
            image: "",
            episode: []
        ))
    }
}
