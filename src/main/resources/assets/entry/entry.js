var entry = angular.module('entryModule', ['ngResource', 'ngSanitize', 'textAngular']);

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

entry.controller('updateEntryController', ['$scope', '$location', 'entryService', function($scope, $location, entryService){
    $scope.newEntry = {};

    var entryToUpdate = entryService.entryToUpdate;

    $scope.newEntry.title = entryToUpdate.title;
    $scope.newEntry.message = entryToUpdate.message;

    $scope.submit = function () {
        var newEntryId = entryService.update(entryToUpdate.id, $scope.newEntry);

        newEntryId.$promise.then(
            function(success) {
                $scope.alert = '<div class="alert alert-success">Your entry has been successfully updated.</div>';
                $location.path('/');
            },
            function (error) {
                $scope.alert = '<div class="alert alert-danger">Your entry could not be updated. There has been an Error.</div>';
            }
        );
    };

    $scope.reset = function() {
        $scope.newEntry = null;
        entryService.entryToUpdate = {};
    };
}]);

entry.controller('entryController', ['$scope', '$location','entryService', function($scope, $location, entryService) {

    $scope.setDetailedEntry = function (entry) {
        $scope.detailedEntry = entry || '';
    };

    $scope.entries = entryService.getEntries();

    $scope.remove = function() {
        entryService.remove($scope.detailedEntry.id);
    };

    $scope.edit = function(entry) {
        entryService.entryToUpdate = entry;
        $location.path('/apprentice/update/entry');
    };

    $scope.printDetailedEntry = function() {
      var printContents = document.getElementById('entryToPrint').innerHTML;
      var popupWin = window.open('', '_blank', 'width=1000,height=600');
      popupWin.document.open();
      popupWin.document.write('<html><head><link rel="stylesheet" type="text/css" href="style/print.css"/><link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css"></head><body onload="window.print()">' + printContents + '</html>');
      popupWin.document.close();
    };
}]);

entry.controller('trainerEntryController', ['$scope', 'entryService', 'users', function($scope, entryService, users) {

    $scope.users = users;

    $scope.setEntries = function (userId) {
        $scope.entries = entryService.getApprenticeEntries(userId);
    };

    $scope.setDetailedEntry = function (entry) {
        $scope.detailedEntry = entry || '';
    };

    $scope.printDetailedEntry = function() {
        var printContents = document.getElementById('entryToPrint').innerHTML;
        var popupWin = window.open('', '_blank', 'width=1000,height=600');
        popupWin.document.open();
        popupWin.document.write('<html><head><link rel="stylesheet" type="text/css" href="style/print.css"/><link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css"></head><body onload="window.print()">' + printContents + '</html>');
        popupWin.document.close();
    };
}]);

entry.controller('vocationTrainerEntryController', ['$scope', '$http','entryService', function($scope, $http, entryService){

    $scope.users = entryService.getApprentices();

    $scope.setEntries = function (userId) {
        $scope.entries = entryService.getApprenticeEntries(userId);
    };

    $scope.setDetailedEntry = function (entry) {
        $scope.detailedEntry = entry || '';
    };

    $scope.printDetailedEntry = function() {
        var printContents = document.getElementById('entryToPrint').innerHTML;
        var popupWin = window.open('', '_blank', 'width=1000,height=600');
        popupWin.document.open();
        popupWin.document.write('<html><head><link rel="stylesheet" type="text/css" href="style/print.css"/><link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css"></head><body onload="window.print()">' + printContents + '</html>');
        popupWin.document.close();
    };
}]);

entry.service('entryService', ['$resource', '$http',function ($resource, $http) {

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
        },
        update: function(entryId, entry) {
            var resource = $resource('/api/entry/' + entryId, {}, {'update': {method: 'PUT', isArray: false }});
            var entryToPut = {title: entry.title, message: entry.message};
            return resource.update({}, entryToPut);
        },
        entryToUpdate: {}
    }
}]);