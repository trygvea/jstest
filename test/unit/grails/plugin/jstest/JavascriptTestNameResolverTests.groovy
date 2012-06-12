package grails.plugin.jstest
import org.junit.Test

class JavaScriptTestNameResolverTests {

	@Test
	void shouldResolveTestNameForUnixStylePaths() {
		assert JavaScriptTestNameResolver.resolveTestNameForFile(new File("/tmp/myApp/test/unit/js/foo/myTest.js")) == "allTests(js.foo.myTest)"
		assert JavaScriptTestNameResolver.resolveTestNameForFile(new File("/tmp/myApp/test/unit/js/myTest.js")) == "allTests(js.myTest)"
	}

	@Test
	void shouldResolveTestNameForWindowsStylePaths() {
		assert JavaScriptTestNameResolver.resolveTestNameForFile(new File("C:\\dev\\foo\\test\\unit\\js\\foo\\myTest.js")) == "allTests(js.foo.myTest)"
		assert JavaScriptTestNameResolver.resolveTestNameForFile(new File("C:\\dev\\foo\\test\\unit\\js\\myTest.js")) == "allTests(js.myTest)"
	}

}
