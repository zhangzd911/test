package excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;

import java.util.List;

public class WriteExcel {

    public void write(List<ExcelData> list){
        // 写法2
        String fileName = "/Users/space/Desktop/去过张家界的用户.xlsx";
        // 这里 需要指定写用哪个class去写
        ExcelWriter excelWriter = null;
        try {
            excelWriter = EasyExcel.write(fileName, ExcelData.class).build();
            WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
            excelWriter.write(list, writeSheet);
        } finally {
            // 千万别忘记finish 会帮忙关闭流
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
    }
}
