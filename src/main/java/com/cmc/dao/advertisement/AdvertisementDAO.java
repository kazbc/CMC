/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmc.dao.advertisement;

import com.cmc.model.advertisement.Advertisement;
import com.cmc.model.pricing.Pricing;
import java.sql.SQLException;
import java.util.List;

/**
 * @Author : KAsun Udayanaga
 * @Document : AdvertisementDAO
 * @Created on : Nov 3, 2017, 2:26:15 PM
 */
public interface AdvertisementDAO {

    public long crateApplication(final Advertisement advertisement) throws SQLException;
    
    public void updateApplication(final Advertisement advertisement) throws SQLException;

    public List<Advertisement> getTableData(Advertisement parameters, String user) throws SQLException;

    public Pricing calculateCosts(final Advertisement advertisement) throws SQLException;

    public Advertisement getApplication(int advertisementId) throws SQLException;

}
