package excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.Date;

@Data
public class ExcelData {
    //@ExcelProperty("教育局")
    private String orgName;
    //@ExcelProperty("学校")
    private String campus;
    //@ExcelProperty("姓名")
    private String name;
    //@ExcelProperty("身份")
    private String personType;
    //@ExcelProperty("地址")
    private String address;
    //@ExcelProperty("日期")
    private String date;


    public void spli(){
        JSONObject json = JSONObject.parseObject(this.address);
        StringBuffer buffer = new StringBuffer("");
        buffer.append(json.getString("provinceName")+"省")
                .append(",")
                .append(json.getString("cityName"))
                .append(",")
                .append(json.getString("countyName") == null ? "" : json.getString("countyName"))
                .append(",")
                .append(json.getString("address") == null ? "" : json.getString("address"));
        this.address = buffer.toString();

    }
}
