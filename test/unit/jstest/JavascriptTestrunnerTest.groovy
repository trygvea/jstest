package jstest

import org.junit.*

class JavaScriptTestRunnerTest {
	
	@Test
	void "compiler error should look nice"() {
		def runner = new JavaScriptTestRunner()
		assert runner.runTest("test/unit/js/compilerErrorTest_.js") == false
		assert runner.output.contains("missing ) after argument list")
	}
	
	@Test
	void "should assert false as the javascript tests are failing"() {
		def runner = new JavaScriptTestRunner()
		assert runner.runTest("test/unit/js/failingTest_.js") == false
		assert runner.output.contains("test fail")
		assert runner.output.contains("test/unit/js/failingTest_.js:5")
		println runner.output
	}
		
	@Test
	void "should assert true as the javascript tests are ok"() {
		def runner = new JavaScriptTestRunner()
		assert runner.runTest("test/unit/js/simpleTest_.js")
	}
}
