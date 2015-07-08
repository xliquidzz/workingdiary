exports.config = {
    seleniumAddress: 'http://localhost:4444/wd/hub',

    capabilities: {
        'browserName': 'chrome'
    },

    specs: ['user.scenario.js', 'entry.scenario.js'],

    jasmineNodeOpts: {
        showColors: true
    }
};