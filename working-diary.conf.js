module.exports = function(config){
    var resourcePath = 'resources/assets/';
    var bowerPath =  resourcePath + 'lib/bower_components/';
    var testPath = 'test/client/';
    config.set({
        basePath : 'D:/01_css/WorkingDiary/src/main',
        files : [
            bowerPath + 'angular/angular.js',
            bowerPath + 'angular-route/angular-route.js',
            bowerPath + 'angular-mocks/angular-mocks.js',
            bowerPath + 'angular-resource/angular-resource.js',
            bowerPath + 'angular-sanitize/angular-sanitize.js',
            resourcePath + 'lib/text-angular/**/*.js',
            resourcePath + 'modules/core/*.js',
            resourcePath + 'modules/entry/*.js',
            resourcePath + 'modules/user/*.js',
            resourcePath + 'modules/core/**/*.js',
            resourcePath + 'modules/user/**/*.js',
            testPath + '**/*.js',
            resourcePath + 'modules/entry/**/*.js',

        ],
        autoWatch : true,
        frameworks: ['jasmine'],
        browsers : ['Chrome'],
        plugins : [
            'karma-chrome-launcher',
            'karma-firefox-launcher',
            'karma-jasmine',
            'karma-junit-reporter'
        ],
        junitReporter : {
            outputFile: 'test_out/unit.xml',
            suite: 'unit'
        },
        require: []
    });
};