String getCharactersQuery = """
  query GetCharacters(\$page: Int!) {
    characters(page: \$page) {
      info {
        next
      }
      results {
        id
        name
        status
        gender
        image
        episode {
          id
          name
          episode
        }
      }
    }
}
""";
