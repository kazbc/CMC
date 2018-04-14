<%-- 
    Document   : cssinclude
    Created on : Sep 9, 2017, 10:18:06 PM
    Author     : KAsun Udayanaga
--%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<meta charset="utf-8">

<title> CMS </title>
<meta name="description" content="">
<meta name="author" content="">

<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

<!-- Basic Styles -->
<spring:url value="/resources/core/css/bootstrap.min.css" var="bootstrapCss" />
<link href="${bootstrapCss}" rel="stylesheet" />

<spring:url value="/resources/core/css/demo.css" var="demoCss" />
<link href="${demoCss}" rel="stylesheet" />

<spring:url value="/resources/core/css/create_applicant.css" var="createApplicant" />
<link href="${createApplicant}" rel="stylesheet" />

<spring:url value="/resources/core/css/material-dashboard.css" var="materialdashbord" />
<link href="${materialdashbord}" rel="stylesheet" />

<spring:url value="/resources/core/css/font-awesome.min.css" var="fontAwsome" />
<link href="${fontAwsome}" rel="stylesheet" />

<spring:url value="/resources/core/css/materialIcons.css" var="materialIcons" />
<link href="${materialIcons}" rel="stylesheet" />

<spring:url value="/resources/core/css/custom-style.css" var="customStyle" />
<link href="${customStyle}" rel="stylesheet" />

<spring:url value="/resources/core/css/material-datepicker.css" var="materialDatepicker" />
<link href="${materialDatepicker}" rel="stylesheet" />

