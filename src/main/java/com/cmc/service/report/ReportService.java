/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmc.service.report;

import com.cmc.model.report.Report;
import java.util.Date;
import java.util.HashMap;
import javax.servlet.ServletContext;

/**
 * @Author : KAsun Udayanaga
 * @Document : ReportService
 * @Created on : Dec 31, 2017, 4:05:54 PM
 */
public interface ReportService {

    public Object generateExcelReport(Report parameters, ServletContext context, HashMap<String, Object> parameterMap, String reportBuildPath, String user, Date date) throws Exception;

}
