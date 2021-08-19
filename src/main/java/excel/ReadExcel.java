package excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;

public class ReadExcel {


    public void read(){

        String fileName = "/Users/space/Desktop/执行结果1 (2).xlsx";
        ExcelReader excelReader = null;
        try {
            excelReader = EasyExcel.read(fileName, ExcelData.class, new DemoDataListener()).build();
            ReadSheet readSheet = EasyExcel.readSheet(0).build();
            excelReader.read(readSheet);
        } finally {
            if (excelReader != null) {
                // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
                excelReader.finish();
            }
        }
    }
}
