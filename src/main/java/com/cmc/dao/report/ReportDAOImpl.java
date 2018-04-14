/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmc.dao.report;

import com.cmc.model.advertisement.Advertisement;
import com.cmc.model.report.Report;
import com.cmc.util.common.Common;
import com.cmc.util.common.ExcelCommon;
import com.cmc.util.varlist.CommonVarList;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletContext;
import javax.sql.DataSource;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * @Author : KAsun Udayanaga
 * @Document : ReportDAOImpl
 * @Created on : Dec 31, 2017, 4:09:01 PM
 */
@Repository("ReportDAO")
public class ReportDAOImpl implements ReportDAO {

    private int detailRow;
    private int rowCount;
    private final int columnCount = 13;
    private final int headerRowCount = 7;
    private final int staticWidthColumn = 3;
    private final int commentColumnWith = 10000;

    @Autowired
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public JdbcTemplate getJdbcTemplate() {

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate;
    }

    @Override
    public Object generateExcelReport(Report parameters, ServletContext context, HashMap<String, Object> parameterMap, String reportBuildPath, String user, Date date) throws Exception {

        SXSSFWorkbook workbook = new SXSSFWorkbook(-1);
        Object returnObject = null;
        try {
            int totalRecordCount = 0;

            String query = "SELECT COUNT(*) FROM advertisemet ADV";
            query = query.replace(":where", this.getWhere(parameters, user));
            totalRecordCount = getJdbcTemplate().queryForObject(query, new Object[]{}, Integer.class);
            if (totalRecordCount > 0) {
                long maxRow = Long.parseLong(context.getInitParameter(CommonVarList.CONTEXT_PARAM_NUMBER_OF_ROWS_PER_EXCEL));
                workbook = this.createExcelTopSection(parameterMap, user, date);
                SXSSFSheet sheet = workbook.getSheetAt(0);
                detailRow = headerRowCount;
                int fileCount = 0;
                int newFileStatus = 0;
                this.createExcelTableHeandeSection(workbook);
                int selectRow = Integer.parseInt(context.getInitParameter(CommonVarList.CONTEXT_PARAM_NUMBER_OF_SELECT_ROWS));
                int numberOfTimes = totalRecordCount / selectRow;
                if ((totalRecordCount % selectRow) > 0) {
                    numberOfTimes += 1;
                }
                int from = 0;
                int to = selectRow;
                int count = 0;
                for (int i = 0; i < numberOfTimes; i++) {

                    List<Advertisement> advdatalist;

                    String selectquery = "SELECT    ADV.ADVERTISEMENTID AS ADVERTISEMENTID, "
                            + "                     ADV.APPLICANTNAME AS APPLICANTNAME, "
                            + "                     ADV.APPLICANTTELNO AS APLLICANTTELNO, "
                            + "                     ADV.CLIENTNAME AS CLIENTNAME, "
                            + "                     ADV.CLIENTTELNO AS CLIENTTELNO, "
                            + "                     ADV.RESPONSIBLEPERSONNAME AS RESPONSIBLEPERSONNAME, "
                            + "                     ADV.RESPONSIBLEPERSONTELNO AS RESPONSIBLEPERSONTELNO, "
                            + "                     ADVT.DESCRIPTION AS ADVERTISEMENTTYPE, "
                            + "                     ADVS.DESCRIPTION AS ADVERTISEMENTSTATUS, "
                            + "                     PT.DESCRIPTION AS PROPEERTYTYPE, "
                            + "                     ILS.DESCRIPTION AS ILUMINATIONSTATUS, "
                            + "                     LIS.DESCRIPTION AS LEAGALILLIGALSTATUS, "
                            + "                     ADV.ADVERTISEMENTHEIGHT AS ADVERTISEMENTHEIGHT "
                            + "FROM advertisemet ADV "
                            + "LEFT OUTER JOIN advertisemettype ADVT ON ADVT.ADVERTISEMENTTYPEID = ADV.ADVERTISEMENTTYPE "
                            + "LEFT OUTER JOIN applicantcontact AC ON AC.APPLICANTCONTACTID = ADV.APPLICANTADDRESS "
                            + "LEFT OUTER JOIN clientcontact CL ON CL.CLIENTCONTACTID = ADV.CLIENTADDRESS "
                            + "LEFT OUTER JOIN responsiblepersoncontact RPC ON RPC.RESPERSONCONTACTID = ADV.RESPONSIBLEPERSONADDRESS "
                            + "LEFT OUTER JOIN propertytype PT ON PT.PROPERTYTYPEID = ADV.PROPERTYTYPE "
                            + "LEFT JOIN status ADVS ON ADVS.STATUSID = ADV.ADVERTISEMETSTATUS "
                            + "LEFT JOIN status ILS ON ILS.STATUSID = ADV.ILLUMINATIONSTATUS "
                            + "LEFT JOIN status LIS ON LIS.STATUSID = ADV.LEAGALILLEGALSTATUS "
                            + "WHERE 1 = 1 :where"
                            + "LIMIT ?,?";

                    selectquery = selectquery.replace(":where", this.getWhere(parameters, user));

                    advdatalist = this.getJdbcTemplate().query(selectquery, new Object[]{from, to},
                            new RowMapper<Advertisement>() {
                                @Override
                                public Advertisement mapRow(ResultSet rs, int rowNum) throws SQLException {
                                    Advertisement advdata = new Advertisement();
                                    advdata.setAdverisementid(rs.getString("ADVERTISEMENTID"));
                                    advdata.setNameofapplicant(rs.getString("APPLICANTNAME"));
                                    advdata.setApplicanttelno(rs.getString("APLLICANTTELNO"));
                                    advdata.setNameofclient(rs.getString("CLIENTNAME"));
                                    advdata.setClienttelno(rs.getString("CLIENTTELNO"));
                                    advdata.setNameofresponsibleperson(rs.getString("RESPONSIBLEPERSONNAME"));
                                    advdata.setResponsiblepersontelno(rs.getString("RESPONSIBLEPERSONTELNO"));
                                    advdata.setAdvertisementtype(rs.getString("ADVERTISEMENTTYPE"));
                                    advdata.setAdvertisementstatus(rs.getString("ADVERTISEMENTSTATUS"));
                                    advdata.setPropertytype(rs.getString("PROPEERTYTYPE"));
                                    advdata.setLightningtype(rs.getString("ILUMINATIONSTATUS"));
                                    advdata.setLeagalstatus(rs.getString("LEAGALILLIGALSTATUS"));
                                    advdata.setHeight(rs.getString("ADVERTISEMENTHEIGHT"));
                                    return advdata;
                                }
                            });

                    for (Advertisement advdata : advdatalist) {

                        if (detailRow + 1 > maxRow) {
                            fileCount++;
                            newFileStatus = 1;
                            this.writeTemporaryFile(workbook, fileCount, reportBuildPath);
                            workbook = this.createExcelTopSection(parameterMap, user, date);
                            sheet = workbook.getSheetAt(0);
                            detailRow = headerRowCount;
                            this.createExcelTableHeandeSection(workbook);
                        }
                        this.createExcelTableRow(workbook, sheet, count, newFileStatus, advdata);
                        newFileStatus = 0;
                        count++;
                    }

                    // manually control how rows are flushed to disk 
                    if (selectRow % 100 == 0) {
                        ((SXSSFSheet) sheet).flushRows(100); // retain 100 last rows and flush all others
                    }
                    from = from + to;
                }

                if (fileCount > 0) {
                    fileCount++;
                    this.writeTemporaryFile(workbook, fileCount, reportBuildPath);
                    ByteArrayOutputStream outputStream = Common.zipFiles(new File(reportBuildPath).listFiles());
                    returnObject = outputStream;
                } else {
                    for (int i = 0; i < this.columnCount; i++) {
                        if (i == 0) {
                            sheet.setColumnWidth(i, setWidth(14));
                        } else if (i == 1) {
                            sheet.setColumnWidth(i, setWidth(60));
                        } else if (i == 2) {
                            sheet.setColumnWidth(i, setWidth(18));
                        } else if (i == 3) {
                            //int width = (int) (maxCharacter * 1.14388);
                            sheet.setColumnWidth(i, setWidth(60));
                        } else if (i == 4) {
                            sheet.setColumnWidth(i, setWidth(18));
                        } else if (i == 5) {
                            sheet.setColumnWidth(i, setWidth(60));
                        } else if (i == 6) {
                            sheet.setColumnWidth(i, setWidth(22));
                        } else if (i == 7) {
                            sheet.setColumnWidth(i, setWidth(60));
                        } else if (i == 8) {
                            sheet.setColumnWidth(i, setWidth(20));
                        } else if (i == 9) {
                            sheet.setColumnWidth(i, setWidth(14));
                        } else if (i == 10) {
                            sheet.setColumnWidth(i, setWidth(18));
                        } else if (i == 11) {
                            sheet.setColumnWidth(i, setWidth(14));
                        } else if (i == 12) {
                            sheet.setColumnWidth(i, setWidth(20));
                        }
                    }
                    returnObject = workbook;
                }
            }
        } catch (Exception e) {
            throw e;
        }

        return returnObject;

    }

