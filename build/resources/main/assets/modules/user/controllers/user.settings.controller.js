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
    angular.module('userModule')
        .controller('userSettingsController', userSettingsController);

    userSettingsController.$inject = ['$scope', 'loginService'];

    function userSettingsController($scope, loginService) {
        $scope.passwords = {};
        $scope.passwords.oldPassword = '';
        $scope.passwords.newPassword = '';
        $scope.passwords.repeatedPassword = '';

        $scope.changePassword = changePassword;

        function changePassword() {
            var errorPanel = '<div class="alert alert-danger">Your Password could not be changed.</div>';

            if($scope.passwords.newPassword === $scope.passwords.repeatedPassword && $scope.passwords.newPassword.length > 0) {
                var promise = loginService.changePassword($scope.passwords);

                promise.$promise.then(function(success) {
                    $scope.alertPasswordChange = '<div class="alert alert-success">Your Password has been successfully changed.</div>';
                    reset();
                }, function (error) {
                    $scope.alertPasswordChange = errorPanel;
                    reset();
                });
            } else {
                $scope.alertPasswordChange = errorPanel;
            }
        }

        function reset() {
            $scope.passwords = {};
        }
    }
}());