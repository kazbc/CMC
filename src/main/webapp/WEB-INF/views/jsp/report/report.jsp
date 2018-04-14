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
                margin-top: 76px;
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
                                                <h4 class="title">Advertisement Reports</h4>
                                                <p class="category">Download</p>
                                            </div>
                                            <div class="card-content">
                                                <form:form commandName="reportForm" action="${pageContext.servletContext.contextPath}/report">
                                                    <form:hidden id="action" path="action"/>
                                                    <div class="row">
                                                        <div class="col-md-2"></div>
                                                        <div class="col-md-3">
                                                            <div class="form-group">
                                                                <label class="control-label">Advertisement Type</label>
                                                                <form:select path="applicationtype" id = "applicationtype" class="form-control" items="${advertisementTypeList}"/>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-7"></div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-md-2"></div>
                                                        <div class="col-md-3">
                                                            <div class="form-group">
                                                                <label class="control-label">Start Date</label>
                                                                <form:input id="startdate" path="startdate" class="datepicker form-control" type="text" data-date-format="yyyy-mm-dd"/>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-2"></div>
                                                        <div class="col-md-3">
                                                            <div class="form-group">
                                                                <label class="control-label">End Date</label>
                                                                <form:input id="enddate" path="enddate" class="datepicker form-control" type="text" data-date-format="yyyy-mm-dd"/>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-2"></div>
                                                    </div>

                                                    <div class="row">
                                                        <!--<div class="col-xs-10"></div>-->
                                                        <div class="col-xs-12 pull-right">
                                                            <footer style="background-color: #ffffff; float: right;">
                                                                <form:hidden id="download_token_value_id" path="download_token_value_id"/>
                                                                <form:button id="download_excel" type="submit" class="btn btn-primary" onclick="excelButton()">Download Excel</form:button>
                                                                <%--<form:button id="search_btn" type="button" class="btn btn-default">Search</form:button>--%>
                                                                </footer>
                                                            </div>
                                                        </div>
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

            function excelButton() {
                $('#action').val('EXCEL');
                return true;
            }

            //$('.datepicker').datepicker();

            //$('.datepicker').datepicker({
            //weekStart: 1
            //});

            var nowTemp = new Date();
            var now = new Date(nowTemp.getFullYear(), nowTemp.getMonth(), nowTemp.getDate(), 0, 0, 0, 0);

            var checkin = $('#startdate').datepicker({
                onRender: function (date) {
                    return date.valueOf() < now.valueOf() ? 'disabled' : '';
                }
            }).on('changeDate', function (ev) {
                if (ev.date.valueOf() > checkout.date.valueOf()) {
                    var newDate = new Date(ev.date)
                    newDate.setDate(newDate.getDate() + 1);
                    checkout.setValue(newDate);
                }
                checkin.picker.hide();
                $('#enddate')[0].focus();
            }).data('datepicker');
            var checkout = $('#enddate').datepicker({
                onRender: function (date) {
                    return date.valueOf() <= checkin.date.valueOf() ? 'disabled' : '';
                }
            }).on('changeDate', function (ev) {
                checkout.picker.hide();
            }).data('datepicker');

        </script>
    </body>
</html>

