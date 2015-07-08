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

(function () {
    angular
        .module('utilModule')
        .directive('wdSpinner', wdSpinner);

    wdSpinner.$inject = ['$scope'];

    function wdSpinner($scope) {
        scope.$on('startSpinner', toggleSpinner);
        $scope.$on('startSpinner', toggleSpinner);

        return {
            restrict: 'E',
            template: '<div ng-bind-html="wdSpinner"></div>',
            scope: {
                isLoading: false,
                wdSpinner: '<i class="fa fa-spinner fa-pulse fa-2x"></i>'
            }
        };

        function toggleSpinner() {
            console.log(isLoading);
            console.log(scope.isLoading);
            if(isLoading === false) {
                isLoading = true;
            } else {
                isLoading = false;
            }
            return isLoading;
        }
    }
}());