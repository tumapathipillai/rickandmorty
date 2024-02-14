import { gql } from "@apollo/client";

export default gql`
  query GetCharacters($page: Int!, $filter: FilterCharacter!) {
    characters(page: $page, filter: $filter) {
      info {
        next
      }
      results {
        id
        name
        status
        gender
        image
      }
    }
  }
`;
