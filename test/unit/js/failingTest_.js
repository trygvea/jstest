Loader.load('test/unit/js/lib/qunit-boilerplate.js');
		
module("This is my failing module");

test("a failing test", function() {
	  equal(100, 101, "Oh no!");
});
