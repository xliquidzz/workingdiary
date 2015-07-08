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
    angular
        .module('userModule')
        .controller('userAdminController', userAdminController);

    userAdminController.$inject = ['$scope', 'loginService', 'apprentices'];

    function userAdminController($scope, loginService, apprentices) {
        // TODO fetch Roles from Server at start of app
        $scope.roles = [{ role: "Lernender", id: 1 }, { role: "Praxisbildner", id: 2 }, { role: "Berufsbildner", id: 2 }];
        $scope.selectedRole = $scope.roles[0];

        $scope.selectedApprentice = {};
        $scope.apprentices = apprentices;
        $scope.setSelectedApprenticeId = setSelectedApprenticeId;
        $scope.remove = remove;
        $scope.createUser = createUser;

        function setSelectedApprenticeId(apprenticeId) {
            $scope.selectedApprentice.id = apprenticeId;
        }

        function remove() {
            loginService.removeById($scope.selectedApprentice.id);
        }

        function createUser() {
            $scope.newUser.roleId = $scope.selectedRole.id;
            $scope.newUser.id = 0;
            var result = loginService.createUser($scope.newUser);
            result.$promise.then(onSuccess, onError);
        }

        function onSuccess(success) {
            $scope.userAdminAlert = '<div class="alert alert-success">The User has been successfully created.</div>';
        }

        function onError(error) {
            $scope.userAdminAlert = '<div class="alert alert-danger">Something went wrong. There has an error occurred.</div>';
        }
    }
}());