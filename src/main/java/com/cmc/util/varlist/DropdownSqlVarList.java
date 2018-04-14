/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmc.util.varlist;

/**
 * @Author : KAsun Udayanaga
 * @Document : DropdoenSqlVarList
 * @Created on : Oct 27, 2017, 3:33:29 PM
 */
public class DropdownSqlVarList {

    public static final String CMC_ADVERTISEMENT_TYPE = "SELECT AT.ADVERTISEMENTTYPEID AS ID, AT.DESCRIPTION "
            + "FROM advertisemettype AT";
    public static final String CMC_CITY_LIMITS = "SELECT CL.CITYLIMITID AS ID, CL.DESCRIPTION "
            + "FROM clitylimits CL";
    public static final String CMC_ADV_STATUS = "SELECT S.STATUSID AS ID, S.DESCRIPTION "
            + "FROM status S "
            + "WHERE S.STATUSCATEGORY = %d "
            + "ORDER BY S.SORTID ";
    public static final String CMC_PROPERTY_TYPE = "SELECT PT.PROPERTYTYPEID AS ID, PT.DESCRIPTION "
            + "FROM propertytype PT "
            + "ORDER BY PT.SORTID ";
}
