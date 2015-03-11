var route = angular.module('route', ['ngRoute']);

route.config(['$routeProvider', function ($routeProvider) {
    $routeProvider
    .when('/apprentice/entry', {
        templateUrl: 'entry/entries.html',
        controller: 'entryController',
        resolve: {
            roleId: ['loginService', function (loginService) {
                return loginService.getRoleId();
            }]
        }
    })
    .when('/create/entry', {
        templateUrl: 'entry/newentry.html',
        controller: 'newEntryController'
    })
    .when('/all/apprentice', {
        templateUrl: 'entry/entries_vocationTrainer.html',
        controller: 'vocationTrainerEntryController'
    })
    .otherwise ({
        redirectTo: '/'
    });
}]);