#jstest

A grails plugin which runs javascript tests.

The plugin is in a very early stage.

You are free to use which javascript testing framework you want, but we've supplied QUnit for convention over configuration.


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

### I don't wanna use QUnit
That's fine, but you need to make your test framework throw an exception in javascript when you intend a test to fail.

Have a loook at qunit-boilerplate if you wanna see why qunit works. QUnit allows us to attach callbacks when tests have run, you need simular stuff in your framework.

### Other
Check out the jstest-sampleapp for some example tests.
 This was easy to do in QUnit, so we chose it.
 
The plugin uses the rhino javascript engine.

### Links
http://docs.jquery.com/Qunit
http://www.mozilla.org/rhino/