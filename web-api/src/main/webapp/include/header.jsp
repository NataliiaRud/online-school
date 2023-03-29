<%@ page contentType="text/html; charset=UTF-8"%><%
    Object current = "index";
    if (request.getAttribute("current") != null) {
        current = request.getAttribute("current");
    }
%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="./styles.css" />
    <title>Перелік курсів онлайн школи</title>
  </head>
  <body>
    <div class="container">
      <header class="head">
        <div>
          <a class="logo link" href="index.jsp">Онлайн Школа</a>
        </div>

        <nav class="navigation">
          <ul class="menu site-nav list">
            <li><a class="menu__nav-link link <%= "index".equals(current) ? "current" : "" %>" href="index.jsp">Онлайн Школа</a></li>
            <li><a class="menu__nav-link link <%= "courses".equals(current) ? "current" : "" %>" href="courses.jsp">Курси</a></li>
            <li><a class="menu__nav-link link <%= "students".equals(current) ? "current" : "" %>" href="students.jsp">Студенти</a></li>
            <li><a class="menu__nav-link link <%= "teachers".equals(current) ? "current" : "" %>" href="teachers.jsp">Вчителі</a><li>
            <li><a class="menu__nav-link link <%= "lectures".equals(current) ? "current" : "" %>" href="lectures.jsp">Лекції</a></li>
            <li><a class="menu__nav-link link <%= "additional-materials".equals(current) ? "current" : "" %>" href="additional-materials.jsp">Додаткові матеріали</a></li>
          </ul>
        </nav>
      </header>