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
        .controller('listEntriesApprenticeController', listEntriesApprenticeController);

    listEntriesApprenticeController.$inject = ['$scope', '$filter', 'entryService','entryPrintService'];

    function listEntriesApprenticeController($scope, $filter, entryService, entryPrintService) {
        $scope.edit = edit;
        $scope.entries = getFilteredEntries();
        $scope.isSelected = isSelected;
        $scope.printDetailedEntry = printDetailedEntry;
        $scope.remove = remove;
        $scope.setDetailedEntry = setDetailedEntry;

        function isSelected(entryId) {
            return $scope.detailedEntry.id === entryId;
        }

        function printDetailedEntry() {
            entryPrintService.printEntry();
        }

        function edit(entry) {
            entryService.entryToUpdate = entry;
        }

        function remove() {
            entryService.remove($scope.detailedEntry.id);
        }

        function setDetailedEntry(entry) {
            $scope.$emit('detailedEntryChanged', entry);
            $scope.detailedEntry =  entry;
        }

        function onSuccess(entries) {
            $scope.filteredEntries = $filter('filter')(entries, { created: $filter('date')($scope.dt, 'dd.MM.yy')});
           // $scope.filteredEntries = entries;
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
    }
}());