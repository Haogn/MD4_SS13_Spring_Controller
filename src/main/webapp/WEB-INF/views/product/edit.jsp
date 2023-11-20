<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 11/10/2023
  Time: 1:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html lang="en">
<head>
    <title>Edit Category</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-lg-6">
            <h1 class="text-center">Edit Product</h1>
            <f:form action="${pageContext.request.contextPath}/update-product/${product.productId}"
                    method="post" modelAttribute="product"
                    enctype="multipart/form-data">
                <div class="form-group">
                    <label for="productId">productId</label>
                    <input type="text" class="form-control" id="productId" readonly value=${product.productId}>
                </div>
                <div class="form-group">
                    <label for="productName">productName</label>
                    <f:input type="text" class="form-control" id="productName" path="productName"/>
                </div>
                <div class="form-group">
                    <label for="price">price</label>
                    <f:input type="text" class="form-control" id="price" path="price"/>
                </div>
                <div class="form-group">
                    <label>image</label>
                    <img src="<%= request.getContextPath()%>/uploads/images/${product.image}" alt="">
                    <input type="file" class="form-control" id="image" name="img_upload"/>
                </div>
                <f:input path="image" type="hiden"/>
                <div class="form-group">
                    <label for="">Category</label>
                    <f:select path="category.categotyId" class="form-control" id="">
                        <c:forEach items="${categoryList}" var="item">
                            <option value=${item.categotyId}>${item.categoryName}</option>
                        </c:forEach>
<%--                        <f:options items="${categoryList}" itemLabel="categotyName" itemValue="categoryId"></f:options>--%>
                    </f:select>
                </div>
                <button type="submit" class="btn btn-outline-dark">Update</button>
            </f:form>
        </div>
    </div>
</div>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
</body>
</html>
