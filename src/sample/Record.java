package sample;

import org.apache.poi.xssf.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Record {
    private String name;
    private String frestTime;
    private String secondTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFrestTime() {
        return frestTime;
    }

    public void setFrestTime(String frestTime) {
        this.frestTime = frestTime;
    }

    public String getSecondTime() {
        return secondTime;
    }

    public void setSecondTime(String secondTime) {
        this.secondTime = secondTime;
    }
}
