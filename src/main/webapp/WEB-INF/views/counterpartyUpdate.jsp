<%--
  Изменяем контрагент
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html lang="ru">
<head>
    <title>Контрагенты</title>
    <link href="/css/styles.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="form-style-2">
    <div class="form-style-2-heading">
        <h3>Изменить контрагент</h3>
    </div>
    <form:form method="POST" action="/counterparties/update" modelAttribute="command">
        <table width="850">
            <tr>
                <td/>
                <td><form:hidden path="id"/></td>
            </tr>
            <tr>
                <td width="15%">Имя контрагента:</td>
                <td><form:input path="name" class="input-field"
                                            title="Имя контрагента от 1 до 20 символов. Должно быть уникальным"/>
                    <form:errors path="name" cssClass="error" />
                </td>
            </tr>
            <tr>
                <td>ИНН:</td>
                <td><form:input path="inn" class="input-field"
                                title="ИНН контрагента 10 или 12 цифр"/>
                    <form:errors path="inn" cssClass="error"/>
                </td>
            </tr>
            <tr>
                <td>КПП:</td>
                <td><form:input path="kpp" class="input-field"
                                title="КПП контрагента 9 цифр"/>
                    <form:errors path="kpp" cssClass="error"/>
                </td>
            </tr>
            <tr>
                <td>Счет:</td>
                <td><form:input path="accountNumber" class="input-field"
                                title="Номер расчетного счета контрагента 20 цифр"/>
                    <form:errors path="accountNumber" cssClass="error"/>
                </td>
            </tr>
            <tr>
                <td>БИК:</td>
                <td><form:input path="bic" class="input-field"
                                title="БИК контрагента 9 цифр"/>
                    <form:errors path="bic" cssClass="error"/>
                </td>
            </tr>
        </table>
        <input type="submit" value="Изменить контрагент">
        <a href="../counterparties" class="button24" title="Отобразить все контрагенты">Отмена</a>
    </form:form>
</div>
</body>
</html>