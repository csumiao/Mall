app.controller('baseController',function($scope){
	//分页控件配置 
	$scope.paginationConf = {
			 currentPage: 1,
			 totalItems: 10,
			 itemsPerPage: 10,
			 perPageOptions: [10, 20, 30, 40, 50],
			 onChange: function(){
			        	 $scope.reloadList();//重新加载
			 }
	}; 
	
	//刷新
	$scope.reloadList=function(){
		 //切换页码  
		$scope.search( $scope.paginationConf.currentPage, $scope.paginationConf.itemsPerPage);
	}
	
	$scope.selectIds=[];//选中的ID集合 
	//勾选复选框
	$scope.updateSelection=function($event,id){
		if($event.target.checked){
			$scope.selectIds.push(id);//push向集合添加元素
		}else{
		    var index = $scope.selectIds.indexOf(id);//查找值的位置
		    $scope.selectIds.splice(index,1)//参数（移除的位置， 移除的个数）： 
		}
	
	}
	
	
});