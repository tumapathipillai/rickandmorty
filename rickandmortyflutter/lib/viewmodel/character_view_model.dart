import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:rick_and_morty_flutter/repository/character_repository.dart';

import '../model/character.dart';

class CharacterViewModel extends StateNotifier<List<Character>> {
  final CharacterRepository _characterRepository = CharacterRepository();
  int currentPage = 0;

  CharacterViewModel() : super([]) {
    loadCharacters();
  }

  void loadCharacters() {
    currentPage += 1;
    _characterRepository.getCharacters(currentPage).then((value) {
      state = [...state, ...value];
    });
  }
}

final characterViewModelProvider =
    StateNotifierProvider<CharacterViewModel, List<Character>>(
  (ref) {
    return CharacterViewModel();
  },
);
