<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
								<c:if test="${pdfViewEq != null }">
									<embed type="application/pdf"
										src="data:application/pdf;base64,${pdfViewEq}"
										style="height: 800px; width: 100%">
									</embed>
								</c:if>
</body>
</html>