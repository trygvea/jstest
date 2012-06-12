package grails.plugin.jstest
import org.codehaus.groovy.grails.test.GrailsTestTypeResult


class GrailsJavaScriptTestTypeResult implements GrailsTestTypeResult {
	int runCount = 0
	int failCount = 0
	int getPassCount() {
		runCount - failCount
	}
}
