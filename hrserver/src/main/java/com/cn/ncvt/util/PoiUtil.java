package com.cn.ncvt.util;

import com.cn.ncvt.entity.*;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    public static List<Employee> importExcel(MultipartFile file,
                                             List<Nation> allNations,
                                             List<PoliticalStatus> allPolitics,
                                             List<Department> allDeps,
                                             List<Position> allPos,
                                             List<JobLevel> allJobLevels){

        List<Employee> emps = new ArrayList<>();
        try {
            HSSFWorkbook workbook =
                    new HSSFWorkbook(new POIFSFileSystem(file.getInputStream()));
            int numberOfSheets = workbook.getNumberOfSheets();
            for (int i = 0; i < numberOfSheets; i++) {
                HSSFSheet sheet = workbook.getSheetAt(i);
                int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
                Employee employee;
                Date d;
                for (int j = 0; j < physicalNumberOfRows; j++) {
                    if (j == 0) {
                        continue;//标题行
                    }
                    HSSFRow row = sheet.getRow(j);
                    if (row == null) {
                        continue;//没数据
                    }
                    int physicalNumberOfCells = row.getPhysicalNumberOfCells();
                    employee = new Employee();
                    for (int k = 0; k < physicalNumberOfCells; k++) {
                        HSSFCell cell = row.getCell(k);
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        d = new Date();
                        switch (k) {
                            case 0:
                                SimpleDateFormat sdf = new SimpleDateFormat("yyHHmmssSS");
                                String workId = sdf.format(d);
                                employee.setWorkId(Long.parseLong(workId));
                                break;
                            case 1:
                                employee.setName(cell.getStringCellValue());
                                break;
                            case 2:
                                employee.setSex(cell.getStringCellValue());
                                break;
                            case 3:
                                String b = dateFormat.format(cell.getDateCellValue());
                                employee.setBirthday(Timestamp.valueOf(b));
                                break;
                            case 4:
                                int nationIndex = 0;
                                for (Nation nation: allNations) {
                                    if (nation.getName().equals(cell.getStringCellValue())){
                                        nationIndex = nation.getId();
                                    }
                                }
                                employee.setNationId(nationIndex);
                                break;
                            case 5:
                                int psIndex = 0;
                                for (PoliticalStatus politicalStatus: allPolitics) {
                                    if (politicalStatus.getName().equals(cell.getStringCellValue())){
                                        psIndex = politicalStatus.getId();
                                    }
                                }
                                employee.setPoliticlId(psIndex);
                                break;
                            case 6:
                                employee.setNativePlace(cell.getStringCellValue());
                                break;
                            case 7:
                                employee.setWedlock(cell.getStringCellValue());
                                break;
                            case 8:
                                cell.setCellType(CellType.STRING);
                                String idcard = cell.getStringCellValue();
                                employee.setIdCard(idcard);
                                String password = idcard.substring(idcard.length() - 6);
                                String salt = new SecureRandomNumberGenerator().nextBytes().toString();    //生成加密盐
                                String encodedPassword = new SimpleHash("md5",password,salt,2).toString();    //生成加密后的密码
                                employee.setPassword(encodedPassword);
                                employee.setSalt(salt);
                                break;
                            case 9:
                                employee.setPhone(cell.getStringCellValue());
                                break;
                            case 10:
                                employee.setEmail(cell.getStringCellValue());
                                break;
                            case 11:
                                employee.setAddress(cell.getStringCellValue());
                                break;
                            case 12:
                                employee.setSchool(cell.getStringCellValue());
                                break;
                            case 13:
                                employee.setSpecialty(cell.getStringCellValue());
                                break;
                            case 14:
                                employee.setTitopDegree(cell.getStringCellValue());
                                break;
                            case 15:
                                int depIndex = 0;
                                for (Department department: allDeps) {
                                    if (department.getName().equals(cell.getStringCellValue())){
                                        depIndex = department.getId();
                                    }
                                }
                                employee.setDepartmentId(depIndex);
                                break;
                            case 16:
                                int posIndex = 0;
                                for (Position position: allPos) {
                                    if (position.getName().equals(cell.getStringCellValue())){
                                        posIndex = position.getId();
                                    }
                                }
                                employee.setPositionId(posIndex);
                                break;
                            case 17:
                                int jlIndex = 0;
                                for (JobLevel jobLevel: allJobLevels) {
                                    if (jobLevel.getName().equals(cell.getStringCellValue())){
                                        jlIndex = jobLevel.getId();
                                    }
                                }
                                employee.setJobLevelId(jlIndex);
                                break;
                            case 18:
                                String wb = dateFormat.format(cell.getDateCellValue());
                                employee.setBeginDate(Timestamp.valueOf(wb));
                                break;
                            case 19:
                                employee.setWorkState(cell.getStringCellValue());
                                break;
                            case 20:
                                if (cell.getDateCellValue()!=null){
                                    String q = dateFormat.format(cell.getDateCellValue());
                                    employee.setQuitTime(Timestamp.valueOf(q));
                                }
                                break;
                            case 21:
                                if (cell.getDateCellValue()!=null){
                                    String c = dateFormat.format(cell.getDateCellValue());
                                    employee.setConversionTime(Timestamp.valueOf(c));
                                }
                                break;
                            case 22:
                                String bc = dateFormat.format(cell.getDateCellValue());
                                employee.setBeginContract(Timestamp.valueOf(bc));
                                break;
                            case 23:
                                String ec = dateFormat.format(cell.getDateCellValue());
                                employee.setEndContract(Timestamp.valueOf(ec));
                                break;
                            case 24:
                                employee.setBaseSalary(cell.getNumericCellValue());
                                break;
                        }
                    }
                    emps.add(employee);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return emps;

    }

}
