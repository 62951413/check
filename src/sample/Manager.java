package sample;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Manager {

    public static void createTable(XSSFWorkbook workbook) {
        //todo 时间
        XSSFSheet sheet = workbook.createSheet("考勤记录表");
        XSSFRow header = sheet.createRow(0);
        // 创建单元格，设置值表头，设置表头居中
        XSSFCellStyle style = workbook.createCellStyle();
        // 居中格式
//        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //需要的字段
        String[] array = {"姓名", "初次打卡", "二次打卡"};
        // 设置表头
        for (int i = 0; i < array.length; i++) {
            XSSFCell cell = header.createCell(i);
            cell.setCellValue(array[i]);
            cell.setCellStyle(style);
        }
    }

    public static void createRecord(File file, XSSFWorkbook workbook) throws IOException {
        List<String> name = Manager.readExcel(file);
        if (name.size() == 0) {
            System.out.println("姓名文件有问题");
            return;
        }
        List<Record> recordList = Manager.getRecordList(name.size());
        Manager.addBody(recordList, workbook, name);
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
    private static List<String> readExcel(File file) throws IOException {
        InputStream is = new FileInputStream(file);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);

        if(xssfWorkbook==null) {
            System.out.println("未读取到内容,请检查文件！");
            return new ArrayList<String>();
        }
        XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
        if (xssfSheet == null) {
            return  new ArrayList<String>();
        }

        List<String> nameList = new ArrayList<>();
        for (int i = 0; i <= xssfSheet.getLastRowNum(); i++) {
            nameList.add(xssfSheet.getRow(i).getCell(0).getStringCellValue());
        }
        return nameList;
        }


    private static List<Record> getRecordList(int nameSize) {
        List<Record> recordList = new ArrayList<>();
        for (int i = 0; i < nameSize*31; i++) {
            recordList.add(Manager.initRecord());
        }
        return recordList;
    }

    private static Record initRecord() {
        Record record = new Record();
        record.setFrestTime("你猜");
        record.setSecondTime("草");
        return record;
    }


    private static void addBody(List<Record> recordList, XSSFWorkbook workbook, List<String> nameList) {
        //todo 获得当前月份时间
        int num = 30;
        int index = 0;
        XSSFSheet sheet = workbook.getSheetAt(0);
        for (String s: nameList) {
            for (int i = 0; i < num; i++) {
                //todo 判断是否是周日周六
                if (true){
                    Row row = sheet.createRow( index + 1);
                    row.createCell(0).setCellValue(s);
                    row.createCell(1).setCellValue(recordList.get(index).getFrestTime());
                    row.createCell(2).setCellValue(recordList.get(index).getSecondTime());
                    index ++;
                }
            }
        }

    }
}
