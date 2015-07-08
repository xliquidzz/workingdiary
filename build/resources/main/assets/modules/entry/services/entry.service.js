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
        .module('entryModule')
        .service('entryService', entryService);

    entryService.$inject = ['$resource', '$route', 'Entry'];

    function entryService($resource, $route, Entry) {
        var vm = this;
        vm.response = {};

        return {
            create: create,
            getEntries: getEntries,
            getApprentices: getApprentices,
            getApprenticeEntries: getApprenticesEntries,
            remove: remove,
            getApprenticeOfTrainer: getApprenticeOfTrainer,
            update: update,
            entryToUpdate: {}
        };

        function create(entry) {
            var response = Entry.save(entry)
            return  response.$promise.then(
                onSuccess,
                onError
            );

            function onSuccess(data) {
                vm.response.alert = '<div class="alert alert-success">Your entry has been successfully created.</div>';
                vm.response.reset = true;
                return vm.response;
            }

            function onError(error) {
                vm.response.alert =  '<div class="alert alert-danger">Your entry could not be created. There has been an Error.</div>';
                vm.response.reset = false;
                return vm.response;
            }
        }

        function getEntries() {
            var result = Entry.query();
            result.$promise.then(
                function(success) {
                    return success;
                },
                function (error) {
                    return [];
                }
            );
            return result;
        }

        function getApprentices() {
            var result = $resource('/api/user/role/1').query();
            return result;
        }

        function getApprenticesEntries(userId) {
            var result = $resource('/api/entry/user/' + userId).query();
            return result;
        }

        function remove(entryId) {
            var result = Entry.delete({
                entryId: entryId
            });

            return result.$promise.then(function(data) {
                $route.reload();
                return data;
            });
        }

        function getApprenticeOfTrainer() {
            var result = $resource('/api/user/apprentices').query();
            return result;
        }

        function update(entryId, entry) {
            var entryToPut = {title: entry.title, message: entry.message};
            return Entry.update({entryId: entryId}, entryToPut);
        }
    }
}());