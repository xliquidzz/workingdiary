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
    angular.module('userModule')
        .service('loginService', [
            '$resource',
            loginService
        ]);

    function loginService($resource) {
        return {
            getRoleId: getRoleId,
            getApprentices: getApprentices,
            removeById: removeById,
            createUser: createUser,
            signUp: signUp,
            changePassword: changePassword
        };

        function changePassword(passwords) {
            var result = $resource('/api/user/change-password');
            return result.save(passwords);
        }

        function createUser(newUser) {
            var result = $resource('/api/user');
            return result.save(newUser);
        }

        function getRoleId() {
            var result = $resource('/api/user/get-role').get();
            return result;
        }

        function getApprentices() {
            var apprenticeRole = 1;
            var result = $resource('/api/user/role/' + apprenticeRole);
            return result.query().$promise.then(function(success) {
                return success;
            });
        }

        function removeById(userId) {
            var result = $resource('/api/user/' + userId);
            return result.remove(userId).$promise.then(function(success) {
                return success;
            });
        }

        function signUp(newUser) {
            var result = $resource('/api/user/sign-up');
            return result.save(newUser);
        }
    }
}());
