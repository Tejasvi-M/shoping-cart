$(function() {
	switch (menu) {

	case 'About':
		$('#about').addClass('active');
		break;
	case 'Contact':
		$('#contact').addClass('active');
		break;
	case 'All Products':
		$('#listproducts').addClass('active');
		break;
	case 'Manage Products':
		$('#manageproducts').addClass('active');
		break;
		
	case 'User Cart':
		$('#userCart').addClass('active');
		break;	

	default:
		$('#home').addClass('active');
		break;
	}

	var token=$('meta[name="_csrf"]').attr('content');
	var header=$('meta[name="_csrf_header"]').attr('content');
	
	if(token.length>0 && header.length>0)
	{
	$(document).ajaxSend(function(e,xhr,options){
		
		xhr.setRequestHeader(header,token);
	});
}
	
	// code for jquery data table

	// jquery to fetch table
	var $table = $('#productListTables');

	if ($table.length) {
		var jsonUrl = '';
		if (window.categoryId == '') {
			jsonUrl = window.contextRoot + '/json/data/all/products';
		} else {
			jsonUrl = window.contextRoot + '/json/data/category/'
					+ window.categoryId + '/products';
		}

		$table
				.DataTable({
					lengthMenu : [ [ 3, 5, 10, -1 ],
							[ '3 records', '5 records', '10 records', 'All' ] ],
					pageLength : 5,
					ajax : {
						url : jsonUrl,
						dataSrc : ''
					},
					columns : [
							{
								data : 'code',
								bSortable : false,
								mRender : function(data, type, row) {
									return '<img src="' + window.contextRoot
											+ '/resources/images/' + data
											+ '.jpg" class="dataTableImg"/>';
								}

							},
							{
								data : 'name'
							},
							{
								data : 'brand'
							},
							{
								data : 'unitPrice',
								mRender : function(data, type, row) {
									return '&#8377;' + data;
								}
							},
							{
								data : 'quantity',
								mRender : function(data, row, type) {
									if (data < 1) {
										return '<span style="color:red">Out of Stock!</span>';
									}
									return data;
								}
							},

							{
								data : 'id',
								bSortable : false,
								mRender : function(data, type, row) {
									var str = '';
									str += '<a href="'
											+ window.contextRoot
											+ '/show/'
											+ data
											+ '/product" class="btn btn-primary"><span class="fa fa-eye"></span></a> &#160;';
									
									if(userRole=='ADMIN'){
										
										str += '<a href="'
											+ window.contextRoot
											+'/manage/'
											+ data
											+ '/product" class="btn btn-warning"><span class="fa fa-cog"></span></a>';

										
									}
									else{
									if (row.quantity < 1) {
										str += '<a href="javascript:void(0)" class="btn btn-success disabled"><strike><span class="fa fa-shopping-cart"></span></strike></a>';

									}

									else {
											
											str += '<a href="'+ window.contextRoot+ '/cart/add/'
												+ data+ '/product" class="btn btn-success"><span class="fa fa-shopping-cart"></span></a>';
										}	
								}

									return str;

								}
							} ]
				});
	}

	var $alert = $('.alert');
	if ($alert.length) {
		setTimeout(function() {
			$alert.fadeout('slow');
		}, 3000)
	}

	// listing all products to admin

	var $productsTable = $('#productsTable');

	if ($productsTable.length) {

		var jsonUrl = window.contextRoot + '/json/data/admin/all/products';
		console.log(jsonUrl);

		$productsTable
				.DataTable({
					lengthMenu : [ [ 10, 30, 50, -1 ],
							[ '10 Records', '30 Records', '50 Records', 'All' ] ],
					pageLength : 30,

					ajax : {
						url : jsonUrl,
						dataSrc : ''
					},
					columns : [
							{
								data : 'id'
							},
							{
								data : 'code',
								bSortable : false,
								mRender : function(data, type, row) {
									return '<img src="' + window.contextRoot
											+ '/resources/images/' + data
											+ '.jpg" class="dataTableImg"/>';
								}
							},
							{
								data : 'name'
							},
							{
								data : 'brand'
							},
							{
								data : 'quantity',
								mRender : function(data, type, row) {
									if (data < 1) {
										return '<span style="color:red">Out of stock!</span>';
									}
									return data;
								}

							},
							{
								data : 'unitPrice',
								mRender : function(data, type, row) {
									return '&#8377; ' + data
								}
							},
							{
								data : 'active',
								bSortable : false,
								mRender : function(data, type, row) {
									var str = '';
									str+='<label class="switch">'
									if (data) {
										str += '<input type="checkbox" value="'
												+ row.id
												+ '" checked="checked">';
									}

									else {
										str+='<input type="checkbox" value="'+row.id+'">';
									}
									
									str+='<div class="slider round"></div></label>'
									return str;
								}
							},
							{
								data : 'id',
								bSortable : false,
								mRender : function(data, type, row) {
									var str = '';
									str += '<a href="'
											+ window.contextRoot
											+ '/manage/'
											+ data
											+ '/product" class="btn btn-primary"><span class="fa fa-cog"></span></a>&#160;';
									return str;
								}

							}

					],

					initComplete : function() {
						var api = this.api();
						api
								.$('.switch input[type="checkbox"]')
								.on(
										'change',
										function() {
											var dText = (this.checked) ? 'You want to activate the Product?'
													: 'You want to deactivate the Product?';
											var checked = this.checked;
											var checkbox = $(this);
											
											bootbox
													.confirm({
														size : 'medium',
														title : 'Product Activation/Deactivation',
														message : dText,
														callback : function(
																confirmed) {
															if (confirmed) {
																$
																		.ajax({
																			type : 'GET',
																			url : window.contextRoot
																					+ '/manage/product/'
																					+ checkbox
																							.prop('value')
																					+ '/activation',
																			timeout : 100000,
																			success : function(
																					data) {
																				bootbox
																						.alert(data);
																			},
																			error : function(
																					e) {
																				bootbox
																						.alert('ERROR:'
																								+ e);
																			}

																		});
															} else {
																checkbox
																		.prop(
																				'checked',
																				!checked);
															}
														}
													});
										});
					}
				});
	}

	// .........................................

	$('button[name="refreshCart"]').click(function(){
		
		//fetch cart line id
		var cartLineId=$(this).attr('value');
		var countElement=$('#count_' + cartLineId);
		var originalCount= countElement.attr('value');
		
		var currentCount=countElement.val();
		
		//refresh on when the count has changed
		
		if(currentCount!== originalCount){
			console.log("current count "+currentCount);
			console.log("original count "+originalCount);
		}
		if(currentCount<1 || currentCount>5){
			//changing the value of current count to original count value if the value exceeds limitation
			countElement.val(originalCount);
			
			bootbox.alert({
				size: 'medium',
				title: 'Error',
				message: 'Cart limit violation'
			});
			
		}
		
		else{
			
			var updateUrl=window.contextRoot+'/cart/'+cartLineId+'/update?count='+currentCount;
			window.location.href=updateUrl;
			
		}
		
	});
	
});
