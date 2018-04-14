/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmc.dao.report;

import com.cmc.model.report.Report;
import java.util.Date;
import java.util.HashMap;
import javax.servlet.ServletContext;

/**
 * @Author : KAsun Udayanaga
 * @Document : ReportDAO
 * @Created on : Dec 31, 2017, 4:08:37 PM
 */
public interface ReportDAO {

    public Object generateExcelReport(Report parameters, ServletContext context, HashMap<String, Object> parameterMap, String reportBuildPath, String user, Date date) throws Exception;
    
}
