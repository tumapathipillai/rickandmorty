import SwiftUI

struct ContentView: View {
    @EnvironmentObject var modelData: ListCharacterViewModel

    var body: some View {
        CharactersList()
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
            .environmentObject(ListCharacterViewModel(getCharacterService: Network.shared.apollo))
    }
}
