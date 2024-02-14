import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

import 'package:rick_and_morty_flutter/viewmodel/character_view_model.dart';

class CharactersView extends ConsumerWidget {
  const CharactersView({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final charactersViewModel = ref.watch(characterViewModelProvider);

    return Scaffold(
      appBar: AppBar(
        title: const Text("Rick And Morty"),
      ),
      body: Column(
        children: [
          Expanded(
            child: ListView.separated(
              itemBuilder: (context, index) {
                final character = charactersViewModel[index];

                return ListTile(
                  leading: Image.network(character.image!),
                  title: Text(character.name!),
                );
              },
              separatorBuilder: (BuildContext context, int index) {
                return const Divider(
                  color: Colors.black,
                );
              },
              itemCount: charactersViewModel.length,
            ),
          ),
          TextButton(
            onPressed: () => {
              ref.watch(characterViewModelProvider.notifier).loadCharacters()
            },
            child: const Text("Load More..."),
          ),
        ],
      ),
    );
  }
}