    private String getWhere(Report parameters, String user) throws SQLException {

        String where = "";

        if (parameters.getApplicationtype() != null) {
            where += "AND ADVT.ADVERTISEMENTTYPEID = '" + parameters.getApplicationtype() + "' ";
        }
        if (parameters.getStartdate() != null && parameters.getStartdate() != null) {
            where += "AND ADV.CREATEDDATETIME BETWEEN '" + parameters.getStartdate() + "' AND '" + parameters.getStartdate() + "' ";
        }

        return where;
    }

    private SXSSFWorkbook createExcelTopSection(HashMap<String, Object> parameterMap, String user, Date date) throws Exception {
        SXSSFWorkbook workbook = new SXSSFWorkbook();
        SXSSFSheet sheet = workbook.createSheet("Customer Detail Report");

        XSSFCellStyle fontBoldedCell = (XSSFCellStyle) ExcelCommon.getFontBoldedTitleCell(workbook);
        XSSFCellStyle style = (XSSFCellStyle) workbook.createCellStyle();

        XSSFFont font = (XSSFFont) workbook.createFont();
        font.setFontHeightInPoints((short) 16);
        font.setBold(true);
        style.setFont(font);

        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue("Colombo Municipal Council");
        sheet.addMergedRegion(new CellRangeAddress(
                0, //first row (0-based)
                0, //last row (0-based)
                0, //first column (0-based)
                2 //last column (0-based)
        ));
        style.setVerticalAlignment(CellStyle.ALIGN_LEFT);
        cell.setCellStyle(style);

        row = sheet.createRow(2);
        cell = row.createCell(0);
        cell.setCellValue("Customer Detail Report");
        sheet.addMergedRegion(new CellRangeAddress(
                2, //first row (0-based)
                2, //last row (0-based)
                0, //first column (0-based)
                1 //last column (0-based)
        ));
        style.setVerticalAlignment(CellStyle.ALIGN_LEFT);
        cell.setCellStyle(fontBoldedCell);

        row = sheet.createRow(4);
        cell = row.createCell(0);
        cell.setCellValue("Created By");
        cell = row.createCell(1);
        cell.setCellValue(user);

        row = sheet.createRow(5);
        cell = row.createCell(0);
        cell.setCellValue("Created On");
        cell = row.createCell(1);
        cell.setCellValue(new SimpleDateFormat(CommonVarList.DATE_FORMAT_yyyy_MM_dd_hh_mm_a).format(date));

        return workbook;
    }

