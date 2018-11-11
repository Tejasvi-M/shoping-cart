<div class="container page-display-size">

<div class="row">
<!-- div to display sidebar -->
<div class="col-md-3">

<%@include file="./shared/sidebar.jsp" %>

</div>
<!-- div to display the products -->

<div class="col-md-9">

<!-- div element to display breadcrumb -->
<div class="row">
<div class="col-lg-12">

<c:if test="${clickAllProducts==true}">
<script>
window.categoryId='';
</script>
<ol class="breadcrumb">
<li><a href="${contextRoot}/home">Home </a></li>
<li class="active">  All Products</li>
</ol>
</c:if>

<c:if test="${clickCategoryProducts==true}">
<script>
window.categoryId='${category.id}';
</script>
<ol class="breadcrumb">
<li><a href="${contextRoot}/home">Home</a></li>
<li class="active">Category</li>
<li class="active">${category.name}</li>
</ol>
</c:if>



</div>
</div>
<div calss="row">
<div class="col-xs-12">
<table id="productListTables" class="table table-striped table-borderd">
<thead>
<tr>
<th></th>
<th>Name</th>
<th>Brand</th>
<th>Price</th>
<th>Quantity Available</th>
<th></th>
</tr>
</thead>
<tfoot>
<tr>
<th></th>
<th>Name</th>
<th>Brand</th>
<th>Price</th>
<th>Quantity Available</th>
<th></th>
</tr>
</tfoot>
</table>
</div>
</div>
</div>

</div>
</div>