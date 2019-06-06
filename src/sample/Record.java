package sample;

import org.apache.poi.xssf.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;

public class Record {
    private String name;
    private String firstWorkTime;
    private String secondWorkTime;
    private String thirdWorkTime;
    private String firstOffWorkTime;
    private String secondOffWorkTime;
    private String thirdOffWorkTime;
    private String date;

    private String attendanceTime;
    private String overTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstWorkTime() {
        return firstWorkTime;
    }

    public void setFirstWorkTime(String firstWorkTime) {
        this.firstWorkTime = firstWorkTime;
    }

    public String getSecondWorkTime() {
        return secondWorkTime;
    }

    public void setSecondWorkTime(String secondWorkTime) {
        this.secondWorkTime = secondWorkTime;
    }

    public String getThirdWorkTime() {
        return thirdWorkTime;
    }

    public void setThirdWorkTime(String thirdWorkTime) {
        this.thirdWorkTime = thirdWorkTime;
    }

    public String getFirstOffWorkTime() {
        return firstOffWorkTime;
    }

    public void setFirstOffWorkTime(String firstOffWorkTime) {
        this.firstOffWorkTime = firstOffWorkTime;
    }

    public String getSecondOffWorkTime() {
        return secondOffWorkTime;
    }

    public void setSecondOffWorkTime(String secondOffWorkTime) {
        this.secondOffWorkTime = secondOffWorkTime;
    }

    public String getThirdOffWorkTime() {
        return thirdOffWorkTime;
    }

    public void setThirdOffWorkTime(String thirdOffWorkTime) {
        this.thirdOffWorkTime = thirdOffWorkTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAttendanceTime() {
        return attendanceTime;
    }

    public void setAttendanceTime(String attendanceTime) {
        this.attendanceTime = attendanceTime;
    }

    public String getOverTime() {
        return overTime;
    }

    public void setOverTime(String overTime) {
        this.overTime = overTime;
    }


    public String getFirstWorkRandomTime() {
        if (Math.random() < 0.96) {
            int sec = (int) (Math.random() * 90);
            return sec >= 60 ? "8:" + (sec - 60) : "8:" + (sec < 10 ? "0" + sec : sec);
        } else {
            int sec = (int) (Math.random() * 30);
            return sec == 30 ? "9:00" : "8:" + (sec + 30);
        }
    }

    public String getFirsOfftWorkRandomTime() {
        if (Math.random() < 0.95) {
            int sec = (int) (Math.random() * 30);
            return sec == 30 ? "12:00" : "11:" + (sec + 30);
        } else {
            int sec = (int) (Math.random() * 30);
            return "11:" + (sec + 30);
        }
    }

    public String getSecondWorkRandomTime() {
        if (Math.random() < 0.97) {
            int sec = (int) (Math.random() * 30);
            return sec == 30 ? "13:00" : "12:" + (sec + 30);
        } else {
            int sec = (int) (Math.random() * 30);
            return "13:" + (sec < 10 ? "0" + sec : sec);
        }
    }


    public String getSecondOffWorkRandomTime(int i) {
        if (Math.random() < 0.95) {
            int sec = (int) (Math.random() * 30);
            if (i == 1) {
            return sec == 30 ? "18:00" : "17:" + (sec + 30);
            }else {
                return "17:" + (sec < 10 ? "0" + sec : sec);
            }
        } else {
            int sec = (int) (Math.random() * 30);
            if (i == 1) {
                return "17:" + (sec < 10 ? "0" + sec : sec);
            }else {
                return sec == 30 ? "17:00" : "16:" + (sec + 30);
            }
        }

    }


    public static void main(String[] args) {
        System.out.println((int) (Math.random() * 90));
    }

}
