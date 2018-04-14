package com.cmc.controller.report;

import com.cmc.controller.adverticement.AdvertisementController;
import com.cmc.dao.common.CommonDAO;
import com.cmc.dao.common.CommonDAOImpl;
import com.cmc.model.advertisement.Advertisement;
import com.cmc.model.report.Report;
import com.cmc.service.advertisement.AdvertisementService;
import com.cmc.service.report.ReportService;
import com.cmc.util.common.Common;
import com.cmc.util.varlist.CommonVarList;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * @Author : KAsun Udayanaga
 * @Document : reportcontroller
 * @Created on : Dec 31, 2017, 2:56:48 PM
 */
@Controller
public class Reportcontroller {

    @Autowired
    CommonDAO commonDAO;

    @Autowired
    ServletContext context;
    
    @Autowired
    ReportService reportService;
    
    @Autowired
    AdvertisementService advertisementService;

    @RequestMapping(value = "/report", method = RequestMethod.GET)
    public String createview(ModelMap model) {
        try {
            model.put("root", "Report");
            model.put("page", "Download");
            model.put("reportForm", new Report());
            advertisementService.loadPageComponent(model);
        } catch (Exception exception) {
            Logger.getLogger(Reportcontroller.class.getName()).log(Level.SEVERE, null, exception);
            exception.printStackTrace();
        }
        return "report/report";
    }

    @RequestMapping(value = "/report", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<byte[]> getReport(@ModelAttribute("reportForm") Report parameters, HttpServletResponse response, HttpSession session) throws Exception {

        String user = (String) session.getAttribute("username");
        HashMap<String, Object> parameterMap;
        ResponseEntity<byte[]> outPutFile = null;
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");

        try {
            Date date = commonDAO.getCurentTimesStamp();
            parameterMap = this.getDisplayParameterMap(parameters, user, date);

            HttpHeaders headers = new HttpHeaders();

            ByteArrayOutputStream outputStream;
            byte[] outputFile = null;
            String filename = "";
            if (parameters.getAction().equalsIgnoreCase("EXCEL")) {
                Object object = this.getExcelReport(parameters, user, parameterMap, date);
                if (object instanceof SXSSFWorkbook) {
                    /*To download single excel file*/
                    headers.setContentType(MediaType.parseMediaType("application/vnd.ms-excel"));
                    filename = "CUSTOMER_REPORT_" + Common.getStringFormatDate(CommonVarList.DATE_FORMAT_yyyyMMddHHmmss, date) + ".xlsx";

                    SXSSFWorkbook workbook = (SXSSFWorkbook) object;
                    outputStream = new ByteArrayOutputStream();
                    ZipSecureFile.setMinInflateRatio(0);
                    workbook.write(outputStream);

                    outputFile = outputStream.toByteArray();
                    workbook.dispose();// dispose of temporary files backing this workbook on disk
                } else if (object instanceof ByteArrayOutputStream) {
                    /*To download zip file*/
                    headers.setContentType(MediaType.parseMediaType("application/octet-stream"));
                    filename = "CUSTOMER_REPORT_" + Common.getStringFormatDate(CommonVarList.DATE_FORMAT_yyyyMMddHHmmss, date) + ".zip";

                    outputStream = (ByteArrayOutputStream) object;
                    outputFile = outputStream.toByteArray();
                }
            }

            headers.setContentDispositionFormData(filename, filename);
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
            outPutFile = new ResponseEntity<>(outputFile, headers, HttpStatus.OK);
            response.addCookie(new Cookie("fileDownloadToken", parameters.getDownload_token_value_id()));

        } catch (Exception e) {
            Logger.getLogger(Reportcontroller.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
        return outPutFile;
    }

    private Object getExcelReport(Report parameters, String user, HashMap<String, Object> parameterMap, Date date) throws Exception {
        Object object = null;
        try {
            String reportBuildPath = context.getInitParameter(CommonVarList.CONTEXT_PARAM_REPORT_BUILD_PATH);

            reportBuildPath += File.separator + user + File.separator + "EXCEL";
            File file = new File(reportBuildPath);
            if (!file.exists()) {
                file.mkdirs();
            } else {
                for (File temp : file.listFiles()) {
                    temp.delete();
                }
            }

            object = reportService.generateExcelReport(parameters, context, parameterMap, reportBuildPath, user, date);

        } catch (Exception e) {
            throw e;
        }
        return object;
    }

    private HashMap<String, Object> getDisplayParameterMap(Report parameters, String user, Date date) throws Exception {

        HashMap<String, Object> parameterMap = new HashMap<>();
        parameterMap.put("CMC_CRM_RPT_TITLE", "CUSTOMER REPORT");
        parameterMap.put("CMC_RPT_USERNAME", user);
        parameterMap.put("CMC_RPT_DATE", new SimpleDateFormat(CommonVarList.DATE_FORMAT_yyyy_MM_dd_hh_mm_a).format(date));
//        parameterMap.put("CMC_RPT_START_DATE", new SimpleDateFormat(CommonVarList.DATE_FORMAT_yyyy_MM_dd_hh_mm_a).format(parameters.getStartdate()));
//        parameterMap.put("CMC_RPT_END_DATE", new SimpleDateFormat(CommonVarList.DATE_FORMAT_yyyy_MM_dd_hh_mm_a).format(parameters.getEnddate()));

        return parameterMap;
    }

}
