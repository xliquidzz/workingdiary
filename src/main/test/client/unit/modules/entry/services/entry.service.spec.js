/*describe('entry.service.js', function() {
    var $scope;

    beforeEach(module('workingDiary'));

    beforeEach(inject(function($rootScope, $controller) {
        $scope = $rootScope.$new();
        $controller('createEntryController', {$scope: $scope});
    }));

    describe('entry', function() {
        it('reset should clear the form fields and set $scope.newEntry to null', function() {
            // Arrange
            $scope.newEntry = {};
            $scope.newEntry.title = 'anyTitle';
            $scope.newEntry.message = 'anyMessage';

            // Actual
            $scope.reset();

            // Assert
            expect($scope.newEntry).toBeNull();
        });

        it('$scope.newEntry should not be undefined after reset', function() {
            // Arrange
            $scope.newEntry = {};
            $scope.newEntry.title = 'anyTitle';
            $scope.newEntry.message = 'anyMessage';

            // Actual
            $scope.reset();

            // Assert
            expect($scope.newEntry).not.toBeUndefined();
        });

        it('should be undefined on default', function() {
            expect($scope.newEntry).toBeUndefined();
        });
    });
});*/