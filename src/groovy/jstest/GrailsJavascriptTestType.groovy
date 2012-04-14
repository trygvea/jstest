package jstest

//import grails.plugin.spock.test.listener.OverallRunListener

import org.junit.runner.JUnitCore
import org.codehaus.groovy.grails.test.GrailsTestTypeResult
import org.codehaus.groovy.grails.test.event.GrailsTestEventPublisher
import org.codehaus.groovy.grails.test.support.GrailsTestTypeSupport
import org.codehaus.groovy.grails.test.report.junit.JUnitReportsFactory


class GrailsJavascriptTestType extends GrailsTestTypeSupport {

	
	GrailsJavascriptTestType(String name, String relativeSourcePath) {
		super(name, relativeSourcePath)
		//println "${name} ${relativeSourcePath}"
	  }
	
	def files
	@Override
	protected int doPrepare() {
		def basedir = new File('test/unit/js')
		files = basedir.listFiles().grep(~/.*Test.js$/)
		return files.size()
	}

	@Override
	protected GrailsTestTypeResult doRun(GrailsTestEventPublisher eventPublisher) {
		
		int failCount = 0
		for (File currentFile in files) { 
			println "running ${currentFile.path}"
			if (!new JavascriptTestrunner().runTest(currentFile.path)) failCount ++
			
		}
		return new GrailsJavascriptTestTypeResult(runCount:files.size(), failCount:failCount);
	}
}
