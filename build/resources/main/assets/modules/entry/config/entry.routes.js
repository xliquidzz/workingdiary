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
    angular.module('entryModule').config(['$routeProvider', function ($routeProvider) {
        $routeProvider
            .when('/', {
                resolve: {
                    roleId: ['$location', 'loginService', function ($location, loginService) {
                        var request = loginService.getRoleId();
                        return request.$promise.then(function (success) {
                            var roleId = success.roleId;
                            if(roleId === 1) {
                                $location.path('/apprentice/working-diary');
                            } else if (roleId === 2) {
                                $location.path('/trainer/working-diary');
                            } else if (roleId === 3) {
                                $location.path('/vocation-trainer/working-diary');
                            }
                            return roleId;
                        });
                    }]
                }
            })
            .when('/apprentice/working-diary', {
                templateUrl: 'modules/entry/views/list-entries.html',
                controller: 'listEntriesApprenticeController'
            })
            .when('/apprentice/entry', {
                templateUrl: 'modules/entry/views/list-entries.html',
                controller: 'listEntriesApprenticeController'
            })
            .when('/apprentice/working-diary/new', {
                templateUrl: 'modules/entry/views/create-entry.html',
                controller: 'createEntryController'
            })
            .when('/vocation-trainer/working-diary', {
                templateUrl: 'modules/entry/views/display-entries-for-trainers.html',
                controller: 'listEntriesVocationTrainerController'
            })
            .when('/trainer/working-diary', {
                templateUrl: 'modules/entry/views/display-entries-for-trainers.html',
                controller: 'listEntriesTrainerController',
                resolve: {
                    users: ['entryService', function(entryService) {
                        return entryService.getApprenticeOfTrainer();
                    }]
                }
            })
            .when('/apprentice/working-diary/update', {
                templateUrl: 'modules/entry/views/update-entry.html',
                controller: 'updateEntryController'
            })
            .otherwise ({
            redirectTo: '/'
        });
    }]);
}());