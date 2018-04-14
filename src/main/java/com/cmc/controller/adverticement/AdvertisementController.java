/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmc.controller.adverticement;

import com.cmc.model.advertisement.Advertisement;
import com.cmc.service.advertisement.AdvertisementService;
import com.cmc.util.common.Common;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author : KAsun Udayanaga
 * @Document : AdvertisementController
 * @Created on : Sep 9, 2017, 12:50:00 PM
 */
@Controller
//@RequestMapping("/advertisement")
public class AdvertisementController {

    @Autowired
    AdvertisementService advertisementService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String createview(ModelMap model) {
        try {
            model.put("root", "Advertisement");
            model.put("page", "Create");
            model.put("advertisementForm", new Advertisement());
            advertisementService.loadPageComponent(model);
        } catch (Exception exception) {
            Logger.getLogger(AdvertisementController.class.getName()).log(Level.SEVERE, null, exception);
            exception.printStackTrace();
        }
        return "advertisement/createadvertisement";
    }

    @RequestMapping(value = "/view/advertisement", method = RequestMethod.GET)
    public String advertisementView(@RequestParam(value = "advertisementid", required = false) int advertisementid,
            HttpSession session,
            @ModelAttribute("advertisementView") Advertisement advertisement,
            ModelMap model) throws SQLException {

        try {
            advertisementService.loadPageComponent(model);
            advertisement = advertisementService.getApplication(advertisementid);
            model.addAttribute("advertisementView", advertisement);
        } catch (Exception e) {
            Logger.getLogger(AdvertisementController.class.getName()).log(Level.SEVERE, null, e);
        }

        return "advertisement/viewadvertisemenet";

    }
    
    @RequestMapping(value = "/edit/advertisement", method = RequestMethod.GET)
    public String advertisemnetEdit(@RequestParam(value = "advertisementid", required = false) int advertisementid,
            HttpSession session,
            @ModelAttribute("advertisementEdit") Advertisement advertisement,
            ModelMap model) throws SQLException {

        try {
            advertisementService.loadPageComponent(model);
            advertisement = advertisementService.getApplication(advertisementid);
            model.addAttribute("advertisementEdit", advertisement);
        } catch (Exception e) {
            Logger.getLogger(AdvertisementController.class.getName()).log(Level.SEVERE, null, e);
        }

        return "advertisement/editadvertisement";

    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String callSearch(ModelMap model) {
        return "advertisement/searchadvertisement";
    }

    @RequestMapping(value = "/searchapplicationdata", method = RequestMethod.POST)
    public @ResponseBody
    String loadTableData(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Advertisement advertisement = new Advertisement();
        advertisement.setInput(request.getParameter("input"));
        return advertisementService.getTableData(request, advertisement, null);
    }

    @RequestMapping(value = "/createadvertisement", method = RequestMethod.POST)
    public @ResponseBody
    String createApplication(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String advertisementdata = request.getParameter("advertisementdata");
        Advertisement advertisement = (Advertisement) new ObjectMapper().readValue(advertisementdata, Advertisement.class);

        JSONObject object = new JSONObject();
        try {
            long advertisementId = advertisementService.crateApplication(advertisement);
            object.put("CODE", "SUCCESS");
            object.put("MESSAGE", advertisementId);
        } catch (Exception e) {
            object.put("CODE", "ERROR");
            object.put("MESSAGE", "Error occured while creating the application");
            Logger.getLogger(AdvertisementController.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
        return object.toString();
    }
    
    @RequestMapping(value = "/updateadvertisement", method = RequestMethod.POST)
    public @ResponseBody
    String updateApplication(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String advertisementdata = request.getParameter("advertisementdata");
        Advertisement advertisement = (Advertisement) new ObjectMapper().readValue(advertisementdata, Advertisement.class);

        JSONObject object = new JSONObject();
        try {
            advertisementService.updateApplication(advertisement);
            object.put("CODE", "SUCCESS");
            object.put("MESSAGE", advertisement.getAdverisementid());
        } catch (Exception e) {
            object.put("CODE", "ERROR");
            object.put("MESSAGE", "Error occured while updating the application");
            Logger.getLogger(AdvertisementController.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
        return object.toString();
    }

    @RequestMapping(value = "/calculatecosts", method = RequestMethod.POST)
    public @ResponseBody
    String calculateBannerCosts(HttpServletRequest request, HttpServletResponse response) throws Exception {

        JSONObject object = new JSONObject();
        String advertisementdata = request.getParameter("advertisementdata");
        Advertisement advertisement = (Advertisement) new ObjectMapper().readValue(advertisementdata, Advertisement.class);
        object.put("CODE", "SUCCESS");
        object.put("MESSAGE", Common.getGroupedDoubleNumber(advertisementService.calculateCosts(advertisement)));
        return object.toString();
    }

}
