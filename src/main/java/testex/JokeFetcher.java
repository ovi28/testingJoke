package testex;

import static com.jayway.restassured.RestAssured.given;
import com.jayway.restassured.response.ExtractableResponse;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import testex.jokefetching.FetcherFactory;
import testex.jokefetching.IFetcherFactory;
import testex.jokefetching.IJokeFetcher;


public class JokeFetcher {


    private final List<String> availableTypes = Arrays.asList("EduJoke", "ChuckNorris" , "Moma" , "Tambal");

    private IDateFormatter dateFormatter;
    private IFetcherFactory fetcherFactory;


    public JokeFetcher(IDateFormatter dateFormatter, IFetcherFactory fetcherFactory) {
        this.dateFormatter = dateFormatter;
        this.fetcherFactory = fetcherFactory;
    }


    public List<String> getAvailableTypes() {
        return availableTypes;
    }


    boolean isStringValid(String jokeTokens) {
        String[] tokens = jokeTokens.split(",");
        for (String token : tokens) {
            if (!availableTypes.contains(token)) {
                return false;
            }
        }
        return true;
    }

    public Jokes getJokes(String jokesToFetch, String timeZone) throws Exception {
        if (!isStringValid(jokesToFetch)) {
            throw new JokeException("Inputs (jokesToFetch) contain types not recognized");
        }
        String[] tokens = jokesToFetch.split(",");
        Jokes jokes = new Jokes();
        for (IJokeFetcher fetcher : fetcherFactory.getJokeFetchers(jokesToFetch)) {
            jokes.addJoke(fetcher.getJoke());
        }

        String timeZoneString = dateFormatter.getFormattedDate(timeZone, new Date());
        jokes.setTimeZoneString(timeZoneString);
        return jokes;
    }


    public static void main(String[] args) throws Exception {
        JokeFetcher jf = new JokeFetcher(new DateFormatter(), new FetcherFactory()); // inject the DateFormatter
        Jokes jokes = jf.getJokes("EduJoke,ChuckNorris,ChuckNorris,Moma,Tambal", "Europe/Copenhagen");
        jokes.getJokes().forEach((joke) -> {
            System.out.println(joke);
        });
        System.out.println("Is String Valid: " + jf.isStringValid("edu_prog,xxx"));
    }
}
