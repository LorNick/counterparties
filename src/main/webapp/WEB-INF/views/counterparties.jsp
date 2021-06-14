<%--
  Список контрагентов
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Контрагенты</title>
    <link href="/css/styles.css" rel="stylesheet" type="text/css">
</head>
<body>
<div >
    <div class="form-style-2-heading">
        <h3>Список контрагентов</h3>
    </div>
    <a href="../counterparties" class="button24" title="Отобразить все контрагенты">Отобразить всё</a>
    <form method="get" action="../counterparties/search" style="margin:0">
        <p style="margin:2px">Поиск по имени: </p>
        <input type="text" name="name" title="Введите имя контрагента"/>
        <input type="submit" value="Поиск" class="button24" title="Найти контрагента по имени"/>
    </form>
    <form method="get" action="../counterparties/search_two">
        <p style="margin:2px">Поиск по номеру счета и БИК: </p>
        <input type="text" name="accountNumber" title="Введите номер счета контрагента"/>
        <input type="text" name="bic" title="Введите БИК контрагента" style="margin:0"/>
        <input type="submit" value="Поиск" class="button24" title="Найти контрагента по номеру счета и БИК"/>
    </form>
    <form method="get" action="../counterparties/new" style="margin:0">
        <input type="submit" value="Новый контрагент" class="button24" title="Добавляет новый контрагент" style="margin-bottom:6px"/>
    </form>
    <div class="form-style-3">
    <table border="2" cellpadding="10">
        <tr>
            <th>id</th>
            <th>Имя контрагента</th>
            <th>ИНН</th>
            <th>КПП</th>
            <th>Номер счета</th>
            <th>Бик</th>
            <th>Действия</th>
        </tr>
        <c:forEach items="${counterparties}" var="counterparty">
            <tr>
                <td>${counterparty.id}</td>
                <td>${counterparty.name}</td>
                <td>${counterparty.inn}</td>
                <td>${counterparty.kpp}</td>
                <td>${counterparty.accountNumber}</td>
                <td>${counterparty.bic}</td>
                <td>
                    <div id="viewport">
                        <form method="get" action="../counterparties/update" style="margin:0; display: inline-block">
                            <input type="hidden" name="id" value="${counterparty.id}"/>
                            <input type="submit" value="Edit" class="link-button" title="Редактировать контрагент"/>
                        </form>
                        <form method="get" action="../counterparties/delete" style="margin:0; display: inline-block">
                            <input type="hidden" name="id" value="${counterparty.id}"/>
                            <input type="submit" value="Delete" class="link-button" title="Удалить контрагент"/>
                        </form>
                    </div>
                </td>
            </tr>
        </c:forEach>
    </table>
    </div>
</div>
</body>
</html>
