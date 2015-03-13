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
                        $('.bs-example-modal-md').modal('hide');
                        if($location.path('/')) {
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

user.service('loginService', ['$resource', '$q',function ($resource, $q) {
    return {
        isSignedIn: false,
        getRoleId: function () {
            var result = $resource('/api/user/get-role').get();
            return result;
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

