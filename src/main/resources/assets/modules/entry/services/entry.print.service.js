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
        .module('entryModule')
        .service('entryPrintService', entryPrintService);

    function entryPrintService() {
        return {
            printEntry: printEntry
        };

        function printEntry() {
            var printContents = document.getElementById('entryToPrint').innerHTML;
            var popupWin = window.open('', '_blank', 'width=1000,height=600');
            popupWin.document.open();
            popupWin.document.write('<html><head><link rel="stylesheet" type="text/css" href="style/layouts/print.css"/><link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css"></head><body onload="window.print()">' + printContents + '</html>');
            popupWin.document.close();
        }
    }
}());