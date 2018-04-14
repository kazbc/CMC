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
            .invalid{
                color:red;
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
                                                <p class="category">Edit</p>
                                            </div>
                                            <div class="card-content">
                                                <form:form commandName="advertisementEdit">
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
                                                                <label class="control-label">Name of the Applicant <samp style="color: red">*</samp></label>
                                                                <form:input path = "nameofapplicant" id = "nameofapplicant" type="text" class="form-control" />
                                                            </div>
                                                        </div>
                                                        <div class="col-md-2"></div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-md-2"></div>
                                                        <div class="col-md-2">
                                                            <div class="form-group">
                                                                <label class="control-label">No <samp style="color: red">*</samp></label>
                                                                <form:input path = "applicantaddressno" id = "applicantaddressno" type="text" class="form-control" />
                                                            </div>
                                                        </div>
                                                        <div class="col-md-4">
                                                            <div class="form-group">
                                                                <label class="control-label">Location <samp style="color: red">*</samp></label>
                                                                <form:input path = "applicantlocation" id = "applicantlocation" type="text" class="form-control" />
                                                            </div>
                                                        </div>
                                                        <div class="col-md-2">
                                                            <div class="form-group">
                                                                <label class="control-label">City Limit <samp style="color: red">*</samp></label>
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
                                                                <label class="control-label">Name of the Client/Owner <samp style="color: red">*</samp></label>
                                                                <form:input path = "nameofclient" id = "nameofclient" type="text" class="form-control" />
                                                            </div>
                                                        </div>
                                                        <div class="col-md-2"></div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-md-2"></div>
                                                        <div class="col-md-2">
                                                            <div class="form-group">
                                                                <label class="control-label">No <samp style="color: red">*</samp></label>
                                                                <form:input path = "clientaddressno" id = "clientaddressno" type="text" class="form-control" />
                                                            </div>
                                                        </div>
                                                        <div class="col-md-4">
                                                            <div class="form-group">
                                                                <label class="control-label">Location <samp style="color: red">*</samp></label>
                                                                <form:input path = "clientlocation" id = "clientlocation" type="text" class="form-control"/>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-2">
                                                            <div class="form-group">
                                                                <label class="control-label">City Limit <samp style="color: red">*</samp></label>
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
                                                                <label class="control-label">Name of the Responsible person for this Advertisement <samp style="color: red">*</samp></label>
                                                                <form:input path="nameofresponsibleperson" id = "nameofresponsibleperson" type="text" class="form-control" />
                                                            </div>
                                                        </div>
                                                        <div class="col-md-2"></div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-md-2"></div>
                                                        <div class="col-md-2">
                                                            <div class="form-group">
                                                                <label class="control-label">No <samp style="color: red">*</samp></label>
                                                                <form:input path="responsiblepersonno" id="responsiblepersonno" type="text" class="form-control" />
                                                            </div>
                                                        </div>
                                                        <div class="col-md-4">
                                                            <div class="form-group">
                                                                <label class="control-label">Location <samp style="color: red">*</samp></label>
                                                                <form:input path="responsiblepersonlocation" id="responsiblepersonlocation" type="text" class="form-control" />
                                                            </div>
                                                        </div>
                                                        <div class="col-md-2">
                                                            <div class="form-group">
                                                                <label class="control-label">City Limit <samp style="color: red">*</samp></label>
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
                                                                <label class="control-label">Advertisement Type <samp style="color: red">*</samp></label>
                                                                <form:select path="advertisementtype" id = "advertisementtype" class="form-control" items="${advertisementTypeList}"/>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-7"></div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-md-2"></div>
                                                        <div class="col-md-3">
                                                            <div class="form-group">
                                                                <label class="control-label">Advertisement Status <samp style="color: red">*</samp></label>
                                                                <form:select path="advertisementstatus" id = "advertisementstatus" class="form-control" items="${advertisementStatusList}"/>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-2"></div>
                                                        <div class="col-md-3">
                                                            <div class="form-group">
                                                                <label class="control-label">Authorization Status <samp style="color: red">*</samp></label>
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
                                                                <label class="control-label">Property Type <samp style="color: red">*</samp></label>
                                                                <form:select path="propertytype" id = "propertytype" class="form-control" items="${propertyTypeList}"/>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-2"></div>
                                                        <div class="col-md-3">
                                                            <div class="form-group">
                                                                <label class="control-label">Lightning Status <samp style="color: red">*</samp></label>
                                                                <form:select path="lightningtype" id="lightningtype" class="form-control" items="${advertisementLightningList}"/>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-2"></div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-md-2"></div>
                                                        <div class="col-md-3">
                                                            <div class="form-group pull-left">
                                                                <label class="control-label ">Width (ft) <samp style="color: red">*</samp></label>
                                                                <form:input id="adv_width" path="advwidth" type="text" class="form-control" />
                                                            </div>
                                                            <div class="form-group pull-right">
                                                                <label class="control-label ">Height (ft) <samp style="color: red">*</samp></label>
                                                                <form:input id="adv_height" path="advheight" type="text" class="form-control" />
                                                            </div>
                                                            <!--                                                            <div class="note dimensionhint">
                                                                                                                            <strong>Hint</strong> Please enter the dimension of advertisement (ft)
                                                                                                                        </div>-->
                                                        </div>
                                                        <div class="col-md-2">
                                                            <button class="btn btn-primary btn-just-icon" id="btncalculatecosts" type="button">
                                                                <i class="material-icons">dialpad</i>
                                                            </button>
                                                        </div>
                                                        <div class="col-md-3">
                                                            <div class="form-group">
                                                                <label class="control-label">Height above ground level (ft) <samp style="color: red">*</samp></label>
                                                                <form:input path="height" id="height"  type="text" class="form-control" />
                                                            </div>
                                                        </div>
                                                        <div class="col-md-3"></div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-md-2"></div>
                                                        <div class="col-md-3">
                                                            <div class="form-group">
                                                                <label class="control-label">Advertisement Total Costs <samp style="color: red">*</samp></label>
                                                                <form:input path="totalcosts" id="totalcosts"  type="text" class="form-control"  disabled="true"/>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-7"></div>
                                                    </div>
                                                    <button type="button" id="btncreateapplication" class="btn btn-primary pull-right">Save Application</button>
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

            console.log("Advertisement list: " + $('#advertisementtype').val());

            $("#btncreateapplication").click(function () {

                $("#adv_width").rules("add", "required");
                $("#adv_height").rules("add", "required");
                $("#advertisementtype").rules("add", "required");
                $("#lightningtype").rules("add", "required");
                $("#leagalstatus").rules("add", "required");
                $("#advertisementstatus").rules("add", "required");
                $("#height").rules("add", "required");
                $("#nameofapplicant").rules("add", "required");
                $("#applicantaddressno").rules("add", "required");
                $("#applicantlocation").rules("add", "required");
                $("#applicantcitylimit").rules("add", "required");
                $("#nameofclient").rules("add", "required");
                $("#clientaddressno").rules("add", "required");
                $("#nameofclient").rules("add", "required");
                $("#clientaddressno").rules("add", "required");
                $("#clientlocation").rules("add", "required");
                $("#clientcitylimit").rules("add", "required");
                $("#nameofresponsibleperson").rules("add", "required");
                $("#responsiblepersonno").rules("add", "required");
                $("#responsiblepersonlocation").rules("add", "required");
                $("#responsiblepersoncitylimit").rules("add", "required");

                if ($("#advertisementEdit").valid()) {

                    var dataObject = new Object();
                    dataObject.adverisementid = ${advertisementEdit.adverisementid}
                    dataObject.nameofapplicant = $("#nameofapplicant").val();
                    dataObject.applicantaddressno = $("#applicantaddressno").val();
                    dataObject.applicantlocation = $("#applicantlocation").val();
                    dataObject.applicantcitylimit = $("#applicantcitylimit").val();
                    dataObject.applicanttelno = $("#applicanttelno").val();
                    dataObject.nameofclient = $("#nameofclient").val();
                    dataObject.clientaddressno = $("#clientaddressno").val();
                    dataObject.clientlocation = $("#clientlocation").val();
                    dataObject.clientcitylimit = $("#clientcitylimit").val();
                    dataObject.clienttelno = $("#clienttelno").val();
                    dataObject.nameofresponsibleperson = $("#nameofresponsibleperson").val();
                    dataObject.responsiblepersonno = $("#responsiblepersonno").val();
                    dataObject.responsiblepersonlocation = $("#responsiblepersonlocation").val();
                    dataObject.responsiblepersoncitylimit = $("#responsiblepersoncitylimit").val();
                    dataObject.responsiblepersontelno = $("#responsiblepersontelno").val();
                    dataObject.advertisementtype = $("#advertisementtype").val();
                    dataObject.advertisementstatus = $("#advertisementstatus").val();
                    dataObject.leagalstatus = $("#leagalstatus").val();
                    dataObject.location = $("#location").val();
                    dataObject.streetjunction = $("#streetjunction").val();
                    dataObject.propertytype = $("#propertytype").val();
                    dataObject.lightningtype = $("#lightningtype").val();
                    dataObject.advheight = $("#adv_height").val();
                    dataObject.advwidth = $("#adv_width").val();
                    dataObject.totalcosts = $("#totalcosts").val().substr($("#totalcosts").val().indexOf(' ') + 1).replace(/,/g, "");
                    dataObject.height = $("#height").val();

                    var content = JSON.stringify(dataObject);
                    console.log(content);

                    $.ajax({
                        type: "POST",
                        url: "${pageContext.servletContext.contextPath}/updateadvertisement",
                        data: {advertisementdata: content},
                        cache: false,
                        success: function (response, textStatus, jqXHR) {
                            console.log(response);
                            console.log("In sucess" + jqXHR);
                            console.log(response);
                            response = JSON.parse(response);
                            if (response.CODE === "SUCCESS") {
                                demo.showNotificationPanel("ID: " + response.MESSAGE + "<br>" + "Application Updated Successfully !", "done", 1, "top", "right");
                                window.scrollTo(0, 0);
                            } else {
                                demo.showNotificationPanel("Error occured while updating the application !", "info_outline", 4, "top", "right");
                                window.scrollTo(0, 0);
                            }

                        },
                        error: function (jqXHR, textStatus, errorThrown) {
                            demo.showNotificationPanel("Communication Error Please Re-Try!", "info_outline", 4, "top", "right");
                        }
                    });
                }

            });

            $("#btncalculatecosts").click(function () {

                $("#adv_width").rules("add", "required");
                $("#adv_height").rules("add", "required");
                $("#advertisementtype").rules("add", "required");
                $("#lightningtype").rules("add", "required");
                $("#leagalstatus").rules("remove");
                $("#advertisementstatus").rules("remove");
                $("#height").rules("remove");
                $("#nameofapplicant").rules("remove");
                $("#applicantaddressno").rules("remove");
                $("#applicantlocation").rules("remove");
                $("#applicantcitylimit").rules("remove");
                $("#nameofclient").rules("remove");
                $("#clientaddressno").rules("remove");
                $("#nameofclient").rules("remove");
                $("#clientaddressno").rules("remove");
                $("#clientlocation").rules("remove");
                $("#clientcitylimit").rules("remove");
                $("#nameofresponsibleperson").rules("remove");
                $("#responsiblepersonno").rules("remove");
                $("#responsiblepersonlocation").rules("remove");
                $("#responsiblepersoncitylimit").rules("remove");


                if ($("#advertisementEdit").valid()) {

                    var dataObject = new Object();
                    dataObject.advertisementtype = $("#advertisementtype").val();
                    dataObject.lightningtype = $("#lightningtype").val();
                    dataObject.dimension = $("#adv_width").val() * $("#adv_height").val();

                    var content = JSON.stringify(dataObject);
                    console.log(content);

                    $.ajax({
                        type: "POST",
                        url: "${pageContext.servletContext.contextPath}/calculatecosts",
                        data: {advertisementdata: content},
                        cache: false
                        success: function (response, textStatus, jqXHR) {
                            console.log(response);
                            console.log("In sucess" + jqXHR);
                            console.log(response);
                            response = JSON.parse(response);
                            if (response.CODE === "SUCCESS") {
                                $("#totalcosts").val("Rs. " + response.MESSAGE);
                                $("#totalcosts").prop("disabled", "disabled");
                            } else {
                                $("#totalcosts").val("Rs. " + "0.00");
                                $("#totalcosts").prop("disabled", "disabled");
                            }

                        },
                        error: function (jqXHR, textStatus, errorThrown) {
                            console.log("In Error" + jqXHR)
                            $('#msg_dev').html('<div class="alert alert-warning"><strong>Warning!</strong> Communication Error Please Re-Try!</div> <br/>');
                            window.scrollTo(0, 0);
                        }
                    });
                }

            });

            $("#advertisementEdit").validate({
                rules: {
                    advwidth: {
                        required: true,
                        number: true
                    },
                    advheight: {
                        required: true,
                        number: true
                    },
                    advertisementtype: {
                        required: true
                    },
                    lightningtype: {
                        required: true
                    },
                    advertisementstatus: {
                        required: true
                    },
                    leagalstatus: {
                        required: true
                    },
                    propertytype: {
                        required: true
                    },
                    height: {
                        required: true,
                        number: true
                    },
                    nameofapplicant: {
                        required: true
                    },
                    applicantaddressno: {
                        required: true
                    },
                    applicantlocation: {
                        required: true
                    },
                    nameofclient: {
                        required: true
                    },
                    clientaddressno: {
                        required: true
                    },
                    clientlocation: {
                        required: true
                    },
                    clientcitylimit: {
                        required: true
                    },
                    nameofresponsibleperson: {
                        required: true
                    },
                    responsiblepersonno: {
                        required: true
                    },
                    responsiblepersonlocation: {
                        required: true
                    },
                    responsiblepersoncitylimit: {
                        required: true
                    }
                }
            });


            function numberWithCommas(x) {
                var parts = x.toString().split(".");
                parts[0] = parts[0].replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                return parts.join(".");
            }
        </script>
    </body>
</html>

