$(function(){
	switch(menu){
	
	case 'About': $('#about').addClass('active');
	                break;
	case 'Contact': $('#contact').addClass('active');
					break;
	case 'All Products':$('#listproducts').addClass('active');
						break;
	default: $('#home').addClass('active');
			 break;
	}
	
	
	
	// code for jquery data table
	
	
	// jquery to fetch table
	var $table = $('#productListTables');
	
	if($table.length){
		var jsonUrl='';
		if(window.categoryId==''){
			jsonUrl=window.contextRoot+'/json/data/all/products';
		}
		else{
			jsonUrl=window.contextRoot+'/json/data/category/'+window.categoryId+'/products';
		}
		
		$table.DataTable({
			lengthMenu:[[3,5,10,-1],['3 records','5 records','10 records','All']],
			pageLength: 5,
			ajax: {
				url:jsonUrl,
				dataSrc:''
			},
			columns:[{
				data:'code',
				bSortable:false,
				mRender:function(data,type,row){
					return '<img src="'+window.contextRoot+'/resources/images/'+data+'.jpg" class="dataTableImg"/>';
				}
				
				},
				{
					data:'name'
				},
				{
					data:'brand'
				},
				{
					data:'unitPrice',
					mRender: function(data,type,row){
						return '&#8377;'+ data;
					}
				},
				{
					data:'quantity',
					mRender: function(data,row,type){
						if(data<1){
							return '<span style="color:red">Out of Stock!</span>';
						}
						return data;
					}
				},
				{
					data:'id',
					bSortable:false,
					mRender:function(data,type,row){
						var str='';
						str+='<a hreff="'+window.contexRoot+'/show/'+data+'/product" class="btn btn-primary"><span class="glyphicon glyphicon-eye-open"></span></a> &#160;';
						
						str+='<a href="'+window.contextRoot+'/cart/add/'+data+'/product" class="btn btn-success"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
						
						return str;
								
						
					}
				}
			]
		});
	}
});