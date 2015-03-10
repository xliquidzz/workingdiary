var route = angular.module('route', ['ngRoute']);

route.config(['$routeProvider', function ($routeProvider) {
    $routeProvider
    .when('/', {
        templateUrl: 'entry/entries.html',
        controller: 'entryController'
    })
    .when('/create/entry', {
        templateUrl: 'entry/newentry.html',
        controller: 'newEntryController'
    })
    .otherwise({
        redirectTo: '/'
    });
}]);