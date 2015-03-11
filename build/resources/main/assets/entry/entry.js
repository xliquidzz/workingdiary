var entry = angular.module('entryModule', ['ngResource', 'ngSanitize']);

entry.controller('newEntryController', ['$scope', 'entryService', function($scope, entryService){

    $scope.submit = function () {
        var newEntryId = entryService.create($scope.newEntry);

        newEntryId.$promise.then(
            function(success) {
                $scope.alert = '<div class="alert alert-success">Your entry has been successfully created.</div>';
                $scope.reset();
            },
            function (error) {
                $scope.alert = '<div class="alert alert-danger">Your entry could not be created. There has been an Error.</div>';
            }
        );
    };

    $scope.reset = function() {
        $scope.newEntry = null;
    };
}]);

entry.controller('entryController', ['$scope', 'entryService', function($scope, entryService) {

    $scope.setDetailedEntry = function (entry) {
        $scope.detailedEntry = entry || '';
    };

    $scope.entries = entryService.getEntries();

    $scope.remove = function() {
        entryService.remove($scope.detailedEntry.id);
    };
}]);

entry.controller('trainerEntryController', ['$scope', 'entryService', function($scope, entryService) {

    $scope.users = entryService.getApprenticeOfTrainer();

    $scope.setEntries = function (userId) {
        $scope.entries = entryService.getApprenticeEntries(userId);
    };

    $scope.setDetailedEntry = function (entry) {
        $scope.detailedEntry = entry || '';
    };

}]);

entry.controller('vocationTrainerEntryController', ['$scope', 'entryService', function($scope, entryService){

    $scope.users = entryService.getApprentices();

    $scope.setEntries = function (userId) {
        $scope.entries = entryService.getApprenticeEntries(userId);
    };

    $scope.setDetailedEntry = function (entry) {
        $scope.detailedEntry = entry || '';
    };
}]);

entry.service('entryService', ['$resource', function ($resource) {

    var resource = $resource('/api/entry');

    return {
        create: function (entry) {
            return resource.save(entry);
        },
        getEntries: function() {
            var result = resource.query();
            result.$promise.then(
                function(success) {
                    return success;
                },
                function (error) {
                    return [];
                }
            );
            return result;
        },
        getApprentices: function () {
            var result = $resource('/api/user/role/1').query();
            return result;
        },
        getApprenticeEntries: function (userId) {
            var result = $resource('/api/entry/user/' + userId).query();
            return result;
        },
        remove: function(entryId) {
            var result = $resource('/api/entry/' + entryId).delete();
            return result;
        },
        getApprenticeOfTrainer: function () {
            var result = $resource('/api/user/apprentices').query();
            return result;
        }
    }
}]);