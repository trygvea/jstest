loadJavaScriptTestTypeClass = { ->
	def doLoad = { -> classLoader.loadClass('jstest.GrailsJavaScriptTestType') }
	try {
	  doLoad()
	} catch (ClassNotFoundException e) {
	  includeTargets << grailsScript("_GrailsCompile")
	  compile()
	  doLoad()
	}
}

loadJavaScriptTestTypes = {
//  if (!binding.variables.containsKey("unitTests")) return
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
