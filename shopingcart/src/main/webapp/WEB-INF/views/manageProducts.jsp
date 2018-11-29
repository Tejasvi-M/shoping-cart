<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<div class="container">
	<div class="row">

		<c:if test="${not empty message}">
			<div class="col-xs-12">
				<div class="alert alert-success alert-dismissible">
					<button type="button" class="close" data-dismiss="alert">&times;</button>
				</div>
			</div>
		</c:if>
		<div class="col-md-offset-2 col-md-8">
			<div class="card card-primary">
				<div class="card-header">
					<h4>Product Management</h4>
				</div>

				<div class="card-block">
					<!--  form elements -->
					<sf:form class="form-horizontal" modelAttribute="product"
						action="${contextRoot}/manage/products" method="POST"
						enctype="multipart/form-data">
						<div class="form-group">
							<label class="control-label col-md-4" for="name">Enter
								Product name</label>
							<div class="col-md-8">
								<sf:input type="text" path="name" id="name"
									placeholder="Product Name" class="form-control" />
								<sf:errors path="name" cssClass="help-block" element="em" />
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-4" for="brand">Enter
								Brand name</label>
							<div class="col-md-8">
								<sf:input type="text" path="brand" id="brand"
									placeholder="Brand Name" class="form-control" />
								<sf:errors path="brand" cssClass="help-block" element="em" />
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-4" for="description">Product
								Description </label>
							<div class="col-md-8">
								<sf:textarea path="description" id="description" rows="4"
									class="form-control" />
								<sf:errors path="description" cssClass="help-block" element="em" />

							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-4" for="unitPrice">Enter
								price</label>
							<div class="col-md-8">
								<sf:input type="number" path="unitPrice" id="unitPrice"
									placeholder="Unit price in &#8377;" class="form-control" />
								<sf:errors path="unitPrice" cssClass="help-block" element="em" />
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-4" for="quantity">Enter
								available quatity</label>
							<div class="col-md-8">
								<sf:input type="number" path="quantity" id="quantity"
									placeholder="Quantity Available" class="form-control" />

							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-4" for="file">Upload
								the image</label>
							<div class="col-md-8">
								<sf:input type="file" path="file" id="file" class="form-control" />
								<sf:errors path="file" cssClass="help-block" element="em" />
							</div>
						</div>


						<div class="form-group">
							<label class="control-label col-md-4" for="categoryId">Select
								Category</label>
							<div class="col-md-8">
								<sf:select class="form-control" id="categoryId"
									path="categoryId" items="${categories}" itemLabel="name"
									itemValue="id" />

								<c:if test="${product.id==0}">
									<div class="text-right">
										<br />
										<button type="button" data-toggle="modal"
											data-target="#myCategoryModal" class="btn btn-warning btn-xs">Add
											Category</button>
									</div>
								</c:if>
							</div>
						</div>

						<div class="form-group">

							<div class="col-md-offset-4 col-md-8">
								<input type="submit" name="submit" id="submit" value="Submit"
									class="btn btn-primary" />
								<!-- hidden fields  -->

								<sf:hidden path="id" />
								<sf:hidden path="code" />
								<sf:hidden path="supplierId" />
								<sf:hidden path="active" />
								<sf:hidden path="purchases" />
								<sf:hidden path="views" />

							</div>
						</div>
					</sf:form>
				</div>
			</div>

		</div>
	</div>
	<br/>
	<div class="row">
		<div class="col-xs-12">
			<h3>Available Products</h3>
		</div>

		<div class="col-xs-12">
			<div style="overflow: auto">
					<br>
				<table id="productsTable" class="table table-striped table-bordered">
					<thead>
						<tr>
							<th>Id</th>
							<th>&#160;</th>
							<th>Name</th>
							<th>Brand</th>
							<th>Quantity</th>
							<th>Unit Price</th>
							<th>Active</th>
							<th>Edit</th>
						</tr>
					</thead>



					<tfoot>
						<tr>
							<th>Id</th>
							<th>&#160;</th>
							<th>Name</th>
							<th>Brand</th>
							<th>Quantity</th>
							<th>Unit Price</th>
							<th>Active</th>
							<th>Edit</th>
					</tfoot>
				</table>
			</div>
		</div>

	</div>

	<div class="modal fade" id="myCategoryModal" role="dialog">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
							<span> &times;</span>
								
					</button>
					<h4 class="modal-title">Add new Category</h4>		
			    </div>
			    <div class="modal-body">
			    	<!-- category form -->
			    	<sf:form modelAttribute="category" action="${contextRoot}/manage/category" method="POST" class="form-horizontal">
			    		<div class="form-group">
			    			<label for="category_name" class="control-label col-md-4">Category Name</label>
			    			<div class="col-md-8">
			    				<sf:input type="text" path="name" id="category_name" class="form-control"/>
			    			</div>
			    		</div>
			    		
			    		<div class="form-group">
			    			<label for="category_description" class="control-label col-md-4">Description</label>
			    			<div class="col-md-8">
			    				<sf:textarea rows="5" path="description" id="category_description" class="form-control"/>
			    			</div>
			    		</div>
						
						<div class="form-group">
							<div class="col-md-offset-4 col-md-8">
								<input type="submit" value="Add Category" class="btn btn-primary"/>
							</div>
						</div>
							    		
			    	</sf:form>
			    </div>
			</div>
		</div>
	</div>
</div>
