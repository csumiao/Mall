//登录服务



app.service('loginService', function($http){
	//读取登录人姓名
	
	this.loginName = function(){
		return $http.get('../login/name.do');
	}
	
});