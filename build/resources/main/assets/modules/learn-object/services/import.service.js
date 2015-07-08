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

/*
(function() {
    angular.module('educationGoalModule').service('importService', [function() {
        return {
           csvToJSON = csvToJSON
        };

        function csvToJSON(csv){
            var lines = csv.split("\n");
            var result = [];
            var headers = lines[0].split(",");

            for( var i=1; i < lines.length; i++ ) {
                var obj = {};
                var currentline=lines[i].split(",");

                for( var j = 0; j < headers.length; j++ ) {
                    obj[headers[j]] = currentline[j];
                }
                result.push(obj);
            }
            return JSON.stringify(result);
        }
    }]);
});*/