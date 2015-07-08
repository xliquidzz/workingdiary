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
    angular.module('entryModule').controller('listEntriesTrainerController', listEntriesTrainerController);

    listEntriesTrainerController.$inject = ['$scope', 'entryService', 'entryPrintService', 'users'];

    function listEntriesTrainerController($scope, entryService, entryPrintService, users) {
        $scope.users = users;
        $scope.setEntries = setEntries;
        $scope.setDetailedEntry = setDetailedEntry;
        $scope.printDetailedEntry = printDetailedEntry;

        function setEntries(userId) {
            $scope.entries = entryService.getApprenticeEntries(userId);
        }

        function setDetailedEntry(entry) {
            $scope.detailedEntry = entry || '';
        }

        function printDetailedEntry() {
            entryPrintService.printEntry();
        }

        function getFilteredEntries() {
            return entryService.getEntries().$promise.then(function(entries) {
                $scope.entries = $filter('dateFilter')(entries, $scope.dt);
            });
        }

        $scope.$on('dateChanged', function(event, newValue) {
            $scope.dt = newValue;
            setDetailedEntry(null);
            getFilteredEntries();
        });

        $scope.$on('apprenticeChanged', function(event, newUserId) {
            setEntries(newUserId);
        });

    }
}());