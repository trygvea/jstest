package jstest

import org.junit.Test

class JavascriptTestNameResolverTests {

	@Test
	void shouldResolveTestNameForUnixStylePaths() {
		assert JavascriptTestNameResolver.resolveTestNameForFile(new File("/tmp/myApp/test/unit/js/foo/myTest.js")) == "allTests(js.foo.myTest)"
		assert JavascriptTestNameResolver.resolveTestNameForFile(new File("/tmp/myApp/test/unit/js/myTest.js")) == "allTests(js.myTest)"
	}

	@Test
	void shouldResolveTestNameForWindowsStylePaths() {
		assert JavascriptTestNameResolver.resolveTestNameForFile(new File("C:\\dev\\foo\\test\\unit\\js\\foo\\myTest.js")) == "allTests(js.foo.myTest)"
		assert JavascriptTestNameResolver.resolveTestNameForFile(new File("C:\\dev\\foo\\test\\unit\\js\\myTest.js")) == "allTests(js.myTest)"
	}

}
