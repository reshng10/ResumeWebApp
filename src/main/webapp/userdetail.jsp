

<%@page import="com.mycompany.dao.impl.UserDaoImpl"%>
<%@page import="com.mycompany.entity.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%

        User u =(User) request.getAttribute("user");

          if(u==null) {
              %>
      no such user

            <% } else {%>


            <form method="POST" action="UserDetail">


            <input type="hidden" name="uc_id" value="<%=u.getId()%>"/>
            name:  <input type="text" name="name" value="<%=u.getName()%>"/>
          surname: <input type="text" name="surname" value="<%=u.getSurname()%>"/>
          <input type="submit" name="action" value="submit">
            </form>
  <%}%>
    </body>
</html>

<%--          if(request.getParameter("save")!=null && request.getParameter("save").equals("submit")){--%>
<%--             int id =Integer.valueOf(request.getParameter("id"));--%>
<%--        System.out.println("----------------------------------------------------"+id);--%>

<%--       String name=  (request.getParameter("name"));--%>
<%--       String surname=  request.getParameter("surname");--%>
<%--        User user= new UserDaoImpl().getById(id);--%>

<%--       user.setName(name);--%>
<%--       user.setSurname(surname);--%>
<%--        new UserDaoImpl().updateUser(user);--%>
<%-- }--%>