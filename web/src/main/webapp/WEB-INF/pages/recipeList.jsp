<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Recipes</title>
</head>
<body>
	<h1>Recipes</h1>
    <ul>
    <c:forEach var="recipe" items="${recipes}">
    <li>${recipe.name} - ${recipe.type}</li>
    </c:forEach>
    </ul>
</body>
</html>