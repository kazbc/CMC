/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmc.dao.common;

import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

/**
 * @Author : KAsun Udayanaga
 * @Document : CommonDAO
 * @Created on : Oct 28, 2017, 7:18:11 PM
 */
public interface CommonDAO {

    public Map<String, String> getDropdownValueList(String query) throws SQLException;
    
    public Date getCurentTimesStamp() throws Exception;
}