    private void createExcelTableHeandeSection(SXSSFWorkbook workbook) throws Exception {

        XSSFCellStyle columnHeaderCell = (XSSFCellStyle) ExcelCommon.getColumnHeadeCellReportWise(workbook, IndexedColors.LAVENDER.getIndex());
        columnHeaderCell.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        columnHeaderCell.setAlignment(HorizontalAlignment.CENTER);
        SXSSFSheet sheet = workbook.getSheetAt(0);
        Row row = sheet.createRow(this.detailRow++);
        row.setHeightInPoints(22);
        int column = 0;

        Cell cell = row.createCell(column++);
        cell.setCellValue("Advertisement ID");
        cell.setCellStyle(columnHeaderCell);

        cell = row.createCell(column++);
        cell.setCellValue("Applicant Name");
        cell.setCellStyle(columnHeaderCell);

        cell = row.createCell(column++);
        cell.setCellValue("Applicant Tel No.");
        cell.setCellStyle(columnHeaderCell);

        cell = row.createCell(column++);
        cell.setCellValue("Client Name");
        cell.setCellStyle(columnHeaderCell);

        cell = row.createCell(column++);
        cell.setCellValue("Client Tel No.");
        cell.setCellStyle(columnHeaderCell);

        cell = row.createCell(column++);
        cell.setCellValue("Responsible Person Name");
        cell.setCellStyle(columnHeaderCell);

        cell = row.createCell(column++);
        cell.setCellValue("Responsible Person Tel No");
        cell.setCellStyle(columnHeaderCell);

        cell = row.createCell(column++);
        cell.setCellValue("Advertisement Type");
        cell.setCellStyle(columnHeaderCell);

        cell = row.createCell(column++);
        cell.setCellValue("Advertisement Status");
        cell.setCellStyle(columnHeaderCell);

        cell = row.createCell(column++);
        cell.setCellValue("Property Type");
        cell.setCellStyle(columnHeaderCell);

        cell = row.createCell(column++);
        cell.setCellValue("Illuminiation Status");
        cell.setCellStyle(columnHeaderCell);

        cell = row.createCell(column++);
        cell.setCellValue("Legal Status");
        cell.setCellStyle(columnHeaderCell);

        cell = row.createCell(column++);
        cell.setCellValue("Advertisement Height");
        cell.setCellStyle(columnHeaderCell);

    }

