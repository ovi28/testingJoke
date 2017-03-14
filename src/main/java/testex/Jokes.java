package testex;

import java.util.ArrayList;
import java.util.List;


public class Jokes {

  List<Joke> jokes = new ArrayList();
  String timeZoneString;

  void addJoke(Joke joke) {
    jokes.add(joke);
  }

  public List<Joke> getJokes() {
    return jokes;
  }

  public void setTimeZoneString(String timeZoneString) {
    this.timeZoneString = timeZoneString;
  }

  public String getTimeZoneString() {
    return timeZoneString;
  }

}
