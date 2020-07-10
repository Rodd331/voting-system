package com.voting.system.src.impl.Utils;

import java.util.Calendar;
import java.util.Date;

public class Utils {

    public static Date adcMinut(Date date, Integer minutes) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, minutes);
        Date finalDate = cal.getTime();

        return finalDate;
    }
}