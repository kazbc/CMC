/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmc.dao.advertisement;

import com.cmc.model.advertisement.Advertisement;
import com.cmc.model.pricing.Pricing;
import com.cmc.util.common.Common;
import com.cmc.util.varlist.CommonVarList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

/**
 * @Author : KAsun Udayanaga
 * @Document : AdvertisementDAOImpl
 * @Created on : Nov 3, 2017, 2:26:29 PM
 */
@Repository("advertisementDAO")
public class AdvertisementDAOImpl implements AdvertisementDAO {

    @Autowired
    private DataSource dataSource;

    public JdbcTemplate getJdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate;
    }

    @Override
    public long crateApplication(final Advertisement advertisement) throws SQLException {

        final String applicatquery = "INSERT INTO applicantcontact (ADDRESSNO, "
                + "LOCATION, "
                + "CITYLIMIT, "
                + "CREATEDDATETIME, "
                + "LASTUPDATEDDATETIME, "
                + "CREATEDUSER) VALUES (?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ?)";
        final KeyHolder keyHolderApplicant = new GeneratedKeyHolder();
        new JdbcTemplate(dataSource).update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement statement = connection.prepareStatement(applicatquery, new String[]{"APPLICANTCONTACTID"});
                statement.setString(1, advertisement.getApplicantaddressno());
                statement.setString(2, advertisement.getApplicantlocation());
                statement.setString(3, advertisement.getApplicantcitylimit());
                statement.setString(4, "admin");
                return statement;
            }
        }, keyHolderApplicant);

        final String clientcontact = "INSERT INTO clientcontact (ADDRESSNO, "
                + "LOCATION, "
                + "CITYLIMIT, "
                + "CREATEDDATETIME, "
                + "LASTUPDATEDDATETIME, "
                + "CREATEDUSER) VALUES (?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ?)";
        final KeyHolder keyHolderClient = new GeneratedKeyHolder();
        new JdbcTemplate(dataSource).update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement statement = connection.prepareStatement(clientcontact, new String[]{"CLIENTCONTACTID"});
                statement.setString(1, advertisement.getApplicantaddressno());
                statement.setString(2, advertisement.getApplicantlocation());
                statement.setString(3, advertisement.getApplicantcitylimit());
                statement.setString(4, "admin");
                return statement;
            }
        }, keyHolderClient);

        final String responsiblepersoncontact = "INSERT INTO responsiblepersoncontact (ADDRESSNO, "
                + "LOCATION, "
                + "CITYLIMIT, "
                + "CREATEDDATETIME, "
                + "LASTUPDATEDDATETIME, "
                + "CREATEDUSER) VALUES (?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ?)";
        final KeyHolder keyHolderResponsiblePersonContact = new GeneratedKeyHolder();
        new JdbcTemplate(dataSource).update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement statement = connection.prepareStatement(responsiblepersoncontact, new String[]{"RESPERSONCONTACTID"});
                statement.setString(1, advertisement.getApplicantaddressno());
                statement.setString(2, advertisement.getApplicantlocation());
                statement.setString(3, advertisement.getApplicantcitylimit());
                statement.setString(4, "admin");
                return statement;
            }
        }, keyHolderResponsiblePersonContact);

        final String advertisementquery = "INSERT INTO advertisemet (APPLICANTNAME, "
                + "APPLICANTADDRESS, "
                + "APPLICANTTELNO, "
                + "CLIENTNAME, "
                + "CLIENTADDRESS, "
                + "CLIENTTELNO, "
                + "RESPONSIBLEPERSONNAME, "
                + "RESPONSIBLEPERSONADDRESS, "
                + "RESPONSIBLEPERSONTELNO, "
                + "ADVERTISEMENTTYPE, "
                + "ADVERTISEMETSTATUS, "
                + "PROPERTYTYPE, "
                + "ILLUMINATIONSTATUS, "
                + "LEAGALILLEGALSTATUS, "
                + "ADVERTISEMENTHEIGHT, "
                + "ADVERTISEMENTDHEIGHT, "
                + "ADVERTISEMENTDWIDTH, "
                + "TOTALCOSTS, "
                + "CREATEDDATETIME, "
                + "LASTUPDATEDDATETIME, "
                + "CREATEDUSER) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ?)";
        final KeyHolder keyHolderApplication = new GeneratedKeyHolder();
        new JdbcTemplate(dataSource).update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement statement = connection.prepareStatement(advertisementquery, new String[]{"ADVERTISEMENTID"});
                statement.setString(1, advertisement.getNameofapplicant());
                statement.setInt(2, keyHolderApplicant.getKey().intValue());
                statement.setString(3, advertisement.getApplicanttelno());
                statement.setString(4, advertisement.getNameofclient());
                statement.setInt(5, keyHolderClient.getKey().intValue());
                statement.setString(6, advertisement.getClienttelno());
                statement.setString(7, advertisement.getNameofresponsibleperson());
                statement.setInt(8, keyHolderResponsiblePersonContact.getKey().intValue());
                statement.setString(9, advertisement.getResponsiblepersontelno());
                statement.setString(10, advertisement.getAdvertisementtype());
                statement.setString(11, advertisement.getAdvertisementstatus());
                statement.setString(12, advertisement.getPropertytype());
                statement.setString(13, advertisement.getLightningtype());
                statement.setString(14, advertisement.getLeagalstatus());
                statement.setString(15, advertisement.getHeight());
                statement.setString(16, advertisement.getAdvheight());
                statement.setString(17, advertisement.getAdvwidth());
                statement.setString(18, advertisement.getTotalcosts());
                statement.setString(19, "admin");
                return statement;
            }
        }, keyHolderApplication);

        return keyHolderApplication.getKey().longValue();
    }

    @Override
    public void updateApplication(final Advertisement advertisement) throws SQLException {

        String query = "UPDATE advertisemet ADV "
                + "LEFT OUTER JOIN applicantcontact AC ON AC.APPLICANTCONTACTID = ADV.APPLICANTADDRESS "
                + "LEFT OUTER JOIN clientcontact CL ON CL.CLIENTCONTACTID = ADV.CLIENTADDRESS  "
                + "LEFT OUTER JOIN responsiblepersoncontact RPC ON RPC.RESPERSONCONTACTID = ADV.RESPONSIBLEPERSONADDRESS "
                + "SET  ADV.APPLICANTNAME = ?, "
                + "     AC.ADDRESSNO = ?, "
                + "     AC.LOCATION = ?, "
                + "     AC.CITYLIMIT = ?, "
                + "     ADV.CLIENTNAME = ?, "
                + "     CL.ADDRESSNO = ?, "
                + "     CL.LOCATION = ?, "
                + "     CL.CITYLIMIT = ?, "
                + "     ADV.RESPONSIBLEPERSONNAME = ?, "
                + "     RPC.ADDRESSNO = ?, "
                + "     RPC.LOCATION = ?, "
                + "     RPC.CITYLIMIT= ?, "
                + "     ADV.ADVERTISEMENTTYPE = ?, "
                + "     ADV.ADVERTISEMETSTATUS = ?, "
                + "     ADV.LEAGALILLEGALSTATUS = ?, "
                + "     ADV.ILLUMINATIONSTATUS = ?, "
                + "     ADV.PROPERTYTYPE = ?, "
                + "     ADV.ADVERTISEMENTDHEIGHT = ?, "
                + "     ADV.ADVERTISEMENTDWIDTH = ?, "
                + "     ADV.ADVERTISEMENTHEIGHT = ?, "
                + "     ADV.TOTALCOSTS = ? "
                + "WHERE ADV.ADVERTISEMENTID = ?";

        new JdbcTemplate(dataSource).update(query, advertisement.getNameofapplicant(),
                advertisement.getApplicantaddressno(),
                advertisement.getApplicantlocation(),
                advertisement.getApplicantcitylimit(),
                advertisement.getNameofclient(),
                advertisement.getClientaddressno(),
                advertisement.getClientlocation(),
                advertisement.getClientcitylimit(),
                advertisement.getNameofresponsibleperson(),
                advertisement.getResponsiblepersonno(),
                advertisement.getResponsiblepersonlocation(),
                advertisement.getResponsiblepersoncitylimit(),
                advertisement.getAdvertisementtype(),
                advertisement.getAdvertisementstatus(),
                advertisement.getLeagalstatus(),
                advertisement.getLightningtype(),
                advertisement.getPropertytype(),
                advertisement.getAdvheight(),
                advertisement.getAdvwidth(),
                advertisement.getHeight(),
                advertisement.getTotalcosts(),
                advertisement.getAdverisementid());

    }

    @Override
    public List<Advertisement> getTableData(Advertisement parameters, String user) throws SQLException {

        List<Advertisement> advdatalist;

        String selectquery = "SELECT    ADV.ADVERTISEMENTID AS ADVERTISEMENTID, "
                + "                     ADV.APPLICANTNAME AS APPLICANTNAME, "
                + "                     ADV.APPLICANTTELNO AS APLLICANTTELNO, "
                + "                     ADV.CLIENTNAME AS CLIENTNAME, "
                + "                     ADV.CLIENTTELNO AS CLIENTTELNO, "
                + "                     ADV.RESPONSIBLEPERSONNAME AS RESPONSIBLEPERSONNAME, "
                + "                     ADV.RESPONSIBLEPERSONTELNO AS RESPONSIBLEPERSONTELNO, "
                + "                     ADVT.DESCRIPTION AS ADVERTISEMENTTYPE, "
                + "                     ADVS.DESCRIPTION AS ADVERTISEMENTSTATUS, "
                + "                     PT.DESCRIPTION AS PROPEERTYTYPE, "
                + "                     ILS.DESCRIPTION AS ILUMINATIONSTATUS, "
                + "                     LIS.DESCRIPTION AS LEAGALILLIGALSTATUS, "
                + "                     ADV.ADVERTISEMENTHEIGHT AS ADVERTISEMENTHEIGHT, "
                + "                     ADV.CREATEDDATETIME AS CREATEDDATETIME "
                + "FROM advertisemet ADV "
                + "LEFT OUTER JOIN advertisemettype ADVT ON ADVT.ADVERTISEMENTTYPEID = ADV.ADVERTISEMENTTYPE "
                + "LEFT OUTER JOIN applicantcontact AC ON AC.APPLICANTCONTACTID = ADV.APPLICANTADDRESS "
                + "LEFT OUTER JOIN clientcontact CL ON CL.CLIENTCONTACTID = ADV.CLIENTADDRESS "
                + "LEFT OUTER JOIN responsiblepersoncontact RPC ON RPC.RESPERSONCONTACTID = ADV.RESPONSIBLEPERSONADDRESS "
                + "LEFT OUTER JOIN propertytype PT ON PT.PROPERTYTYPEID = ADV.PROPERTYTYPE "
                + "LEFT JOIN status ADVS ON ADVS.STATUSID = ADV.ADVERTISEMETSTATUS "
                + "LEFT JOIN status ILS ON ILS.STATUSID = ADV.ILLUMINATIONSTATUS "
                + "LEFT JOIN status LIS ON LIS.STATUSID = ADV.LEAGALILLEGALSTATUS "
                + "WHERE 1 = 1 "
                + "ORDER BY ADV.CREATEDDATETIME ASC";

        selectquery = selectquery.replace(":searchstr", this.getWhere(parameters));
        advdatalist = this.getJdbcTemplate().query(selectquery, new Object[]{},
                new RowMapper<Advertisement>() {
                    @Override
                    public Advertisement mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Advertisement advdata = new Advertisement();
                        advdata.setAdverisementid(rs.getString("ADVERTISEMENTID"));
                        advdata.setNameofapplicant(rs.getString("APPLICANTNAME"));
                        advdata.setApplicanttelno(rs.getString("APLLICANTTELNO"));
                        advdata.setNameofclient(rs.getString("CLIENTNAME"));
                        advdata.setClienttelno(rs.getString("CLIENTTELNO"));
                        advdata.setNameofresponsibleperson(rs.getString("RESPONSIBLEPERSONNAME"));
                        advdata.setResponsiblepersontelno(rs.getString("RESPONSIBLEPERSONTELNO"));
                        advdata.setAdvertisementtype(rs.getString("ADVERTISEMENTTYPE"));
                        advdata.setAdvertisementstatus(rs.getString("ADVERTISEMENTSTATUS"));
                        advdata.setPropertytype(rs.getString("PROPEERTYTYPE"));
                        advdata.setLightningtype(rs.getString("ILUMINATIONSTATUS"));
                        advdata.setLeagalstatus(rs.getString("LEAGALILLIGALSTATUS"));
                        advdata.setHeight(rs.getString("ADVERTISEMENTHEIGHT"));
                        advdata.setCreateddate(Common.getStringFormatDate(CommonVarList.DATE_FORMAT_yyyy_MM_dd, rs.getDate("CREATEDDATETIME")));
                        return advdata;
                    }
                });

        return advdatalist;
    }

    private String getWhere(Advertisement parameters) {

        String where = "";

        if (parameters.getInput() != null && !parameters.getInput().equals("") && !parameters.getInput().isEmpty()) {
            where += "OR ADV.APLLICANTTELNO '%" + parameters.getInput().trim() + "%' "
                    + "OR ADV.RESPONSIBLEPERSONTELNO '%" + parameters.getInput().trim() + "%' "
                    + "OR ADV.CLIENTTELNO '%" + parameters.getInput().trim() + "%' "
                    + "OR ADV.APPLICANTNAME '%" + parameters.getInput().trim() + "%' "
                    + "OR ADV.CLIENTNAME '%" + parameters.getInput().trim() + "%' "
                    + "OR ADV.RESPONSIBLEPERSONNAME '%" + parameters.getInput().trim() + "%' ";
        }

        return where;
    }

    @Override
    public Pricing calculateCosts(final Advertisement advertisement) throws SQLException {

        String sql = "SELECT P.AMOUNT, P.AMOUNTWITHLIGHT "
                + "FROM pricing P "
                + "WHERE P.ADVERTISEMENTTYPE = ? ";

        Pricing pricing = (Pricing) this.getJdbcTemplate().queryForObject(
                sql,
                new Object[]{advertisement.getAdvertisementtype()},
                new RowMapper() {
                    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Pricing pricing = new Pricing();
                        pricing.setAmount(rs.getDouble("AMOUNT"));
                        pricing.setAmountwithlights(rs.getDouble("AMOUNTWITHLIGHT"));
                        return pricing;
                    }
                });

        return pricing;
    }

    @Override
    public Advertisement getApplication(int advertisementId) throws SQLException {

        List<Advertisement> advdatalist;

        String selectquery = "SELECT    ADV.ADVERTISEMENTID AS ADVERTISEMENTID,"
                + "                     ADV.APPLICANTNAME AS APPLICANTNAME, "
                + "                     AC.ADDRESSNO AS APPLICANTADDRESS, "
                + "                     AC.CITYLIMIT AS APPLICANTCITYLIMIT, "
                + "                     AC.LOCATION AS APPLICANTLOCATION, "
                + "                     ADV.APPLICANTTELNO AS APLLICANTTELNO, "
                + "                     ADV.CLIENTNAME AS CLIENTNAME, "
                + "                     CL.ADDRESSNO AS CLIENTADDRESSNO, "
                + "                     CL.LOCATION AS CLIENTLOCATION, "
                + "                     CL.CITYLIMIT AS CLIENTCITYLIMIT, "
                + "                     ADV.CLIENTTELNO AS CLIENTTELNO, "
                + "                     ADV.RESPONSIBLEPERSONNAME AS RESPONSIBLEPERSONNAME, "
                + "                     RPC.ADDRESSNO AS RESPONSIBLEPERSONADRESS, "
                + "                     RPC.LOCATION AS RESPONSIBLEPERSONLOCATION, "
                + "                     RPC.CITYLIMIT AS RESPONSIBLEPERSONCITYLIMIT, "
                + "                     ADV.RESPONSIBLEPERSONTELNO AS RESPONSIBLEPERSONTELNO, "
                + "                     ADVT.ADVERTISEMENTTYPEID AS ADVERTISEMENTTYPE, "
                + "                     ADVS.STATUSID AS ADVERTISEMENTSTATUS, "
                + "                     PT.PROPERTYTYPEID AS PROPEERTYTYPE, "
                + "                     ILS.STATUSID AS ILUMINATIONSTATUS, "
                + "                     LIS.STATUSID AS LEAGALILLIGALSTATUS, "
                + "                     ADV.ADVERTISEMENTHEIGHT AS ADVERTISEMENTHEIGHT, "
                + "                     ADV.ADVERTISEMENTDHEIGHT AS ADVERTISEMENTDHEIGHT, "
                + "                     ADV.ADVERTISEMENTDWIDTH AS ADVERTISEMENTDWIDTH, "
                + "                     ADV.TOTALCOSTS AS TOTALCOSTS, "
                + "                     ADV.CREATEDDATETIME AS CREATEDDATETIME "
                + "FROM advertisemet ADV "
                + "LEFT OUTER JOIN advertisemettype ADVT ON ADVT.ADVERTISEMENTTYPEID = ADV.ADVERTISEMENTTYPE "
                + "LEFT OUTER JOIN applicantcontact AC ON AC.APPLICANTCONTACTID = ADV.APPLICANTADDRESS "
                + "LEFT OUTER JOIN clientcontact CL ON CL.CLIENTCONTACTID = ADV.CLIENTADDRESS "
                + "LEFT OUTER JOIN responsiblepersoncontact RPC ON RPC.RESPERSONCONTACTID = ADV.RESPONSIBLEPERSONADDRESS "
                + "LEFT OUTER JOIN propertytype PT ON PT.PROPERTYTYPEID = ADV.PROPERTYTYPE "
                + "LEFT JOIN status ADVS ON ADVS.STATUSID = ADV.ADVERTISEMETSTATUS "
                + "LEFT JOIN status ILS ON ILS.STATUSID = ADV.ILLUMINATIONSTATUS "
                + "LEFT JOIN status LIS ON LIS.STATUSID = ADV.LEAGALILLEGALSTATUS "
                + "WHERE ADV.ADVERTISEMENTID = ?";

        Advertisement advdata = (Advertisement) this.getJdbcTemplate().queryForObject(
                selectquery,
                new Object[]{advertisementId},
                new RowMapper() {
                    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Advertisement advdata = new Advertisement();
                        advdata.setAdverisementid(rs.getString("ADVERTISEMENTID"));
                        advdata.setNameofapplicant(rs.getString("APPLICANTNAME"));
                        advdata.setApplicantaddressno(rs.getString("APPLICANTADDRESS"));
                        advdata.setApplicantcitylimit(rs.getString("APPLICANTCITYLIMIT"));
                        advdata.setApplicantlocation(rs.getString("APPLICANTLOCATION"));
                        advdata.setApplicanttelno(rs.getString("APLLICANTTELNO"));
                        advdata.setNameofclient(rs.getString("CLIENTNAME"));
                        advdata.setClientaddressno(rs.getString("CLIENTADDRESSNO"));
                        advdata.setClientcitylimit(rs.getString("CLIENTCITYLIMIT"));
                        advdata.setClientlocation(rs.getString("CLIENTLOCATION"));
                        advdata.setClienttelno(rs.getString("CLIENTTELNO"));
                        advdata.setNameofresponsibleperson(rs.getString("RESPONSIBLEPERSONNAME"));
                        advdata.setResponsiblepersoncitylimit(rs.getString("RESPONSIBLEPERSONCITYLIMIT"));
                        advdata.setResponsiblepersonlocation(rs.getString("RESPONSIBLEPERSONLOCATION"));
                        advdata.setResponsiblepersonno(rs.getString("RESPONSIBLEPERSONADRESS"));
                        advdata.setResponsiblepersontelno(rs.getString("RESPONSIBLEPERSONTELNO"));
                        advdata.setAdvertisementtype(rs.getString("ADVERTISEMENTTYPE"));
                        advdata.setAdvertisementstatus(rs.getString("ADVERTISEMENTSTATUS"));
                        advdata.setPropertytype(rs.getString("PROPEERTYTYPE"));
                        advdata.setLightningtype(rs.getString("ILUMINATIONSTATUS"));
                        advdata.setLeagalstatus(rs.getString("LEAGALILLIGALSTATUS"));
                        advdata.setHeight(rs.getString("ADVERTISEMENTHEIGHT"));
                        advdata.setAdvheight(rs.getString("ADVERTISEMENTDHEIGHT"));
                        advdata.setAdvwidth(rs.getString("ADVERTISEMENTDWIDTH"));
                        advdata.setCreateddate(Common.getStringFormatDate(CommonVarList.DATE_FORMAT_yyyy_MM_dd, rs.getDate("CREATEDDATETIME")));
                        try {
                            advdata.setTotalcosts("Rs. " + Common.getGroupedDoubleNumber(rs.getDouble("TOTALCOSTS")));
                        } catch (ParseException ex) {
                            Logger.getLogger(AdvertisementDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        return advdata;
                    }
                });
        return advdata;
    }
}
