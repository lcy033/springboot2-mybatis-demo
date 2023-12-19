package com.example.utils;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.example.vo.ExcelVo;
import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Handler;

/**
 * @author : LCY
 * @date : create in 2023/11/9 11:41
 */
public class ExcelExportUtils {

    private static void downLoadExcel(String fileName, HttpServletResponse response, Workbook workbook) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        workbook.write(response.getOutputStream());
    }

    private static void defaultExport(List<Map<String, Object>> list, String fileName, HttpServletResponse response) throws IOException {
        Workbook workbook = ExcelExportUtil.exportExcel(list, ExcelType.XSSF);
        if (workbook != null) {
            downLoadExcel(fileName, response, workbook);
        }
    }

    /**
     * 导出,适用于导出多个sheet的Excel,配合createOneSheet方法一起使用
     *
     * @param list     数据列表(元素是Map)
     * @param fileName 文件名
     */
    public static void exportExcel(List<Map<String, Object>> list, String fileName, HttpServletResponse response) throws IOException {
        defaultExport(list, fileName, response);
    }

    public static void main(String[] args) {
        HttpServletResponse response = new HttpServletResponseWrapper(null);
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();

        List<ExcelVo> list1 = new ArrayList<>();
        ExcelVo vo = new ExcelVo();
        vo.setId(1L);
        vo.setName("张三");
        list1.add(vo);
        list1.add(vo);
        map.put("sheet1", list1);
        map.put("sheet2", list1);
        list.add(map);
        try {
            ExcelExportUtils.exportExcel(list, "测试", response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
