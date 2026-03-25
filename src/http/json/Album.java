package http.json;

class Album {
  int userId;
  int id;
  String title;

  public Album(int userId, int id, String title) {
    this.userId = userId;
    this.id = id;
    this.title = title;
  }

  @Override
  public String toString() {
    return super.toString();
  }
}
