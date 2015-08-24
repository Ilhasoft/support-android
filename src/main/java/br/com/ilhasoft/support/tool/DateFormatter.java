package br.com.ilhasoft.support.tool;

import android.text.format.DateUtils;

import java.util.concurrent.TimeUnit;

/**
 * Created by johncordeiro on 14/08/15.
 */
public class DateFormatter {

    public String getTimeElapsed(long timeAgo, String nowTime) {
        long currentTime = System.currentTimeMillis();
        long timeElapsedInMillis = currentTime - timeAgo;
        int seconds = (int) Math.abs(timeElapsedInMillis / 1000);
        int minutes = seconds / 60;
        int hours = minutes / 60;
        int days = hours / 24;

        String time;
        if(days >= 1) {
            time = DateUtils.getRelativeTimeSpanString(currentTime - TimeUnit.DAYS.toMillis(days)).toString();
        } else if(hours >= 1) {
            time = DateUtils.getRelativeTimeSpanString(currentTime - TimeUnit.HOURS.toMillis(hours)).toString();
        } else if(minutes >= 1) {
            time = DateUtils.getRelativeTimeSpanString(currentTime - TimeUnit.MINUTES.toMillis(minutes)).toString();
        } else {
            time = nowTime;
        }
        return time;
    }

}
