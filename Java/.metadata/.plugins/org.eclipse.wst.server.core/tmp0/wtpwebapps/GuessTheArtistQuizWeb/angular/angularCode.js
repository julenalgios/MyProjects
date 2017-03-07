var mainApp = angular.module('myApp', [])

mainApp.controller('myCtrl', 
		
function($scope, $http) {
	
    $http.get('rest/quizWebService/getFirstSong').
        then(function(response) {
            $scope.round = response.data
        });
        
    $scope.getOneMoreSong = function(inputValue){
    	$http.get('rest/quizWebService/getOneMoreSong/'+inputValue)
    	.then(function(response){
    		$scope.round = response.data
    		$scope.inputValue = null;
    	});
    };
    
    $scope.nextRound = function(inputValue){
    	$http.get('rest/quizWebService/getFirstSong').
        then(function(response) {
            $scope.round = response.data
        });
    };
});

