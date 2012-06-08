package jstest

//import grails.plugin.spock.test.listener.OverallRunListener


import org.junit.runner.*
import org.junit.runner.JUnitCore
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runners.Suite
import org.codehaus.groovy.grails.test.GrailsTestTargetPattern;
import org.codehaus.groovy.grails.test.GrailsTestTypeResult
import org.codehaus.groovy.grails.test.event.GrailsTestEventPublisher
import org.codehaus.groovy.grails.test.event.GrailsTestRunNotifier
import org.codehaus.groovy.grails.test.junit4.JUnit4GrailsTestType

import org.codehaus.groovy.grails.test.junit4.listener.SuiteRunListener
import org.codehaus.groovy.grails.test.junit4.result.JUnit4ResultGrailsTestTypeResultAdapter;
import org.codehaus.groovy.grails.test.junit4.runner.GrailsTestCaseRunnerBuilder;
import org.codehaus.groovy.grails.test.support.GrailsTestTypeSupport
import org.codehaus.groovy.grails.test.report.junit.JUnitReportsFactory

// grails test-app unit: simple*
class GrailsJavascriptTestType extends GrailsTestTypeSupport {


	protected suite
	protected mode
	
	static final SUFFIXES = ["Test", "Tests"].asImmutable()
	
	protected List<String> getTestSuffixes() { SUFFIXES }
	protected List<String> getTestExtensions() {
		["js"]
	}
	
	GrailsJavascriptTestType(String name, String relativeSourcePath) {
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
		SuiteRunListener listner = createListener()
		int failCount = 0
		for (File currentFile in files) {
			notifier.fireTestStarted(new Description(currentFile.name))
			JavascriptTestrunner runner = new JavascriptTestrunner()
			if (!runner.runTest(currentFile.path)) {
				failCount ++
				notifier.fireTestFailure(new Failure(new Description(currentFile.name), runner.lastException))
			}
			notifier.fireTestFinished(new Description(currentFile.name)) 
			reportsFactory.createReports(currentFile.name)
			
		}
		notifier.fireTestRunFinished(result)
		
		return new GrailsJavascriptTestTypeResult(runCount:files.size(), failCount:failCount);
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
