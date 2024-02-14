import SwiftUI

struct Header: View {
    var body: some View {
        HStack(alignment: .center) {
            Image("title")
                .resizable()
                .scaledToFit()
                .frame(height: 80)
        }
    }
}

struct Header_Previews: PreviewProvider {
    static var previews: some View {
        Header()
    }
}
