package jstest
import grails.test.mixin.*

import org.mozilla.javascript.Context;
import org.mozilla.javascript.ContextFactory
import org.mozilla.javascript.EvaluatorException;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.tools.shell.Global

class JavascriptTestrunner {
	Context cx
	Scriptable scope
	
	String output = ""
	
	JavascriptTestrunner() {
		Global global = new Global();
		cx = ContextFactory.getGlobal().enterContext();
		global.init(cx);
		cx.setLanguageVersion(Context.VERSION_1_6)
		scope = cx.initStandardObjects(global)
		scope.put("Loader", scope, new Loader(cx, scope))
		cx.setGeneratingSource(true)
		cx.setGeneratingDebug(true)
		cx.setGenerateObserverCount(true)
		cx.setOptimizationLevel(-1);
	}
	
	Exception lastException
	
	Boolean runTest(String javascriptfile) {
		lastException = null
		output = ""
		try {
			Object result = cx.evaluateReader(scope, new FileReader(javascriptfile), javascriptfile, 1, null);
			output = cx.toString(result)
			if (output != "undefined") println output //not sure what it's from, but it's uneccesary I think.
			return true
		}catch(EvaluatorException evalEx) { // compiler error
			output += "Javascript evaluator exception:\n"
			output += evalEx.message + "\n"
			println output
			lastException = evalEx
			return false
		}catch (all) {
			String[] stack = all.getScriptStackTrace().split("at")
			String relevantLine = stack[stack.size()-1].trim()
			output += all.message?.replace("\n (test/unit/js/qunit-boilerplate.js#10)","") + "\n"
			output += "at: " + relevantLine
			lastException = all
			println output
			//println all.getScriptStackTrace()
			return false
		} catch (all) {
			String[] stack = all.getScriptStackTrace().split("at")
			String relevantLine = stack[stack.size()-1].trim()
			println "fail at: " + relevantLine
			//println "Stack trace:" + all.getScriptStackTrace()
			return false
		}
	}
	
	def runJavascriptFile(Context cx, Scriptable scope, String fileName) {
		return cx.evaluateReader(scope, new FileReader(fileName), fileName, 1, null);
	}
	
	class Loader {
		Context cx
		Scriptable scope
		def load(String fileName) {
			cx.evaluateReader(scope, new FileReader(fileName), fileName, 1, null);
		}
		def println(String string) {
			System.out.println(string);
		}
		Loader(Context cx, Scriptable scope) {
			this.cx = cx
			this.scope = scope
		}
	}
}
