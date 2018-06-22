app.controller('brandController', function($scope, $controller, brandService){
	
	$controller('baseController', {$scope:$scope});
	
    		//查找品牌列表
    		$scope.findAll=function(){
				brandService.findAll().success(
					function(response){
						$scope.list=response;
					}				
				);    			
    		}
    		//分页
    		$scope.findPage=function(page, rows){	
    			brandService.findPage(page, rows).success(
    					function(response){
    						$scope.list=response.rows;	
    						$scope.paginationConf.totalItems=response.total;//更新总记录数
    					}			
    			);
    		}
    		
    		//添加
    		$scope.save=function() {
    			var object=null;
    			if($scope.entity.id !=null) {
    				object=brandService.update($scope.entity);
    			}else{
    				object=brandService.add($scope.entity)
    			}	
    			object.success(
    				function(response) {
    					if(response.success){
    						$scope.reloadList();
    					}else{
    						alert(response.message);
    					}
    				}		
    			)
    		}
    		
    		//查找
    		$scope.findOne=function(id) {
    			brandService.findOne(id).success(
    				function(response){
    					$scope.entity=response;
    				}		
    			)
    		}
    		
    		$scope.selectIds=[];//用户勾选集合 
    		
    		//批量删除 
    		$scope.delt=function(){
    			brandService.delt($scope.selectIds).success(
    				function(response){
    					if(response.success){
    						$scope.reloadList();
    					}else{
    						alert(response.message);
    					}
    				}		
    			  
    		 );
    		}
    		
    		$scope.searchEntity={};//定义搜索对象 		
    		//条件查询
    		$scope.search=function(page,rows){
    			brandService.search(page, rows, $scope.searchEntity).success(
    				function(response){
    						$scope.paginationConf.totalItems=response.total;//总记录数 
    						$scope.list=response.rows;//给列表变量赋值 
    				}		
    			);				
    		}
    		
    	});