var menu = angular.module('menu', []);

menu.directive('menuBar', function() {
    return {
        templateUrl: 'menu/menu.html',
        controller: 'menuController'
    }
});

menu.controller('menuController', ['$scope', '$location', '$window', function ($scope, $location, $window) {

    $scope.isActive = function (viewLocation) {
        return viewLocation === $location.path();
    };

}]);