/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmc.service.advertisement;

import com.cmc.dao.advertisement.AdvertisementDAO;
import com.cmc.dao.common.CommonDAO;
import com.cmc.model.advertisement.Advertisement;
import com.cmc.model.pricing.Pricing;
import com.cmc.util.varlist.DropdownSqlVarList;
import com.cmc.util.varlist.MasterDataVarList;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author : KAsun Udayanaga
 * @Document : AdvertisementServiceImpl
 * @Created on : Oct 28, 2017, 6:39:57 PM
 */
@Service("advertisementService")
public class AdvertisementServiceImpl implements AdvertisementService {

    @Autowired
    private CommonDAO commonDAO;
    @Autowired
    private AdvertisementDAO advertisementDAO;

    @Override
    public void loadPageComponent(Map<String, Object> model) throws SQLException {
        model.put("advertisementTypeList", commonDAO.getDropdownValueList(String.format(DropdownSqlVarList.CMC_ADVERTISEMENT_TYPE)));
        model.put("cityLimitList", commonDAO.getDropdownValueList(String.format(DropdownSqlVarList.CMC_CITY_LIMITS)));
        model.put("propertyTypeList", commonDAO.getDropdownValueList(String.format(DropdownSqlVarList.CMC_PROPERTY_TYPE)));
        model.put("advertisementStatusList", commonDAO.getDropdownValueList(String.format(DropdownSqlVarList.CMC_ADV_STATUS, MasterDataVarList.ADVERTISEMENT_STATUS_CATEGORY)));
        model.put("advertisementAuthList", commonDAO.getDropdownValueList(String.format(DropdownSqlVarList.CMC_ADV_STATUS, MasterDataVarList.ADVERTISEMENT_AUTHORIZATION_STATUS_CATEGORY)));
        model.put("advertisementLightningList", commonDAO.getDropdownValueList(String.format(DropdownSqlVarList.CMC_ADV_STATUS, MasterDataVarList.ADVERTISEMENT_LIGHTING_STATUS_CATEGORY)));
    }

    @Override
    public long crateApplication(final Advertisement advertisement) throws SQLException {
        return advertisementDAO.crateApplication(advertisement);
    }

    @Override
    public void updateApplication(final Advertisement advertisement) throws SQLException {
        advertisementDAO.updateApplication(advertisement);
    }

    @Override
    public String getTableData(HttpServletRequest request, Advertisement parameters, String user) throws SQLException {

        List<Advertisement> advlist = advertisementDAO.getTableData(parameters, user);
        JSONArray rows = new JSONArray();

        for (Advertisement advertisement : advlist) {
            JSONObject jsonobject = new JSONObject();

            jsonobject.put("applicantname", advertisement.getNameofapplicant());
            jsonobject.put("applicanttelno", advertisement.getApplicanttelno());
            jsonobject.put("advtype", advertisement.getAdvertisementtype());
            jsonobject.put("advstatus", advertisement.getAdvertisementstatus());
            jsonobject.put("propertytype", advertisement.getPropertytype());
            jsonobject.put("createddate", advertisement.getCreateddate());
            String action = "<div class=\"row\">"
                    + "<div class=\"col-xs-3\"><a href='" + request.getContextPath() + "/view/advertisement?advertisementid=" + advertisement.getAdverisementid() + "' class=\"btn btn-simple btn-info btn-icon\"><i class=\"material-icons\" title=\"View\">remove_red_eye</i></a></div>"
                    + "<div class=\"col-xs-3\"><a href='" + request.getContextPath() + "/edit/advertisement?advertisementid=" + advertisement.getAdverisementid() + "' class=\"btn btn-simple btn-warning btn-icon\"><i class=\"material-icons\" title=\"Edit\">mode_edit</i></a></div>"
                    + "</div>";

            jsonobject.put("action", action);

            rows.put(jsonobject);
        }

        return rows.toString();

    }

    @Override
    public Double calculateCosts(final Advertisement advertisement) throws SQLException {

        Pricing pricing = advertisementDAO.calculateCosts(advertisement);
        Double pricepertotalsf;

        if (advertisement.getLightningtype().equals("5")) {
            Double pricepersf = pricing.getAmount() + pricing.getAmountwithlights();
            pricepertotalsf = pricepersf * Double.parseDouble(advertisement.getDimension());
        } else {
            pricepertotalsf = pricing.getAmount() * Double.parseDouble(advertisement.getDimension());
        }

        return pricepertotalsf;
    }

    @Override
    public Advertisement getApplication(int advertisementId) throws SQLException {
        return advertisementDAO.getApplication(advertisementId);
    }

}
