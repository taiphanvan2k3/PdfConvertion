<%@page import="model.BEAN.Upload"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View list convert</title>
<link rel="stylesheet" href="./css/common.css" />
<link rel="stylesheet" href="./css/convertion/convertion.css" />
</head>
<body>
  <%@include file="header.jsp"%>
  <%
  ArrayList<Upload> uploads = (ArrayList<Upload>) request.getSession().getAttribute("uploads");
  %>
  <div class="content-downloader list-convert">
    <div class="table-container scrollbar">
      <div class="header-content">
        <a class="btn btn-back" href="./index.jsp">Back to home</a>
        <h1 class="text-center header-text fs-20px">List file
          converted</h1>
      </div>
      <%
      if (uploads != null && uploads.size() > 0) {
      %>
      <table class="styled-table">
        <thead class="thead-dark">
          <tr>
            <th class="text-center">No</th>
            <th class="text-center">File upload</th>
            <th class="text-center">File converted</th>
            <th class="text-center">Date</th>
          </tr>
        </thead>
        <tbody>
          <%
          int i = 1;
          for (Upload upload : uploads) {
          %>
          <tr class="active-row">
            <td class="text-center"><%=i%></td>
            <td class="text-center"><%=upload.getFileNameUpload()%></td>
            <td class="text-center"><a
                href="./DownloadFileServlet?action=downloadfile&fileName=<%=upload.getFileNameOutputInServer()%>"
                target="_blank"
              ><%=upload.getFileNameOutput()%></a></td>
            <td class="text-center"><%=upload.getDate()%></td>
          </tr>
          <%
          i++;
          }
          %>
        </tbody>
      </table>
      <%
      } else {
      %>
      <h3>You haven't converted file before.</h3>
      <%
      }
      %>
    </div>
  </div>
</body>
</html>