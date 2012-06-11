Loader.load('test/unit/js/lib/qunit-boilerplate.js');
		
module("JavaScriptTestRunner tests");

test("successful test", function() {
	  ok(true, "Life's good" );
	  equal(100, 100, "One hundred is one hundred or I'll be damned");
});
