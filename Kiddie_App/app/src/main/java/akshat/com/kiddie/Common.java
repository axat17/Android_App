package akshat.com.kiddie;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by akshat on 09/20/2016.
 */
public class Common {

     public static String ip="http://172.23.0.27/kiddie/";

    public static String msg="";

    public static Date addDays(Date date, int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }
}
