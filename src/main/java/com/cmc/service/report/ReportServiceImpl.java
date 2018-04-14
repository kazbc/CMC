/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmc.service.report;

import com.cmc.dao.report.ReportDAO;
import com.cmc.model.report.Report;
import java.util.Date;
import java.util.HashMap;
import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author : KAsun Udayanaga
 * @Document : ReportServiceImpl
 * @Created on : Dec 31, 2017, 4:07:19 PM
 */
@Service("ReportService")
public class ReportServiceImpl implements ReportService{

    @Autowired
    ReportDAO reportDAO;
    
    @Override
    @Transactional
    public Object generateExcelReport(Report parameters, ServletContext context, HashMap<String, Object> parameterMap, String reportBuildPath, String user, Date date) throws Exception {

        return reportDAO.generateExcelReport(parameters, context, parameterMap, reportBuildPath, user, date);
    }
    
}
