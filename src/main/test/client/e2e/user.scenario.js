describe('user scenarios', function() {
    it('should login and display overview of entries', function() {
        element(by.model('user.username')).sendKeys('test_apprentice');
        element(by.model('user.password')).sendKeys('12345');
        element(by.css('.modal > :button')).click();
        expect(browser.getCurrentUrl()).toBe('/apprentice/entry');
    });
});