describe('create.entry.controller.js', function() {
    var $scope, $httpBackend, entryService;


    beforeEach(function() {
        module('workingDiary');

        inject(function(_$httpBackend_, $rootScope, $controller/*, _entryService_*/) {
            $httpBackend = _$httpBackend_;
            $inject = ['entryModule', 'userModule', 'textAngular']
            /*entryService = _entryService_;*/
            $scope = $rootScope.$new();
            $controller('createEntryController', {$scope: $scope});
        });

        $httpBackend
            .when('GET', '/api/user/get-role')
            .respond ({
            status: 401
        });
        $httpBackend.flush();
    });

    describe('$scope.newEntry', function() {
        it('should be set to null if reset has been called', function() {
            // Arrange
            $scope.newEntry = {};
            $scope.newEntry.title = 'anyTitle';
            $scope.newEntry.message = 'anyMessage';

            // Actual
            $scope.reset();

            // Assert
            expect($scope.newEntry).toBeNull();
        });

        it('should not be undefined after reset', function() {
            // Arrange
            $scope.newEntry = {};
            $scope.newEntry.title = 'anyTitle';
            $scope.newEntry.message = 'anyMessage';

            // Actual
            $scope.reset();

            // Assert
            expect($scope.newEntry).not.toBeUndefined();
        });

        it('should have been successfully created', function() {
            $httpBackend
                .when('POST', '/api/entry')
                .respond (
                    { data: 'any', status: 201 }
                );

            $scope.newEntry = {title: 'anyTitle', message: 'anyMessage'}

            $scope.submit();
            $httpBackend.flush();
            expect($scope.newEntry).toBeNull();
            $httpBackend.expectPOST('/api/entry', undefined).respond();
            expect($scope.alert).toContain('<div class="alert alert-success">');
        });

        it('creation should fail cause of no title', function() {
            $httpBackend
                .when('POST', '/api/entry')
                .respond ({
                    status: 422
                });

            $scope.newEntry = {title: undefined, message: 'anyMessage'}

            $scope.submit();
            $httpBackend.flush();
            expect($scope.newEntry).not.toBeNull();
            expect($scope.alert).toContain('<div class="alert alert-danger">');
        });

        afterEach(function() {
            $httpBackend.verifyNoOutstandingExpectation();
            $httpBackend.verifyNoOutstandingRequest();
        });
    });
});