    private void createExcelTableRow(SXSSFWorkbook workbook, SXSSFSheet sheet, int count, int newFileStatus, Advertisement advdata) throws Exception {

        XSSFCellStyle rowColumnCell = null;
        Row existingRow = null;
        DataFormat format = workbook.createDataFormat();
        int column = 0;

        Row row = sheet.createRow(this.detailRow++);

        if (count == 0) {
            rowColumnCell = (XSSFCellStyle) ExcelCommon.getRowColumnCell(workbook);
        } else {
            if (newFileStatus == 1) {
                rowColumnCell = (XSSFCellStyle) ExcelCommon.getRowColumnCell(workbook);
            } else {
                existingRow = sheet.getRow(row.getRowNum() - 1);
                Cell oldCell = existingRow.getCell(column);
                rowColumnCell = (XSSFCellStyle) oldCell.getCellStyle();
            }
        }

        Cell cell = row.createCell(column++);
        cell.setCellValue(advdata.getAdverisementid());
        rowColumnCell.setAlignment(HorizontalAlignment.RIGHT);
        cell.setCellStyle(rowColumnCell);

        cell = row.createCell(column++);
        cell.setCellValue(advdata.getNameofapplicant());

        cell = row.createCell(column++);
        cell.setCellValue(advdata.getApplicanttelno());

        cell = row.createCell(column++);
        cell.setCellValue(advdata.getNameofclient());

        cell = row.createCell(column++);
        cell.setCellValue(advdata.getClienttelno());

        cell = row.createCell(column++);
        cell.setCellValue(advdata.getNameofresponsibleperson());

        cell = row.createCell(column++);
        cell.setCellValue(advdata.getResponsiblepersontelno());

        cell = row.createCell(column++);
        cell.setCellValue(advdata.getAdvertisementtype());

        cell = row.createCell(column++);
        cell.setCellValue(advdata.getAdvertisementstatus());;

        cell = row.createCell(column++);
        cell.setCellValue(advdata.getPropertytype());

        cell = row.createCell(column++);
        cell.setCellValue(advdata.getLightningtype());

        cell = row.createCell(column++);
        cell.setCellValue(advdata.getLeagalstatus());

        cell = row.createCell(column++);
        rowColumnCell.setAlignment(HorizontalAlignment.RIGHT);
        cell.setCellValue(advdata.getHeight());

        this.rowCount++;
    }

    private void writeTemporaryFile(SXSSFWorkbook workbook, int fileCount, String directory) throws Exception {
        File file;
        FileOutputStream outputStream = null;
        try {
            SXSSFSheet sheet = workbook.getSheetAt(0);
            for (int i = 0; i < this.columnCount; i++) {
                if (i == 0) {
                    sheet.setColumnWidth(i, setWidth(14));
                } else if (i == 1) {
                    sheet.setColumnWidth(i, setWidth(60));
                } else if (i == 2) {
                    sheet.setColumnWidth(i, setWidth(18));
                } else if (i == 3) {
                    //int width = (int) (maxCharacter * 1.14388);
                    sheet.setColumnWidth(i, setWidth(60));
                } else if (i == 4) {
                    sheet.setColumnWidth(i, setWidth(18));
                } else if (i == 5) {
                    sheet.setColumnWidth(i, setWidth(60));
                } else if (i == 6) {
                    sheet.setColumnWidth(i, setWidth(22));
                } else if (i == 7) {
                    sheet.setColumnWidth(i, setWidth(60));
                } else if (i == 8) {
                    sheet.setColumnWidth(i, setWidth(20));
                } else if (i == 9) {
                    sheet.setColumnWidth(i, setWidth(14));
                } else if (i == 10) {
                    sheet.setColumnWidth(i, setWidth(18));
                } else if (i == 11) {
                    sheet.setColumnWidth(i, setWidth(14));
                } else if (i == 12) {
                    sheet.setColumnWidth(i, setWidth(20));
                }
            }
            file = new File(directory);
            if (!file.exists()) {
                System.out.println("Directory created or not : " + file.mkdirs());
            }
            if (fileCount > 0) {
                file = new File(directory + File.separator + "Customer Report" + fileCount + ".xlsx");
            } else {
                file = new File(directory + File.separator + "Customer Report.xlsx");
            }
            outputStream = new FileOutputStream(file);
            workbook.write(outputStream);
        } catch (IOException e) {
            throw e;
        } finally {
            if (outputStream != null) {
                outputStream.flush();
                outputStream.close();
            }
        }
    }

    public int setWidth(int colsize) {

        return ((int) (colsize * 1.14388)) * 256;

    }

}
