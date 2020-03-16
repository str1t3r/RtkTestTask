<%@page import="java.util.Map"%>
<%@page import="org.json.JSONObject"%>
<%@page import="java.util.HashMap"%>
<%@page import="org.json.JSONArray"%>
<%@page import="java.io.IOException"%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="ru.rakovsky.rtk.rtktesttask.DatabaseManager"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container mt-5">
            <div class="row">
                <div class="col-12 text-center">
                    <h1>Hello!</h1>
        <table class="table table-bordered mb-3" id="datatable">
            <thead>
                <tr>
                    <th>Номер цвета</th>
                    <th>Название цвета</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <%
                    JSONArray arr = DatabaseManager.getDataFromDatabase();
                    for (int i = 0; i < arr.length(); ++i) {
                       JSONObject o = arr.getJSONObject(i);
                       Map<String, Object> m = o.toMap();
                       %>
                       <tr>
                           <td><% out.print(m.get("colorNumber")); %></td>
                           <td><% out.print(m.get("name")); %></td>
                           <td>
                               <select class="form-control" data-action="change_color">
                                   <option selected disabled>Выберите действие...</option>
                                   <option value="bg" data-color="<% out.print(m.get("name")); %>">Изменить цвет фона</option>
                                   <option value="txt" data-color="<% out.print(m.get("name")); %>">Изменить цвет букв</option>
                               </select>
                           </td>
                       </tr>   
                        <%
                    }
                %>
            </tbody>
        </table>
            <div class="text-center">
                <button class="btn btn-primary" data-offset="<% out.print(arr.length()) ;%>" id="load_more">Загрузить еще</button>
            </div>
                </div>
            </div>
        </div>
        
    </body>
    <script
  src="https://code.jquery.com/jquery-3.4.1.min.js"
  integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
  crossorigin="anonymous"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/main.js"></script>
    
</html>
