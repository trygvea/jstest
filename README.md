#jstest-qunit

A grails plugin which runs javascript tests.

The plugin is in a very early stage.

## Usage
 - install the plugin
 - have a look at the sample test which is copied into your test/unit/js
 - run 'grails test-app'
 
 ### Example test/unit/js/basicTest.js
 ```javascript
Loader.load('test/unit/js/qunit-boilerplate.js');
module("My Test module");
test("successful test", function() {
	  ok(true, "Life's good" );
	  equal(100, 100, "One hundred is one hundred or I'll be damned");
});
```

## Other
Check out the jstest-sampleapp for some example tests.

The plugin uses the rhino javascript engine.