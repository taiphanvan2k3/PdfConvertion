<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>PDF Convertion</title>
        <link rel="stylesheet" href="./css/common.css" />
        <link rel="stylesheet" href="./css/convertion/convertion.css" />
    </head>
    <body>
        <%@include file="header.jsp"%>
        <div class="content">
            <div class="content-header">
                <h1 class="content-title">PDF to WORD Convertion</h1>
                <h2 class="content-subtitle">Converting pdf documents to word is very convenient</h2>
            </div>
            <div class="content-uploader">
                <a href="#!" class="btn-upload" id="uploadLink">Choose PDF file</a>
            </div>
            <div class="content-uploader">
                <a href="./ListConvertController" class="btn-upload">View list converrt</a>
            </div>
        </div>
        <script src="./js/index.js"></script>
    </body>
</html>
