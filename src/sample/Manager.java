package sample;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Manager {

    public static void createTable(XSSFWorkbook workbook, String name) {
        //todo 时间
        XSSFSheet sheet = workbook.createSheet(name + "考勤记录表");
        XSSFRow header = sheet.createRow(0);
        // 创建单元格，设置值表头，设置表头居中
        XSSFCellStyle style = workbook.createCellStyle();
        //需要的字段
        String[] array = {"日期", "姓名", "班1 上班时间","班1 下班时间","班2 上班时间","班2 下班时间","班3 上班时间","班3 下班时间","实际出勤","加班时间"};
        // 设置表头
        for (int i = 0; i < array.length; i++) {
            XSSFCell cell = header.createCell(i);
            cell.setCellValue(array[i]);
            cell.setCellStyle(style);
        }
    }

    public static void createRecord(File file, XSSFWorkbook workbook, int i) throws IOException {
        XSSFSheet xssfSheet= Manager.readExcel(file);
        if (xssfSheet == null) {
            System.out.println("模版文件有问题");
            return;
        }
        List<String> nameList = Manager.getNameList(xssfSheet);
        List<String> dateList = Manager.getDateList(xssfSheet);
        if (nameList.size() == 0 || dateList.size() == 0) {
            System.out.println("模版文件有问题");
            return;
        }
        List<Record> recordList = Manager.getRecordList(nameList.size(), dateList.size(), i);
        Manager.addBody(recordList, workbook, nameList, dateList);
        OutputStream out = new FileOutputStream(file.getParent()+"/出勤记录表" + ".xlsx");
        workbook.write(out);
        out.close();
    }

    /**
     * 读取选择excel，获得名字list
     * @param file
     * @return
     * @throws IOException
     */
    private static XSSFSheet readExcel(File file) throws IOException {
        InputStream is = new FileInputStream(file);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);

        if(xssfWorkbook==null) {
            System.out.println("未读取到内容,请检查文件！");
            return null;
        }
        XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
        if (xssfSheet == null) {
            return  null;
        }
        return xssfSheet;
    }

    private static List<String> getNameList(XSSFSheet xssfSheet) {
        List<String> nameList = new ArrayList<>();
        for (int i = 1; i <= xssfSheet.getLastRowNum(); i++) {
            if (xssfSheet.getRow(i).getCell(0) == null) {
                break;
            }
            nameList.add(xssfSheet.getRow(i).getCell(0).getStringCellValue());
        }
        return nameList;
    }

    private static List<String> getDateList(XSSFSheet xssfSheet) {
        List<String> dateList = new ArrayList<>();
        for (int i = 1; i <= xssfSheet.getLastRowNum(); i++) {
            if (xssfSheet.getRow(i).getCell(1) == null) {
                break;
            }
            dateList.add(xssfSheet.getRow(i).getCell(1).getStringCellValue());
        }
        return dateList;
    }


    private static List<Record> getRecordList(int nameSize, int size, int type) {
        List<Record> recordList = new ArrayList<>();
        for (int i = 0; i < nameSize*size; i++) {
            recordList.add(Manager.initRecord(type));
        }
        return recordList;
    }

    private static Record initRecord(int i) {
        Record record = new Record();
        record.setFirstWorkTime(record.getFirstWorkRandomTime());
        record.setFirstOffWorkTime(record.getFirsOfftWorkRandomTime());
        record.setSecondWorkTime(record.getSecondWorkRandomTime());
        record.setSecondOffWorkTime(record.getSecondOffWorkRandomTime(i));
        record.setThirdWorkTime("再说");
        record.setThirdOffWorkTime("再说");
        record.setAttendanceTime("再说");
        record.setOverTime("再说");
        return record;
    }





    private static void addBody(List<Record> recordList, XSSFWorkbook workbook, List<String> nameList, List<String> dateList) {
        int index = 0;
        for (int j = 0; j < nameList.size(); j++) {
            Manager.createTable(workbook, nameList.get(j));
            XSSFSheet sheet = workbook.getSheetAt(j);
            for (int i = 0; i < dateList.size(); i++) {
                    Row row = sheet.createRow( i + 1);
                    row.createCell(0).setCellValue(dateList.get(i));
                    row.createCell(1).setCellValue(nameList.get(j));
                    row.createCell(2).setCellValue(recordList.get(index).getFirstWorkTime());
                    row.createCell(3).setCellValue(recordList.get(index).getFirstOffWorkTime());
                    row.createCell(4).setCellValue(recordList.get(index).getSecondWorkTime());
                    row.createCell(5).setCellValue(recordList.get(index).getSecondOffWorkTime());
                    row.createCell(6).setCellValue(recordList.get(index).getThirdWorkTime());
                    row.createCell(7).setCellValue(recordList.get(index).getThirdOffWorkTime());
                    row.createCell(8).setCellValue(recordList.get(index).getAttendanceTime());
                    row.createCell(9).setCellValue(recordList.get(index).getOverTime());
                    index ++;
            }
        }
    }
}
