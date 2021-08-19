package excel;

import java.util.List;

public class ExcelProcess {


    public static ThreadLocal<List<ExcelData>> local = new ThreadLocal<>();

    public static void main(String[] args) {
        ReadExcel readExcel = new ReadExcel();
        readExcel.read();
        List<ExcelData> list= local.get();
        for (ExcelData excelData : list) {
            excelData.spli();
        }
        new WriteExcel().write(list);
    }
}
