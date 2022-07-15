package com.khl.gentledentalcare.utils;

import java.util.Date;

/**
 *
 * @author ASUS
 */
public class CalculatorTime {

    public static String timeAgo(Date currentDate, Date pastDate) {
        long milliSecPerMinute = 60 * 1000;
        long milliSecPerHour = milliSecPerMinute * 60;
        long milliSecPerDay = milliSecPerHour * 24;
        long milliSecPerMonth = milliSecPerDay * 30;
        long milliSecPerYear = milliSecPerDay * 365;

        long msExpired = currentDate.getTime() - pastDate.getTime();

        if (msExpired < milliSecPerMinute) {
            if (Math.round(msExpired / 1000) == 1) {
                return String.valueOf(Math.round(msExpired / 1000)) + " second ago... ";
            } else {
                return String.valueOf(Math.round(msExpired / 1000) + " seconds ago...");
            }
        } else if (msExpired < milliSecPerHour) {
            if (Math.round(msExpired / milliSecPerMinute) == 1) {
                return String.valueOf(Math.round(msExpired / milliSecPerMinute)) + " minute ago... ";
            } else {
                return String.valueOf(Math.round(msExpired / milliSecPerMinute)) + " minutes ago... ";
            }
        } else if (msExpired < milliSecPerDay) {
            if (Math.round(msExpired / milliSecPerHour) == 1) {
                return String.valueOf(Math.round(msExpired / milliSecPerHour)) + " hour ago... ";
            } else {
                return String.valueOf(Math.round(msExpired / milliSecPerHour)) + " hours ago... ";
            }
        } else if (msExpired < milliSecPerMonth) {
            if (Math.round(msExpired / milliSecPerDay) == 1) {
                return String.valueOf(Math.round(msExpired / milliSecPerDay)) + " day ago... ";
            } else {
                return String.valueOf(Math.round(msExpired / milliSecPerDay)) + " days ago... ";
            }
        } else if (msExpired < milliSecPerYear) {
            if (Math.round(msExpired / milliSecPerMonth) == 1) {
                return String.valueOf(Math.round(msExpired / milliSecPerMonth)) + "  month ago... ";
            } else {
                return String.valueOf(Math.round(msExpired / milliSecPerMonth)) + "  months ago... ";
            }
        } else {
            if (Math.round(msExpired / milliSecPerYear) == 1) {
                return String.valueOf(Math.round(msExpired / milliSecPerYear)) + " year ago...";
            } else {
                return String.valueOf(Math.round(msExpired / milliSecPerYear)) + " years ago...";
            }
        }
    }
}
