package grails.plugin.jstest

import org.mozilla.javascript.Context;
import org.mozilla.javascript.ContextFactory
import org.mozilla.javascript.EvaluatorException;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.tools.shell.Global

class JavaScriptTestRunner {
	Context cx
	Scriptable scope
	
	String output = ""

	JavaScriptTestRunner() {
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
	
	Boolean runTest(String javaScriptFile) {
		lastException = null
		output = ""
		try {
			Object result = cx.evaluateReader(scope, new FileReader(javaScriptFile), javaScriptFile, 1, null);
			output = cx.toString(result)
			if (output != "undefined") println output //not sure what the "undefined" is from, but it's unnecessary I think.
			return true
		}catch(EvaluatorException evalEx) { // aka compiler error
			output += "JavaScript evaluator exception:\n"
			output += evalEx.message + "\n"
			println output
			lastException = evalEx
			return false
		}catch (all) {
			output += "JavaScript test fail at:\n"
			String[] stack = all.getScriptStackTrace().split('at')
			output += stack[stack.length-1] //this is the relevant line in the test.
			lastException = all
			println output
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
