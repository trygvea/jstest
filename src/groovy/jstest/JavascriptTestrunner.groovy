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
	Boolean printOutput = false
	
	JavascriptTestrunner() {
		Global global = new Global();
		cx = ContextFactory.getGlobal().enterContext(); //Context.enter()
		global.init(cx);
		cx.setLanguageVersion(Context.VERSION_1_6)
		
		//if (debug) scope = cx.initStandardObjects(org.mozilla.javascript.tools.shell.Main.getGlobal());
		scope = cx.initStandardObjects(global)
		
		scope.put("Loader", scope, new Loader(cx, scope))
		cx.setGeneratingSource(true)
		cx.setGeneratingDebug(true)
		cx.setGenerateObserverCount(true)
		//cx.setOptimizationLevel (-1);
		cx.setOptimizationLevel(9);
	}
	
	Boolean runTest(String javascriptfile) {
		try {
			Object result = cx.evaluateReader(scope, new FileReader(javascriptfile), javascriptfile, 1, null);
			if (printOutput) println cx.toString(result)
			return true
		}catch (all) {
			println all.getMessage()
			println "Stack trace:"
			println all.getScriptStackTrace()
			return false
		}
	}
	
	def runJavascriptFile(Context cx, Scriptable scope, String fileName) {
		return cx.evaluateReader(scope, new FileReader(fileName), fileName, 1, null);
		//return cx.evaluateString(scope, new File(fileName).text, fileName, 1, null)
	}
	
	class Loader {
		//ContextFactory factory = new ContextFactory();
		
		Context cx
		Scriptable scope
		def load(String fileName) {
			cx.evaluateReader(scope, new FileReader(fileName), fileName, 1, null);
			//cx.evaluateString(scope, new File(fileName).text, fileName, 1, null)
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
