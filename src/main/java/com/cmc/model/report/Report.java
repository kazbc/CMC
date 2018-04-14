/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmc.model.report;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Author : KAsun Udayanaga
 * @Document : Report
 * @Created on : Dec 31, 2017, 3:27:25 PM
 */
public class Report {

    private String action;
    private String download_token_value_id;
    private String applicationtype;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startdate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date enddate;

    /**
     * @return the action
     */
    public String getAction() {
        return action;
    }

    /**
     * @param action the action to set
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * @return the download_token_value_id
     */
    public String getDownload_token_value_id() {
        return download_token_value_id;
    }

    /**
     * @param download_token_value_id the download_token_value_id to set
     */
    public void setDownload_token_value_id(String download_token_value_id) {
        this.download_token_value_id = download_token_value_id;
    }

    /**
     * @return the applicationtype
     */
    public String getApplicationtype() {
        return applicationtype;
    }

    /**
     * @param applicationtype the applicationtype to set
     */
    public void setApplicationtype(String applicationtype) {
        this.applicationtype = applicationtype;
    }

    /**
     * @return the startdate
     */
    public Date getStartdate() {
        return startdate;
    }

    /**
     * @param startdate the startdate to set
     */
    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    /**
     * @return the enddate
     */
    public Date getEnddate() {
        return enddate;
    }

    /**
     * @param enddate the enddate to set
     */
    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

}
