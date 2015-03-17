var user = angular.module('userModule', ['ngSanitize', 'ngResource', 'ngRoute']);

user.controller('loginController', ['$scope', '$resource', '$window', '$location', '$route', 'loginService',
    function ($scope, $resource, $window, $location, $route, loginService) {

    $scope.isSignedIn = loginService.isSignedIn;

    var showSignIn = function () {
        $('.bs-example-modal-md').modal({
            toggle: 'modal',
            keyboard: false,
            backdrop: 'static',
            show: true
        });
    };

    $scope.signOut = function () {
        $window.localStorage.removeItem('Authorization');
        delete $scope.roleId;
        $location.path('/');
        showSignIn();
    }

     $scope.signIn = function() {
        var resource = $resource("/api/user/generate-token");
        var signIn = resource.save($scope.user);

        signIn.$promise.then(
            function( value ) {
                $window.localStorage.setItem('Authorization', value.accessToken);
                loginService.signedIn = true;
                var roleId = loginService.getRoleId()
                .$promise.then(function (success) {
                        $scope.roleId = success.roleId;
                        $scope.loginAlert = '';
                        $('.bs-example-modal-md').modal('hide');
                        if($location.path() == '/') {
                            $route.reload();
                        }
                    }
                );
            },
            function( error ){
                $window.localStorage.removeItem('Authorization');
                $scope.loginAlert = '<div class="alert alert-danger">User name or Password invalid.</div>';
                loginService.signedIn = false;
                $scope.user.password = '';
            }
        );
    };

   var roleId = loginService.getRoleId();
   roleId.$promise.then(
         function (success) {
            $scope.roleId = success.roleId;
            $('.bs-example-modal-md').modal('hide');
         },
         function (error) {
             setTimeout(function() {
                showSignIn();
             }, 0);
         }
     );
}]);

user.controller('userAdminController', ['$scope', 'loginService', 'apprentices', function($scope, loginService, apprentices) {
    $scope.selectedApprentice = {};
    $scope.apprentices = apprentices;

    $scope.setSelectedApprenticeId = function (apprenticeId) {
        $scope.selectedApprentice.id = apprenticeId;
    };

    $scope.remove = function() {
        loginService.removeById($scope.selectedApprentice.id);
    };

    $scope.roles = [{ role: "Lernender", id: 1 }, { role: "Praxisbildner", id: 2 }, { role: "Berufsbildner", id: 2 }];
    $scope.selectedRole = $scope.roles[0];

    $scope.createUser = function() {
        $scope.newUser.roleId = $scope.selectedRole.id;
        $scope.newUser.id = 0;
        var result = loginService.createUser($scope.newUser);
        result.$promise.then(function(success) {
            $scope.userAdminAlert = '<div class="alert alert-success">The User has been successfully created.</div>';
        },
        function(error) {
            $scope.userAdminAlert = '<div class="alert alert-danger">Something went wrong. There has an error occurred.</div>';
        });
    };
}]);

user.service('loginService', ['$resource', '$q',function ($resource, $q) {
    return {
        isSignedIn: false,
        getRoleId: function () {
            var result = $resource('/api/user/get-role').get();
            return result;
        },
        getApprentices: function() {
            var apprenticeRole = 1;
            var result = $resource('/api/user/role/' + apprenticeRole);
            return result.query().$promise.then(function(success) {
                return success;
            });
        },
        removeById: function(userId) {
            var result = $resource('/api/user/' + userId);
            return result.remove(userId).$promise.then(function(success) {
                return success;
            });
        },
        createUser: function(newUser) {
            var result = $resource('/api/user');
            return result.save(newUser);
        }
    }
}]);

user.config(['$httpProvider', function ($httpProvider) {
    $httpProvider.interceptors.push('AuthInterceptor');
}]);

user.factory('AuthInterceptor', ['$window', '$q', function ($window, $q) {
    return {
        'request': function(config) {
            config.headers = config.headers || {};
            if ($window.localStorage.getItem('Authorization')) {
                config.headers.Authorization = 'bearer ' + $window.localStorage.getItem('Authorization');
            }
            return config || $q.when(config);
        },

        'response': function(response) {
            return response || $q.when(response);
        },

        'responseError': function (rejection) {
            if(rejection.status === 401) {
                $('.bs-example-modal-md').modal({
                    toggle: 'modal',
                    keyboard: false,
                    backdrop: 'static',
                    show: true
                });
                $window.localStorage.removeItem('Authorization');
            }
            return $q.reject(rejection);
        }
    }
}]);

