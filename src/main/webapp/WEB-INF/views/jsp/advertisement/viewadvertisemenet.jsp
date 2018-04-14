<%-- 
    Document   : createadvertisement
    Created on : Sep 9, 2017, 1:16:46 PM
    Author     : KAsun Udayanaga
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
    <head>
        <!-- css include -->
        <jsp:include page="../template/cssinclude.jsp"/>

        <style>
            .form-group label.control-label {
                font-size: 14px;
                line-height: 1.07143;
                color: #777;
                font-weight: 400;
                margin: 16px 0 0 0;
            }
            .form-group {
                padding-bottom: 0px;
                margin: 0px 0 0 0;
            }
            hr{
                margin-top: 0px;
                margin-bottom: 10px;
            }
            header{
                border-bottom: 1px dotted rgba(0,0,0,.2);
                margin-bottom: 10px;
                font-weight: 900;
            }
            header h4{
                font-weight: 400;
                font-size: 18px;
            }
            .note{
                margin-top: 6px;
                padding: 0 1px;
                font-size: 11px;
                line-height: 15px;
                color: #999;
            }
            .headerdiv {
                margin-bottom: -6px; 
                margin-top: 10px;
            }
            .dimensionhint {
                margin-top: 78px;
            }
        </style>

    </head>

    <body>
        <div class="wrapper">
            <jsp:include page="../template/sidebar.jsp"/>

            <div class="main-panel">
                <div class="content">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="widget-body no-padding">
                                <div class="row">
                                    <div class="col-xs-12">
                                        <div class="card">
                                            <div class="card-header" data-background-color="purple">
                                                <h4 class="title">Advertisement Application</h4>
                                                <p class="category">View</p>
                                            </div>
                                            <div class="card-content">
                                                <form:form commandName="advertisementView">
                                                    <div class="row">
                                                        <div class="col-md-1"></div>
                                                        <div class="col-md-10 headerdiv">
                                                            <header><h4>Applicant Information </h4></header>
                                                        </div>
                                                        <div class="col-xs-1"></div>
                                                    </div>
                                                    <!--<hr>-->
                                                    <div class="row">
                                                        <div class="col-md-2"></div>
                                                        <div class="col-md-8">
                                                            <div class="form-group">
                                                                <label class="control-label">Name of the Applicant</label>
                                                                <form:input path = "nameofapplicant" id = "nameofapplicant" type="text" class="form-control" />
                                                            </div>
                                                        </div>
                                                        <div class="col-md-2"></div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-md-2"></div>
                                                        <div class="col-md-2">
                                                            <div class="form-group">
                                                                <label class="control-label">No</label>
                                                                <form:input path = "applicantaddressno" id = "applicantaddressno" type="text" class="form-control" />
                                                            </div>
                                                        </div>
                                                        <div class="col-md-4">
                                                            <div class="form-group">
                                                                <label class="control-label">Location</label>
                                                                <form:input path = "applicantlocation" id = "applicantlocation" type="text" class="form-control" />
                                                            </div>
                                                        </div>
                                                        <div class="col-md-2">
                                                            <div class="form-group">
                                                                <label class="control-label">City Limit</label>
                                                                <form:select path="applicantcitylimit" id = "applicantcitylimit" class="form-control" items="${cityLimitList}"/>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-2"></div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-md-2"></div>
                                                        <div class="col-md-2">
                                                            <div class="form-group">
                                                                <label class="control-label">Tel No</label>
                                                                <form:input path = "applicanttelno" id = "applicanttelno" type="text" class="form-control" />
                                                            </div>
                                                            <div class="note">
                                                                <strong>Hint</strong> e.g. 0777101010
                                                            </div>
                                                        </div>
                                                        <div class="col-md-8"></div>
                                                    </div>       
                                                    <div class="row">
                                                        <div class="col-md-1"></div>
                                                        <div class="col-md-10 headerdiv">
                                                            <header><h4>Client Information </h4></header>
                                                        </div>
                                                        <div class="col-md-1"></div>
                                                    </div>
                                                    <!--<hr style="margin-top: 0px;">-->
                                                    <div class="row">
                                                        <div class="col-md-2"></div>
                                                        <div class="col-md-8">
                                                            <div class="form-group">
                                                                <label class="control-label">Name of the Client/Owner</label>
                                                                <form:input path = "nameofclient" id = "nameofclient" type="text" class="form-control" />
                                                            </div>
                                                        </div>
                                                        <div class="col-md-2"></div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-md-2"></div>
                                                        <div class="col-md-2">
                                                            <div class="form-group">
                                                                <label class="control-label">No</label>
                                                                <form:input path = "clientaddressno" id = "clientaddressno" type="text" class="form-control" />
                                                            </div>
                                                        </div>
                                                        <div class="col-md-4">
                                                            <div class="form-group">
                                                                <label class="control-label">Location</label>
                                                                <form:input path = "clientlocation" id = "clientlocation" type="text" class="form-control"/>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-2">
                                                            <div class="form-group">
                                                                <label class="control-label">City Limit</label>
                                                                <form:select path="clientcitylimit" id = "clientcitylimit" class="form-control" items="${cityLimitList}"/>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-2"></div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-md-2"></div>
                                                        <div class="col-md-2">
                                                            <div class="form-group">
                                                                <label class="control-label">Tel No</label>
                                                                <form:input path="clienttelno" id = 'clienttelno' class="form-control" />
                                                            </div>
                                                            <div class="note">
                                                                <strong>Hint</strong> e.g. 0777101010
                                                            </div>
                                                        </div>
                                                        <div class="col-md-8"></div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-md-1"></div>
                                                        <div class="col-md-10 headerdiv">
                                                            <header><h4>Responsible Person Information </h4></header>
                                                        </div>
                                                        <div class="col-md-1"></div>
                                                    </div>
                                                    <!--<hr>-->
                                                    <div class="row">
                                                        <div class="col-md-2"></div>
                                                        <div class="col-md-8">
                                                            <div class="form-group">
                                                                <label class="control-label">Name of the Responsible person for this Advertisement</label>
                                                                <form:input path="nameofresponsibleperson" id = "nameofresponsibleperson" type="text" class="form-control" />
                                                            </div>
                                                        </div>
                                                        <div class="col-md-2"></div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-md-2"></div>
                                                        <div class="col-md-2">
                                                            <div class="form-group">
                                                                <label class="control-label">No</label>
                                                                <form:input path="responsiblepersonno" id="responsiblepersonno" type="text" class="form-control" />
                                                            </div>
                                                        </div>
                                                        <div class="col-md-4">
                                                            <div class="form-group">
                                                                <label class="control-label">Location</label>
                                                                <form:input path="responsiblepersonlocation" id="responsiblepersonlocation" type="text" class="form-control" />
                                                            </div>
                                                        </div>
                                                        <div class="col-md-2">
                                                            <div class="form-group">
                                                                <label class="control-label">City Limit</label>
                                                                <form:select path="responsiblepersoncitylimit" id = "responsiblepersoncitylimit" class="form-control" items="${cityLimitList}"/>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-2"></div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-md-2"></div>
                                                        <div class="col-md-2">
                                                            <div class="form-group">
                                                                <label class="control-label">Tel No</label>
                                                                <form:input path="responsiblepersontelno" id = "responsiblepersontelno" class="form-control" />
                                                            </div>
                                                            <div class="note">
                                                                <strong>Hint</strong> e.g. 0777101010
                                                            </div>
                                                        </div>
                                                        <div class="col-md-8"></div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-md-1"></div>
                                                        <div class="col-md-10 headerdiv">
                                                            <header><h4>Advertisement Information</h4></header>
                                                        </div>
                                                        <div class="col-md-1"></div>
                                                    </div>
                                                    <!--<hr>-->
                                                    <div class="row">
                                                        <div class="col-md-2"></div>
                                                        <div class="col-md-3">
                                                            <div class="form-group">
                                                                <label class="control-label">Advertisement Type</label>
                                                                <form:select path="advertisementtype" id = "advertisementtype" class="form-control" items="${advertisementTypeList}" disabled="true"/>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-7"></div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-md-2"></div>
                                                        <div class="col-md-3">
                                                            <div class="form-group">
                                                                <label class="control-label">Advertisement Status</label>
                                                                <form:select path="advertisementstatus" id = "advertisementstatus" class="form-control" items="${advertisementStatusList}"/>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-2"></div>
                                                        <div class="col-md-3">
                                                            <div class="form-group">
                                                                <label class="control-label">Authorization Status</label>
                                                                <form:select path="leagalstatus" id = "leagalstatus" class="form-control" items="${advertisementAuthList}"/>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-2"></div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-md-2"></div>
                                                        <div class="col-md-8">
                                                            <div class="form-group">
                                                                <label>Location</label>
                                                                <div class="form-group">
                                                                    <form:textarea path="location" id="location" class="form-control" rows="2"/>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-2"></div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-md-2"></div>
                                                        <div class="col-md-8">
                                                            <div class="form-group">
                                                                <label class="control-label">Name of Street/Junction</label>
                                                                <form:input path="streetjunction" id="streetjunction" type="text" class="form-control" />
                                                            </div>
                                                        </div>
                                                        <div class="col-md-2"></div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-md-2"></div>
                                                        <div class="col-md-3">
                                                            <div class="form-group">
                                                                <label class="control-label">Property Type</label>
                                                                <form:select path="propertytype" id = "propertytype" class="form-control" items="${propertyTypeList}"/>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-2"></div>
                                                        <div class="col-md-3">
                                                            <div class="form-group">
                                                                <label class="control-label">Lightning Status</label>
                                                                <form:select path="lightningtype" id="lightningtype" class="form-control" items="${advertisementLightningList}"/>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-2"></div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-md-2"></div>
                                                        <div class="col-md-3">
                                                            <div class="form-group pull-left">
                                                                <label class="control-label ">Width</label>
                                                                <form:input id="adv_width" path="advwidth" type="text" class="form-control" />
                                                            </div>
                                                            <div class="form-group pull-right">
                                                                <label class="control-label ">Height</label>
                                                                <form:input id="adv_height" path="advheight" type="text" class="form-control" />
                                                            </div>
                                                            <div class="note dimensionhint">
                                                                <strong>Hint</strong> Please enter the dimension of advertisement (ft)
                                                            </div>
                                                        </div>
                                                        <div class="col-md-2"></div>
                                                        <div class="col-md-3">
                                                            <div class="form-group">
                                                                <label class="control-label">Height above ground level</label>
                                                                <form:input path="height" id="height"  type="text" class="form-control" />
                                                            </div>
                                                        </div>
                                                        <div class="col-md-2"></div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-md-2"></div>
                                                        <div class="col-md-3">
                                                            <div class="form-group">
                                                                <label class="control-label">Advertisement Total Costs</label>
                                                                <form:input path="totalcosts" id="totalcosts"  type="text" class="form-control" />
                                                            </div>
                                                        </div>
                                                        <div class="col-md-7"></div>
                                                    </div>
                                                    <a href="${pageContext.servletContext.contextPath}/edit/advertisement?advertisementid=${advertisementView.adverisementid}" class="btn btn-primary pull-right" role="button" >Edit Application</a>
                                                    <!--<button type="button" id="btncreateapplication" class="btn btn-primary pull-right">Edit Application</button>-->
                                                    <div class="clearfix"></div>
                                                </form:form>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="../template/jsinclide.jsp"/>
        <script type="text/javascript">
            
            $("#advertisementView :input").prop("disabled", true);
            
        </script>
    </body>
</html>

