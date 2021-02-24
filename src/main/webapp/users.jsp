<%@page import="com.mycompany.dao.impl.UserDaoImpl"%>
<%@page import="com.mycompany.entity.User"%>
<%@ page import="java.util.List" %>
<%@ page import="com.mycompany.dao.inter.UserDaoInterface" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <link rel="stylesheet" href="assets/css/users.css">
        <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>

        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <script src="assets/js/index.js"></script>
        <title>Users Table</title>
    </head>
    <body>

        <%
          List<User>  userList = (List<User>) request.getAttribute("userList");

            %>


        <div class="container">
            <form method="get" action="UsersController">
            name:  <input onkeyup="doit()" type="text" id="txt" name="name" on/>
                text here: <span id="spn"> </span>

                <br/>
          surname: <input type="text" name="surname"/>
          <input id="submit"  type="submit" name="search" value="search">
<%--          <input id="btn" type="button"  value="Show Hide" onclick="showHide()">--%>
            </form>
    <div>

        <table class="table">
            <thead>
            <tr>
                <th scope="col">Name</th>
                <th scope="col">Surname</th>
                <th scope="col">Nationality</th>
                <th scope="col"> </th>
            </tr>
            </thead>
            <tbody>
            <% for(User u :userList){%>
            <tr>
                <th scope="row"><%=u.getName()%></th>
                <td><%=u.getSurname()%></td>
                <td><%=(u.getNationality().getNationality()==null) ? "N/A" : u.getNationality().getNationality() %></td>
                <td>


                    <form action="UserDetail" method="get">
                    <input type="hidden" name="uid" value="<%=u.getId()%>">
                    <button class="myButton" type="submit" name="action" value="update"><i class="fa fa-pencil"></i></button>

                </form>


                        <input  type="hidden"  name="uc_id" value="<%=u.getId()%>">
                        <button onclick="doit(<%=u.getId()%>)"   class="myButton" data-toggle="modal" data-target="#exampleModal" type="button"><i class="fa fa-trash"></i></button>


<%--                    <button class="myButton"type="submit" name="action" value="update"><i class="far fa-user-edit"></i></button>--%>

                </td>
            </tr>
           <%}%>
            </tbody>
        </table>
    </div>

        </div>


        <!-- Modal -->
        <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        are you sure to delete?
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>

                        <form action="UserDetail" method="post">
                            <input id="sec_imp_id" type="hidden" name="uc_id" value="" >
                            <button type="submit" name="action" value="delete" class="btn btn-danger">Delete</button>
                        </form>

                    </div>
                </div>
            </div>
        </div>

    </body>

</html>
