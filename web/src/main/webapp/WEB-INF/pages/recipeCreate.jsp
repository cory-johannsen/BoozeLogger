<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
    <title>Create Recipe</title>
</head>
<body>
	<h1>Create Recipe</h1>
    <form:form modelAttribute="recipe">
        <form:hidden path="id" />
        <fieldset>
            <div class="form-row">
                <label>Name:</label>
                <span class="input"><form:input path="name"/></span>
            </div>
        </fieldset>
    </form:form>
    
</body>
</html>