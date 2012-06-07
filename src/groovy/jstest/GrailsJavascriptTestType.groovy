package jstest

//import grails.plugin.spock.test.listener.OverallRunListener

import org.junit.runner.JUnitCore
import org.codehaus.groovy.grails.test.GrailsTestTypeResult
import org.codehaus.groovy.grails.test.event.GrailsTestEventPublisher
import org.codehaus.groovy.grails.test.support.GrailsTestTypeSupport
import org.codehaus.groovy.grails.test.report.junit.JUnitReportsFactory

// grails test-app unit: simple*
class GrailsJavascriptTestType extends GrailsTestTypeSupport {
	
	GrailsJavascriptTestType(String name, String relativeSourcePath) {
		super(name, relativeSourcePath + "/js")
//		println "${name} ${relativeSourcePath}"
	  }
	
	def files = []
	
//	@Override
//	protected List<File> findSourceFiles(GrailsTestTargetPattern targetPattern) {
//	}
	
	protected List<String> getTestSuffixes() {
		return ["Test"]
	}
	
	protected List<String> getTestExtensions() {
		return ["js"]
	}
	
	@Override
	protected int doPrepare() {
		
		eachSourceFile { testTargetPattern, sourceFile ->
			println "${testTargetPattern} ${sourceFile}"
			files << sourceFile
		}
		
		//def basedir = new File('test/unit/js')
		//files = basedir.listFiles().grep(~/.*Test.js$/)
		return files.size()
	}

	@Override
	protected GrailsTestTypeResult doRun(GrailsTestEventPublisher eventPublisher) {
		int failCount = 0
		for (File currentFile in files) {
			def testCaseName = currentFile.parent
			def testName = currentFile.name
			eventPublisher.testCaseStart(testCaseName)
			eventPublisher.testStart(testName)
			if (new JavascriptTestrunner().runTest(currentFile.path)) {
				eventPublisher.testEnd(testName)
			} else {
				eventPublisher.testFailure(testName, new RuntimeException("$testName failed"))
				failCount ++
			}
			eventPublisher.testCaseEnd(testCaseName)
		}
		
		return new GrailsJavascriptTestTypeResult(runCount:files.size(), failCount:failCount);
	}
}
