package jstest

//import grails.plugin.spock.test.listener.OverallRunListener


import org.junit.runner.*

import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runners.Suite

import org.codehaus.groovy.grails.test.GrailsTestTypeResult
import org.codehaus.groovy.grails.test.event.GrailsTestEventPublisher
import org.codehaus.groovy.grails.test.event.GrailsTestRunNotifier

import org.codehaus.groovy.grails.test.junit4.listener.SuiteRunListener

import org.codehaus.groovy.grails.test.junit4.runner.GrailsTestCaseRunnerBuilder;
import org.codehaus.groovy.grails.test.support.GrailsTestTypeSupport
import org.codehaus.groovy.grails.test.report.junit.JUnitReportsFactory

// grails test-app unit: simple*
class GrailsJavaScriptTestType extends GrailsTestTypeSupport {


	protected suite
	protected mode
	
	static final SUFFIXES = ["Test", "Tests"].asImmutable()
	
	protected List<String> getTestSuffixes() { SUFFIXES }
	protected List<String> getTestExtensions() {
		["js"]
	}

	GrailsJavaScriptTestType(String name, String relativeSourcePath) {
		super(name, relativeSourcePath + "/js")
	  }
	
	def files = []
		
	
	@Override
	protected int doPrepare() {

		eachSourceFile { testTargetPattern, sourceFile ->
				files << sourceFile
		}
		if(files) {
			return files.size()
		} else {
			return 0
		}
	}

	@Override
	protected GrailsTestTypeResult doRun(GrailsTestEventPublisher eventPublisher) {
		
		GrailsTestRunNotifier notifier = createNotifier(eventPublisher)
		def result = new Result()
		notifier.addListener(result.createListener())
//
//		suite.run(notifier)
//
//		notifier.fireTestRunFinished(result)
//		new JUnit4ResultGrailsTestTypeResultAdapter(result)
		
		JUnitReportsFactory reportsFactory = createJUnitReportsFactory()
		int failCount = 0
		for (File currentFile in files) {
			String testName = JavaScriptTestNameResolver.resolveTestNameForFile(currentFile)
			def description = new Description(testName)
			notifier.fireTestStarted(description)
			JavaScriptTestRunner runner = new JavaScriptTestRunner()
			if (!runner.runTest(currentFile.path)) {
				failCount ++
				notifier.fireTestFailure(new Failure(description, runner.lastException))
			}
			notifier.fireTestFinished(description)
			reportsFactory.createReports(testName)
		}
		notifier.fireTestRunFinished(result)

		return new GrailsJavaScriptTestTypeResult(runCount:files.size(), failCount:failCount);
	}
	
	protected createRunnerBuilder() {
//		if (mode) {
//			new GrailsTestCaseRunnerBuilder(mode, getApplicationContext(), testTargetPatterns)
//		}
//		else {
			new GrailsTestCaseRunnerBuilder(testTargetPatterns)
//		}
	}

	protected createSuite(classes) {
		new Suite(createRunnerBuilder(), classes as Class[])
	}

	protected createJUnitReportsFactory() {
		JUnitReportsFactory.createFromBuildBinding(buildBinding)
	}

	protected createListener(eventPublisher) {
		new SuiteRunListener(eventPublisher, createJUnitReportsFactory(), createSystemOutAndErrSwapper())
	}

	protected createNotifier(eventPublisher) {
		int total = files.size()
		def notifier = new GrailsTestRunNotifier(total)
		notifier.addListener(createListener(eventPublisher))
		notifier
	}

	
	
	
}
