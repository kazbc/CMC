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
                            <div class="col-md-12">
                                <div class="card">
                                    <div class="card-header card-header-icon" data-background-color="purple">
                                        <!--<i class="material-icons">assignment</i>-->
                                        <h4 class="title">Advertisement Information</h4>
                                        <p class="category">Search</p>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-6" style="padding-left: 38px; padding-top:20px; padding-bottom:10px;">
                                            <div class="form-group">
                                                <label class="control-label">Contact No/ Applicant Name/ Client Name/ Responsible Person Name</label>
                                                <input id='input' class="form-control" />
                                            </div>
                                        </div>
                                        <div class="col-md-1" style="padding-top:38px; padding-left:0px;">
                                            <button type="button" id="btnSearch" class="btn btn-primary btn-round"><i class="material-icons">search</i> Search</button>
                                        </div>
                                    </div>

                                    <div class="card-content">
                                        <h4 class="card-title"></h4>
                                        <div class="toolbar">
                                            <!--        Here you can write extra buttons/actions for the toolbar              -->
                                        </div>
                                        <div class="material-datatables">
                                            <table id="datatables" class="table table-striped table-no-bordered table-hover" cellspacing="0" width="100%" style="width:100%">
                                                <thead>
                                                    <tr style="font-size: 16px;">
                                                        <th class="disabled-sorting" style="font-weight:bold;">Applicant Name</th>
                                                        <th class="disabled-sorting" style="font-weight:bold;">Applicant Contact No</th>
                                                        <th class="disabled-sorting" style="font-weight:bold;">Advertisement Type</th>
                                                        <th class="disabled-sorting" style="font-weight:bold;">Advertisement Status</th>
                                                        <th class="disabled-sorting" style="font-weight:bold;">Property Type</th>
                                                        <th class="disabled-sorting" style="font-weight:bold;">Created Date</th>
                                                        <th class="disabled-sorting" style="font-weight:bold;">Actions</th>
                                                    </tr>
                                                </thead>
                                                <!--                                                <tfoot>
                                                                                                    <tr>
                                                                                                        <th>Name</th>
                                                                                                        <th>Position</th>
                                                                                                        <th>Office</th>
                                                                                                        <th>Age</th>
                                                                                                        <th>Start date</th>
                                                                                                        <th class="text-right">Actions</th>
                                                                                                    </tr>
                                                                                                </tfoot>-->
                                                <tbody>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                    <!-- end content-->
                                </div>
                                <!--  end card  -->
                            </div>
                            <!-- end col-md-12 -->
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="../template/jsinclide.jsp"/>
        <script type="text/javascript">

            $(document).ready(function () {
//                $('#datatables').DataTable({
//                    "pagingType": "full_numbers",
//                    "processing": true,
//                    "searching": false
//                });
                drawDataTable();
            });

            $("#btnSearch").click(function () {
                drawDataTable();
            });

            function drawDataTable() {
                console.log("Clicked");
                $('#datatables').DataTable().destroy();
                $('#datatables').DataTable({
                    "pagingType": "full_numbers",
                    "processing": true,
                    "searching": false,
                    "dom": '<"top"i>rt<"bottom"lp><"clear">',
                    "ajax": {
                        type: "POST",
                        "url": "${pageContext.servletContext.contextPath}/searchapplicationdata",
                        'data': {
                            "input": $('#input').val()
                        },
                        dataSrc: ''
                    },
                    "columns": [{
                            "data": "applicantname"
                        }, {
                            "data": "applicanttelno"
                        }, {
                            "data": "advtype"
                        }, {
                            "data": "advstatus"
                        }, {
                            "data": "propertytype"
                        }, {
                            "data": "createddate"
                        }, {
                            "data": "action"
                        }]
                });
                $('#datatables').DataTable().draw();
            }

        </script>
    </body>
</html>

