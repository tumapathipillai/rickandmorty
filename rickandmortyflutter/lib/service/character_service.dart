import 'package:graphql_flutter/graphql_flutter.dart';

class CharacterService {
  final HttpLink _httpLink = HttpLink("https://rickandmortyapi.com/graphql");

  GraphQLClient getGraphQLClient() {
    return GraphQLClient(link: _httpLink, cache: GraphQLCache());
  }
}
