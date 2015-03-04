var route = angular.module('route', ['ngRoute']);

route.config(['$routeProvider', function ($routeProvider) {

    $routeProvider
    .when('/create/entry', {
        templateUrl: 'entry/newentry.html',
        controller: 'newEntryController'
    })
    .otherwise({
        redirectTo: '/'
    });

}]);