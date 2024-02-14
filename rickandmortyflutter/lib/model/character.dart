class Character {
  int? id;
  String? name;
  String? status;
  String? gender;
  String? image;

  Character({
    required this.id,
    required this.name,
    required this.status,
    required this.gender,
    required this.image,
  });

  Character.fromJson(Map<String, dynamic> json) {
    id = int.parse(json["id"]);
    name = json["name"];
    gender = json["gender"];
    image = json["image"];
    status = json["status"];
  }
}
