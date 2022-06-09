package geekbang.time.commonmistakes.datetime.timezone;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class CommonMistakesApplication {
    public static void main(String[] args) throws Exception {
        test();
        wrong1();
    }

    private static void test() {
        System.out.println("test");
        System.out.println(new Date(0));
        // TimeZone.getDefault().getRawOffset() : 返回当前时区对象跟UTC时间之间的时间偏移量(毫秒计)
        System.out.println(TimeZone.getDefault().getID() + ":" + TimeZone.getDefault().getRawOffset()/3600/1000);
//        ZoneId.getAvailableZoneIds().forEach(id -> System.out.println(String.format("%s:%s", id, ZonedDateTime.now(ZoneId.of(id)))));
    }

    private static void wrong1() throws ParseException {
        System.out.println("wrong1");
        String stringDate = "2020-01-02 22:00:00";
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
        Date date1 = inputFormat.parse(stringDate);
        System.out.println(date1 + ":" + date1.getTime());
        inputFormat.setTimeZone(TimeZone.getTimeZone("America/New_York"));
        Date date2 = inputFormat.parse(stringDate);
        System.out.println(date2 + ":" + date2.getTime());
    }
}
