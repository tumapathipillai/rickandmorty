import 'package:graphql_flutter/graphql_flutter.dart';
import 'package:rick_and_morty_flutter/query/get_characters.dart';
import 'package:rick_and_morty_flutter/service/character_service.dart';

import '../model/character.dart';

class CharacterRepository {
  final CharacterService _characterService = CharacterService();

  Future<List<Character>> getCharacters(int page) async {
    QueryOptions _queryOptions = QueryOptions(
      document: gql(getCharactersQuery),
      variables: {
        "page": page,
      },
    );

    final result =
        await _characterService.getGraphQLClient().query(_queryOptions);

    return List<Character>.from(
      result.data?["characters"]?["results"]
          .map((character) => Character.fromJson(character))
          .toList(),
    );
  }
}
