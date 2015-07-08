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
        .module('entryModule')
        .controller('createEntryController', createEntryController);

    createEntryController.$inject = ['$scope', 'entryService'];

    function createEntryController($scope, entryService) {
        $scope.newEntry = {};
        $scope.newEntry.title = '';
        $scope.newEntry.message = '';

        $scope.submit = createEntry;
        $scope.reset = reset;

        function createEntry() {
            $scope.spinner = '<i class="fa fa-spinner fa-pulse fa-2x"></i>';
            entryService.create($scope.newEntry).then(function(response) {
                $scope.alert = response.alert;
                if(response.reset === true) {
                    reset();
                }
                $scope.spinner = null;
            });
        }

        function reset() {
            $scope.newEntry = {};
        }
    }
}());