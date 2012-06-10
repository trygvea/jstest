#jstest

A grails plugin which runs javascript tests placed in your test/unit/js.

The plugin is bundled with QUnit, but you're free to use whatever javascript testing framework you want. (look at the FAQ for more info).

## Usage
 - install the plugin
 - install a sample javascript test, 'grails install-qunit'
 - run 'grails test-app'
 - run a single test with 'grails test-app :javascript simpleTest'
 
 ### Example test/unit/js/basicTest.js
 ```javascript
Loader.load('test/unit/js/qunit-boilerplate.js');
module("My Test module");
test("successful test", function() {
	  ok(true, "Life's good" );
	  equal(100, 100, "One hundred is one hundred or I'll be damned");
});
```


### FAQ

## The test doesn't find my javascript tests.
The files must end with *Test.js and live in test/unit/js of your project.

## I want to run a test method instead of a whole javascript test file
Sorry, not possible.

The reason is that this would force this plugin to understand the content of the javascript -file, and parse QUnit -specifics and pass it to jUnit. We're not going there. Not yet atleast.

# I don't wanna use QUnit. What do I do?
You need to make your test framework throw an exception in javascript when you intend a test to fail.

Have a loook at qunit-boilerplate.js If you wanna see why QUnit works quite well. QUnit allows us to attach callbacks when tests have run, you need simular stuff in your framework.

# I want some DOM tests.
Have a look at the [DOM test](https://github.com/finnjohnsen/jstest-sampleapp/blob/master/test/unit/js/simpleDOMTest.js) in the jstest-sampleapp on github. Basically you need envjs.

Note that envjs slows the tests down sigificantly.

# This doesn't work well with <MY IDE>
We've tested intelliJ and Springsource Tool Suite at the time of writing. But honestly, the way grails and the IDE's interract via JUnit is black magic.

### Other
The plugin uses the Rhino javascript engine.

### Contributors and thanks
- Ronny Løvtangen
- Fredrik Aubert
- Peter Ledbrook @ Gr8conf 2012

### Links
- http://docs.jquery.com/Qunit
- http://www.mozilla.org/rhino/
