var route = angular.module('route', ['ngRoute']);

route.config(['$routeProvider', function ($routeProvider) {
    $routeProvider
    .when('/', {
        templateUrl: 'entry/entries.html',
        controller: 'entryController',
        resolve: {
            roleId: ['$location', 'loginService', function ($location, loginService) {
                var request = loginService.getRoleId();
                return request.$promise.then(function (success) {
                    var roleId = success.roleId;
                    if(roleId == 1) {
                        $location.path('/apprentice/entry');
                    } else if (roleId == 2) {
                        $location.path('/trainer/apprentices');
                    } else if (roleId == 3) {
                        $location.path('/vocation-trainer/all/apprentice');
                    }
                    return roleId;
                });
            }]
        }
    })
    .when('/apprentice/entry', {
        templateUrl: 'entry/entries.html',
        controller: 'entryController'
    })
    .when('/apprentice/create/entry', {
        templateUrl: 'entry/newentry.html',
        controller: 'newEntryController',
    })
    .when('/vocation-trainer/all/apprentice', {
        templateUrl: 'entry/entries_trainers.html',
        controller: 'vocationTrainerEntryController'
    })
    .when('/trainer/apprentices', {
        templateUrl: 'entry/entries_trainers.html',
        controller: 'trainerEntryController',
        resolve: {
            users: ['entryService', function(entryService) {
                return entryService.getApprenticeOfTrainer();
            }]
        }
    })
    .when('/apprentice/update/entry', {
        templateUrl: 'entry/updateentry.html',
        controller: 'updateEntryController'
    })
    .otherwise ({
        redirectTo: '/'
    });
}]);