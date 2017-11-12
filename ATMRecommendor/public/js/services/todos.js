angular.module('todoService', [])

	// super simple service
	// each function returns a promise object 
	.factory('Todos', ['$http',function($http) {
		return {
			get : function() {
				return $http.get('/api/todos');
			},
			getByID : function(id) {
				
				return $http.get('/api/todos/'+id);
			},
			create : function(todoData) {

				return $http.post('/api/todos', todoData);
			},
			update : function(todoData) {
				alert('hi');
				return $http.put('/api/todos', todoData);
			},
			delete : function(id) {
				
				return $http.delete('/api/todos/' + id);
			}
		}
	}]);