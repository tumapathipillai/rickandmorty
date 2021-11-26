enum Gender {
  FEMALE = "Female",
  MALE = "Male",
  GENDERLESS = "Genderless",
  UNKNOWN = "unknown",
}

enum Status {
  ALIVE = "Alive",
  DEAD = "Dead",
  UNKNOWN = "unknown",
}

interface CharacterInterface {
  id: number;
  name: string;
  gender: Gender;
  status: Status;
  image: string;
}

interface FilterCharacter {
  status?: Status;
  gender?: Gender;
}

export { Gender, Status, CharacterInterface, FilterCharacter };
