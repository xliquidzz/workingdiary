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

(function() {
    angular
        .module('userModule')
        .controller('loginController', loginController);

    loginController.$inject = [
        '$scope',
        '$resource',
        '$window',
        '$location',
        '$route',
        '$rootScope',
        'loginService',
        'entryService',
        'entryPrintService'
    ];

    function loginController($scope, $resource, $window, $location, $route, $rootScope, loginService, entryService, entryPrintService) {
        $scope.user = {};
        $scope.users = [];
        $scope.signIn = signIn;
        $scope.signOut = signOut;
        $scope.tabs = {};
        $scope.tabs.disabled = true;
        $scope.edit = edit;
        $scope.remove = remove;
        $scope.changeLocation = changeLocation;
        $scope.isLocated = isLocated;

        $scope.$on('authFailed', onAuthFailed);
        $scope.$on('detailedEntryChanged', detailedEntryChanged);

        function detailedEntryChanged(event, entry) {
            if(entry === null || entry === undefined) {
                $scope.tabs.disabled = true;
            } else {
                $scope.detailedEntry = entry;
                $scope.tabs.disabled = false;
            }
        }

        function isLocated(location) {
            if($location.path().indexOf(location) === -1) {
                return false;
            } else {
                return true;
            }
        }

        function printEntry() {
            entryPrintService.printEntry();
        }

        function changeLocation(newLocation) {
            $scope.tabs.disabled = true;
            $location.path(newLocation);
        }

        entryService.getApprenticeOfTrainer().$promise.then(onSuccess);
        entryService.getApprentices().$promise.then(onSuccess);

        $scope.setEntries = setEntries;

        function setEntries() {
            $rootScope.$broadcast('apprenticeChanged', $scope.selectedApprentice.id);
        }


        function edit(entry) {
            entryService.entryToUpdate = entry;
        }

        function remove() {
            entryService.remove($scope.detailedEntry.id);
        }

        (function() {
            var roleId = loginService.getRoleId();
            roleId.$promise.then(
                function (success) {
                    $scope.roleId = success.roleId;
                }
            );
        }());

        function onAuthFailed(event, data) {
            $scope.roleId = null;
        }

        function onSuccess(success) {
            $scope.users = success;
        }

        function signOut() {
            $window.localStorage.removeItem('Authorization');
            $scope.roleId = null;
        }

        function signIn() {
            var resource = $resource("/api/user/generate-token");
            var signIn = resource.save($scope.user);

            signIn.$promise.then(onSignInSuccess, onSignInError);
        }

        function onSignInSuccess( value ) {
            $window.localStorage.setItem('Authorization', value.accessToken);
            loginService.getRoleId().$promise.then(function (success) {
                $scope.roleId = success.roleId;
                $scope.loginAlert = '';
                $('.modal-login').modal('hide');
                if($location.path() == '/') {
                    $route.reload();
                }
            });
        }

        function onSignInError( error ) {
            $window.localStorage.removeItem('Authorization');
            $scope.loginAlert = '<div class="alert alert-danger">User name or Password invalid.</div>';
            $scope.user.password = '';
        }

        /* datepicker */
        $scope.today = function() {
            $scope.dt = new Date();
        };
        $scope.today();

        $scope.clear = function () {
            $scope.dt = null;
        };

        // Disable weekend selection
        $scope.disabled = function(date, mode) {
            return ( mode === 'day' && ( date.getDay() === 0 || date.getDay() === 6 ) );
        };

        $scope.toggleMin = function() {
            $scope.minDate = $scope.minDate ? null : new Date();
        };
        $scope.toggleMin();

        $scope.open = function($event) {
            $event.preventDefault();
            $event.stopPropagation();

            $scope.opened = true;
        };

        $scope.dateOptions = {
            formatYear: 'yy',
            startingDay: 1
        };

        $scope.formats = ['dd-MMMM-yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate'];
        $scope.format = $scope.formats[0];

        var tomorrow = new Date();
        tomorrow.setDate(tomorrow.getDate() + 1);
        var afterTomorrow = new Date();
        afterTomorrow.setDate(tomorrow.getDate() + 2);
        $scope.events = [
            { date: tomorrow, status: 'full' },
            { date: afterTomorrow, status: 'partially'}
        ];

        $scope.getDayClass = function(date, mode) {
            if (mode === 'day') {
                var dayToCheck = new Date(date).setHours(0,0,0,0);

                for (var i=0;i<$scope.events.length;i++){
                    var currentDay = new Date($scope.events[i].date).setHours(0,0,0,0);

                    if (dayToCheck === currentDay) {
                        return $scope.events[i].status;
                    }
                }
            }

            return '';
        };

        $scope.$watch('dt', function(newValue, oldValue) {
            $rootScope.$broadcast('dateChanged', newValue);
        });
    }
}());