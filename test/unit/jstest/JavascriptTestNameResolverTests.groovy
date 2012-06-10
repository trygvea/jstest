package jstest

import org.junit.Test

class JavascriptTestNameResolverTests {

	@Test
	void shouldResolveTestNameForFile() {
		 assert JavascriptTestNameResolver.resolveTestNameForFile(new File("/tmp/myApp/test/unit/js/foo/myTest.js")) == "allTests(js.foo.myTest)"
		 assert JavascriptTestNameResolver.resolveTestNameForFile(new File("/tmp/myApp/test/unit/js/myTest.js")) == "allTests(js.myTest)"
	}

}
