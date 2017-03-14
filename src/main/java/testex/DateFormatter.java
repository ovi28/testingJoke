package testex;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.TimeZone;

public class DateFormatter implements IDateFormatter {


    @Override
    public String getFormattedDate(String timeZone, Date time) throws JokeException {
        if (!Arrays.asList(TimeZone.getAvailableIDs()).contains(timeZone)) {
            throw new JokeException("Illegal Time Zone String");
        }

        String dateTimeFormat = "dd MMM yyyy hh:mm aa";
        SimpleDateFormat simpleFormat = new SimpleDateFormat(dateTimeFormat);
        simpleFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
        return simpleFormat.format(time);
    }


    public static void main(String[] args) throws JokeException {

        for (String str : TimeZone.getAvailableIDs()) {
            System.out.println(str);
        }

        System.out.println(new DateFormatter().getFormattedDate("Europe/Kiev", new Date()));

        System.out.println(new DateFormatter().getFormattedDate("ImNotLegal", new Date()));

    }

}
