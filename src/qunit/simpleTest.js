Loader.load('test/unit/js/qunit-boilerplate.js')
		
module("JavascriptTestrunner tests");

test("successful test", function() {
	  ok(true, "Life's good" );
	  equal(100, 100, "One hundred is one hundred or I'll be damned");
});
