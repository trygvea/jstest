package jstest

import jstest.JavascriptTestrunner;
import org.junit.*

class JavascriptTestrunnerTest {
	
	@Test
	void "compiler error should look nice"() {
		def runner = new JavascriptTestrunner()
		assert runner.runTest("test/unit/js/compilerErrorTest_.js") == false
		assert runner.output.contains("missing ) after argument list")
	}
	
	@Test
	void "should assert false as the javascript tests are failing"() {
		def runner = new JavascriptTestrunner()
		assert runner.runTest("test/unit/js/failingTest_.js") == false
		assert runner.output.contains("Test failed")
		assert runner.output.contains("at: test/unit/js/failingTest_.js:5")
	}
		
	@Test
	void "should assert true as the javascript tests are ok"() {
		def runner = new JavascriptTestrunner()
		assert runner.runTest("test/unit/js/simpleTest_.js")
	}
}
