/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmc.service.advertisement;

import com.cmc.model.advertisement.Advertisement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author : KAsun Udayanaga
 * @Document : AdvertisementService
 * @Created on : Oct 28, 2017, 6:39:33 PM
 */
public interface AdvertisementService {

    public void loadPageComponent(Map<String, Object> model) throws SQLException;

    public long crateApplication(final Advertisement advertisement) throws SQLException;
    
    public void updateApplication(final Advertisement advertisement) throws SQLException;

    public String getTableData(HttpServletRequest request, Advertisement parameters, String user) throws SQLException;
    
    public Double calculateCosts(final Advertisement advertisement) throws SQLException;
    
    public Advertisement getApplication(int advertisementId) throws SQLException;

}
