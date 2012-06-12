loadJavaScriptTestTypeClass = { ->
	def doLoad = { -> classLoader.loadClass('grails.plugin.jstest.GrailsJavaScriptTestType') }
	try {
	  doLoad()
	} catch (ClassNotFoundException e) {
	  includeTargets << grailsScript("_GrailsCompile")
	  compile()
	  doLoad()
	}
}

loadJavaScriptTestTypes = {
  def javaScriptTestTypeClass = loadJavaScriptTestTypeClass()
  [unit: unitTests].each { name, types ->
    if (!types.any { it.class == javaScriptTestTypeClass }) {
      types << javaScriptTestTypeClass.newInstance('javascript', name)
    }
  }
}

eventAllTestsStart = {
  loadJavaScriptTestTypes()
}

eventPackagePluginsEnd = {
  loadJavaScriptTestTypes()
}
