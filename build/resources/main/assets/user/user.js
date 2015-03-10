var user = angular.module('userModule', ['ngSanitize', 'ngResource']);

user.controller('loginController', ['$scope', '$resource', '$window', '$location', 'loginService',
    function ($scope, $resource, $window, $location, loginService) {

    $scope.isSignedIn = loginService.isSignedIn;

    var showSignIn = function () {
        $('.bs-example-modal-md').modal({
            toggle: 'modal',
            keyboard: false,
            backdrop: 'static',
            show: true
        });
    };

     $scope.signIn = function() {
        var resource = $resource("/api/user/generate-token");
        var signIn = resource.save($scope.user);

        signIn.$promise.then(
            function( value ) {
                $window.localStorage.setItem('Authorization', value.accessToken);
                loginService.signedIn = true;
                if($location.path() == '/') {
                    $location.path('/entry');
                }
            },
            function( error ){
                $window.localStorage.removeItem('Authorization');
                $scope.loginAlert = '<div class="alert alert-danger">User name or Password invalid.</div>';
                loginService.signedIn = false;
                $scope.user.password = '';
            }
        );
    };

    setTimeout(function(){
       showSignIn();
    }, 0);
}]);

user.service('loginService', [function () {
    return {
        isSignedIn: false
    }
}]);

user.config(['$httpProvider', function ($httpProvider) {
    $httpProvider.interceptors.push('AuthInterceptor');
}]);

user.factory('AuthInterceptor', ['$window', '$q', '$location', function ($window, $q, $location) {
    return {
        'request': function(config) {
            config.headers = config.headers || {};
            if ($window.localStorage.getItem('Authorization')) {
                config.headers.Authorization = 'bearer ' + $window.localStorage.getItem('Authorization');
            }
            return config || $q.when(config);
        },

        'response': function(response) {
            $('.bs-example-modal-md').modal('hide');
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

