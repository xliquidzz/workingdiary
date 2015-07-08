/**
 * This file is part of Working Diary.
 *
 * Working Diary is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Working Diary is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Working Diary.  If not, see <http://www.gnu.org/licenses/>.
 */

(function(){
    angular.module('userModule').factory('authInterceptor', ['$window', '$q', '$location', '$rootScope', function ($window, $q, $location, $rootScope) {
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
                    $('.modal-login').modal({
                        toggle: 'modal',
                        keyboard: false,
                        backdrop: 'static',
                        show: true
                    });
                    $window.localStorage.removeItem('Authorization');
                    $rootScope.$broadcast('authFailed');
                } else if (rejection.status === 403) {
                    $location.path('/');
                }
                return $q.reject(rejection);
            }
        };
    }]);
}());