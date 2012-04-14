package jstest
import org.codehaus.groovy.grails.test.GrailsTestTypeResult

class GrailsJavascriptTestTypeResult implements GrailsTestTypeResult {
	int runCount = 0
	int failCount = 0

	int getPassCount() {
		runCount - failCount
	}
}
