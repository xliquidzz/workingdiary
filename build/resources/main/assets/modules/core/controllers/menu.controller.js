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
        .module('menuModule')
        .controller('menuController', menuController);

    menuController.$inject = ['$location'];

    function menuController($location) {
        var vm = this;
        vm.isSelected = isSelected;

        function isSelected(viewLocation) {
            if($location.path().indexOf(viewLocation) === -1) {
                return false;
            } else {
                return true;
            }
        }
    }
}());