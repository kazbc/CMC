<%-- 
    Document   : sidebar
    Created on : Sep 9, 2017, 11:09:13 PM
    Author     : KAsun Udayanaga
--%>

<div class="sidebar" data-color="purple" data-image="../assets/img/sidebar-1.jpg">
    <!--
    Tip 1: You can change the color of the sidebar using: data-color="purple | blue | green | orange | red"
    Tip 2: you can also add an image using data-image tag
    -->
    <div class="logo">
<!--        <a href="#" class="simple-text">
            Advertise
        </a>-->
    </div>
    <div class="sidebar-wrapper">
        <ul class="nav">
            <li class="active">
                <a href="${pageContext.servletContext.contextPath}/search">
                    <i class="material-icons">search</i>
                    <p>Search Application</p>
                </a>
            </li>
            <li class="active">
                <a href="${pageContext.servletContext.contextPath}/">
                    <i class="material-icons">mode_edit</i>
                    <p>Create Application</p>
                </a>
            </li>
            <li class="active">
                <a href="${pageContext.servletContext.contextPath}/report">
                    <i class="material-icons">assignment</i>
                    <p>Reports</p>
                </a>
            </li>
        </ul>
    </div>
</div>
