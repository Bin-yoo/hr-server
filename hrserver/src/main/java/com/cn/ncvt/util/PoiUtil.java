package com.cn.ncvt.util;

import com.cn.ncvt.entity.Employee;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * @version : V1.0
 * @ClassName: PoiUtil
 * @Description: TODO
 * @Auther: Bin
 * @Date: 2019/11/25 17:34
 **/
public class PoiUtil {

    public static void exportExcel(HttpServletResponse response, List<Employee> excelData) throws IOException{

        //表头数据
        String[] header = {"编号", "姓名", "工号", "性别", "出生日期", "民族", "政治面貌", "籍贯", "婚姻状态", "身份证号码", "联系方式", "邮箱", "居住地址", "毕业院校", "专业", "学历", "所属部门", "职位", "职称", "就职日期", "在职状态", "离职日期", "是否正式员工", "转正日期", "合同起始日期", "合同结束日期"};

        //声明一个工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();

        //创建日期显示格式
        HSSFCellStyle dateCellStyle = workbook.createCellStyle();
        CreationHelper createHelper = workbook.getCreationHelper();
        short dateFormat = createHelper.createDataFormat().getFormat("yyyy-dd-MM");
        dateCellStyle.setDataFormat(dateFormat);
        //创建标题的显示样式
        HSSFCellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.YELLOW.index);
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        //生成一个表格，设置表格名称为"员工档案"
        HSSFSheet sheet = workbook.createSheet("员工档案");

        //设置表格列宽度为10个字节
        sheet.setDefaultColumnWidth(10);

        //创建第一行表头
        HSSFRow headrow = sheet.createRow(0);
        for (int i = 0; i < header.length; i++) {
            //创建一个单元格
            HSSFCell cell = headrow.createCell(i);
            //创建一个内容对象
            HSSFRichTextString text = new HSSFRichTextString(header[i]);
            //将内容对象的文字内容写入到单元格中
            cell.setCellValue(text);
            cell.setCellStyle(headerStyle);
        }

        //模拟遍历结果集，把内容加入表格
        for (int i = 0; i < excelData.size(); i++) {
            Employee employee = excelData.get(i);
            HSSFRow row = sheet.createRow(i+1);
            row.createCell(0).setCellValue(i+1);
            row.createCell(1).setCellValue(employee.getName());
            row.createCell(2).setCellValue(employee.getWorkId());
            row.createCell(3).setCellValue(employee.getSex());
            HSSFCell birthdayCell = row.createCell(4);
            birthdayCell.setCellValue(employee.getBirthday());
            birthdayCell.setCellStyle(dateCellStyle);
            row.createCell(5).setCellValue(employee.getNationName());
            row.createCell(6).setCellValue(employee.getPoliticlName());
            row.createCell(7).setCellValue(employee.getNativePlace());
            row.createCell(8).setCellValue(employee.getWedlock());
            row.createCell(9).setCellValue(employee.getIdCard());
            row.createCell(10).setCellValue(employee.getPhone());
            row.createCell(11).setCellValue(employee.getEmail());
            row.createCell(12).setCellValue(employee.getAddress());
            row.createCell(13).setCellValue(employee.getSchool());
            row.createCell(14).setCellValue(employee.getSpecialty());
            row.createCell(15).setCellValue(employee.getTitopDegree());
            row.createCell(16).setCellValue(employee.getDepartmentName());
            row.createCell(17).setCellValue(employee.getPositionName());
            row.createCell(18).setCellValue(employee.getJobLevelName());
            HSSFCell beginDate = row.createCell(19);
            beginDate.setCellValue(employee.getBeginDate());
            beginDate.setCellStyle(dateCellStyle);
            row.createCell(20).setCellValue(employee.getWorkState());
            HSSFCell quitTime = row.createCell(21);
            quitTime.setCellValue(employee.getQuitTime());
            quitTime.setCellStyle(dateCellStyle);
            if (employee.getTransferdate() != null) {
                row.createCell(22).setCellValue("是");
            } else {
                row.createCell(22).setCellValue("否");
            }
            HSSFCell transferdate = row.createCell(23);
            transferdate.setCellValue(employee.getTransferdate());
            transferdate.setCellStyle(dateCellStyle);
            HSSFCell beginContract = row.createCell(24);
            beginContract.setCellValue(employee.getBeginContract());
            beginContract.setCellStyle(dateCellStyle);
            HSSFCell endContract = row.createCell(25);
            endContract.setCellValue(employee.getEndContract());
            endContract.setCellStyle(dateCellStyle);
        }

        //准备将Excel的输出流通过response输出到页面下载
        //八进制输出流
        response.setContentType("application/octet-stream");

        //这后面可以设置导出Excel的名称，此例中名为student.xls
        response.setHeader("Content-disposition", "attachment;filename=employee.xls");

        //刷新缓冲
        response.flushBuffer();

        //workbook将Excel写入到response的输出流中，供页面下载
        workbook.write(response.getOutputStream());
    }

}
