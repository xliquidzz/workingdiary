var entry = angular.module('entryModule', ['ngResource']);

entry.controller('newEntryController', ['$scope', '$resource', function($scope, $resource){

    $scope.submit = function () {
        var newEntryId = $resource('/api/entry').save($scope.newEntry);
    };

}]);