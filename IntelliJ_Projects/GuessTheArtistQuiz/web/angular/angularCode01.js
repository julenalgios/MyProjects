var quizMainApp = angular.module("QuizApp", [])

quizMainApp.controller("QuizAppCtrl", function ($http, $scope) {
    $http.get('rest/quizWebService/getFirstSong01').
    then(function(response) {
        $scope.round = response.data;
    });

})