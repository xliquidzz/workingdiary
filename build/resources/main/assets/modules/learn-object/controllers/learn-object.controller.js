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
    angular.module('learnObjectModule')
        .controller('learnObjectController', learnObjectController);

    learnObjectController.$inject = ['$scope', '$routeParams', '$filter'];

    function learnObjectController($scope, $routeParams, $filter) {

        $scope.learnYear = $routeParams.learnYear;

        var ratings = [
            {id: 1, rating: 5},
            {id: 2, rating: 3},
            {id: 3, rating: 6},
            {id: 4, rating: 4},
            {id: 5, rating: 2}
        ];

        var semesters = [
            {id: 1, semester: 1, planned: true, done: false, interCompanyCourse: false, vocationalSchool: true},
            {id: 2, semester: 2, planned: false, done: true, interCompanyCourse: true, vocationalSchool: true},
            {id: 3, semester: 3, planned: true, done: false, interCompanyCourse: false, vocationalSchool: false},
            {id: 4, semester: 4, planned: true, done: false, interCompanyCourse: true, vocationalSchool: false},
            {id: 5, semester: 5, planned: false, done: false, interCompanyCourse: false, vocationalSchool: false},
            {id: 6, semester: 6, planned: true, done: false, interCompanyCourse: false, vocationalSchool: true},
            {id: 7, semester: 7, planned: true, done: false, interCompanyCourse: true, vocationalSchool: false},
            {id: 8, semester: 8, planned: true, done: false, interCompanyCourse: false, vocationalSchool: true}
        ];

        var goals = [
            {id: 1, designation: 'A.1.1', description: 'any Description of the goal', semesters: semesters, rating: ratings[0]},
            {id: 2, designation: 'A.2.1', description: 'any Description of the goal',  semesters: semesters, rating: ratings[1]},
            {id: 3, designation: 'A.3.1', description: 'any Description of the goal',  semesters: semesters, rating: ratings[2]},
            {id: 3, designation: 'A.4.1', description: 'any Description of the goal',  semesters: semesters, rating: ratings[2]},
            {id: 3, designation: 'A.5.1', description: 'any Description of the goal',  semesters: semesters, rating: ratings[2]}
        ];


        var competences = [
            {id: 1, number: '1', description: 'Anforderungen und Bedürfnisse analysieren und strukturieren und dokumentieren', goals: goals},
            {id: 2, number: '2', description: 'Verschiedene Lösungsvorschläge mit den notwendigen Benutzerschnittstellen erarbeiten', goals: goals},
            {id: 3, number: '3', description: 'Anforderungen und Bedürfnisse in den gewählten Lösungsvorschlägen auf Vollständigkeit überprüfen', goals: goals},
        ];

        var competences2 = [
            {id: 3, number: '1', description: 'Anforderungen und Bedürfnisse analysieren und strukturieren und dokumentieren', goals: goals},
            {id: 4, number: '2', description: 'Verschiedene Lösungsvorschläge mit den notwendigen Benutzerschnittstellen erarbeiten', goals: goals},
            {id: 5, number: '3', description: 'Anforderungen und Bedürfnisse in den gewählten Lösungsvorschlägen auf Vollständigkeit überprüfen', goals: goals},
        ];

        var competences3 = [
            {id: 6, number: '1', description: 'Anforderungen und Bedürfnisse analysieren und strukturieren und dokumentieren', goals: goals},
            {id: 7, number: '2', description: 'Verschiedene Lösungsvorschläge mit den notwendigen Benutzerschnittstellen erarbeiten', goals: goals},
            {id: 8, number: '3', description: 'Anforderungen und Bedürfnisse in den gewählten Lösungsvorschlägen auf Vollständigkeit überprüfen', goals: goals},
        ];

        var competences4 = [
            {id: 9, number: '1', description: 'Anforderungen und Bedürfnisse analysieren und strukturieren und dokumentieren', goals: goals},
            {id: 10, number: '2', description: 'Verschiedene Lösungsvorschläge mit den notwendigen Benutzerschnittstellen erarbeiten', goals: goals},
            {id: 11, number: '3', description: 'Anforderungen und Bedürfnisse in den gewählten Lösungsvorschlägen auf Vollständigkeit überprüfen', goals: goals},
        ];

        $scope.competenceAreas = [
            {id: 1, letter: 'A', description: 'Handlungskompetenzbereich A, Erfassen, Interpretieren und Darstellen von Anforderungen für Applikationen', competences: competences},
            {id: 2, letter: 'B', description: 'Handlungskompetenzbereich B, Entwickeln von Applikation unter Berücksichtigung von Qualitätsmerkmalen', competences: competences2},
            {id: 3, letter: 'C', description: 'Handlungskompetenzbereich C, Aufbauen und pflegen von Daten sowie von deren Strukturen', competences: competences3},
            {id: 4, letter: 'D', description: 'Handlungskompetenzbereich D, Inbetriebnahme von ICT-Geräten', competences: competences4}
        ];

        $scope.getObjectByAttribute = function(semesters, semester) {
            var found = $filter('filter')(semesters, {semester: semester}, true);
            if(found.length === 1) {
                return found[0];
            }
        };

        $scope.setRating = function(index) {
            ;
        };

        $scope.remove = function() {

        };

    }
}());