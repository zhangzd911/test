package excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.read.listener.ReadListener;

import java.util.ArrayList;
import java.util.List;

public class DemoDataListener extends AnalysisEventListener<ExcelData> {

    List<ExcelData> list = new ArrayList<>();

    @Override
    public void invoke(ExcelData excelData, AnalysisContext analysisContext) {
        list.add(excelData);
        //System.out.println(excelData.toString());
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        ExcelProcess.local.set(list);
    }
}
