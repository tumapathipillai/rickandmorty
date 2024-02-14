import SwiftUI

struct CharactersList: View {
    @EnvironmentObject var modelData: ListCharacterViewModel
    
    var body: some View {
        NavigationView {
            VStack {
                Header()
                List {
                    if modelData.loading {
                        HStack(alignment: .center) {
                            Spacer()
                            ProgressView()
                            Spacer()
                        }
                    } else {
                        ForEach(modelData.characters) { character in
                            NavigationLink {
                                CharacterDetail(character: character)
                            } label: {
                                CharacterRow(character: character)
                            }
                        }
                        Button {
                            modelData.loadCharacters()
                        } label: {
                            HStack(alignment: .center) {
                                Spacer()
                                Text("Load more...")
                                    .foregroundColor(.blue)
                                Spacer()
                            }
                        }
                        .accessibilityIdentifier("LoadMore")
                    }
                }
                .navigationBarTitle("Characters")
                .navigationBarHidden(true)
                
            }
        }
        .onAppear(perform: {
            modelData.loadCharacters()
        })
    }
}

struct CharactersList_Previews: PreviewProvider {
    static var previews: some View {
        CharactersList()
            .environmentObject(ListCharacterViewModel(getCharacterService: Network.shared.apollo))
    }
}


struct Toto<Tutu>: TotoProtocol {
    var whatever: Tutu
}
protocol TotoProtocol {
    associatedtype T
    var whatever: T {get}
}

let toto = Toto(whatever: String.self)
