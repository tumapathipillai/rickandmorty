import SwiftUI

struct CharacterImage: View {
    let url: String
    @State var data: Data = Data()
    @State var loading: Bool = true
    
    var body: some View {
        if loading {
            ProgressView()
                .onAppear(perform: {
                    URLSession.shared.dataTask(with: URL(string: url)!) { imageData, response, error in
                        guard let imageData = imageData, error == nil else {
                            return
                        }
                        DispatchQueue.main.async() { [self] in
                            self.data = imageData
                            self.loading = false
                        }
                    }.resume()
                })
        } else {
            Image(uiImage: UIImage(data: data) ?? UIImage())
                .resizable()
                .accessibilityIdentifier("CharacterImage")
        }
    }
}

struct CharacterImage_Previews: PreviewProvider {
    static var previews: some View {
        CharacterImage(url: "https://rickandmortyapi.com/api/character/avatar/1.jpeg")
    }
}
