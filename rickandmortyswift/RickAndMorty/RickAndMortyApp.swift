import SwiftUI

@main
struct RickAndMortyApp: App {
    @StateObject private var modelData = ListCharacterViewModel(getCharacterService: Network.shared.apollo)

    var body: some Scene {
        WindowGroup {
            ContentView()
                .environmentObject(modelData)
        }
    }
}
