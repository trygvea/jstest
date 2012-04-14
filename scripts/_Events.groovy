loadJavascriptTestTypeClass = { ->
	def doLoad = { -> classLoader.loadClass('jstest.GrailsJavascriptTestType') }
	try {
	  doLoad()
	} catch (ClassNotFoundException e) {
	  includeTargets << grailsScript("_GrailsCompile")
	  compile()
	  doLoad()
	}
}

loadJavascriptTestTypes = {
//  if (!binding.variables.containsKey("unitTests")) return
  def javascriptTestTypeClass = loadJavascriptTestTypeClass()
  [unit: unitTests].each { name, types ->
    if (!types.any { it.class == javascriptTestTypeClass }) {
      types << javascriptTestTypeClass.newInstance('javascript', name)
    }
  }
}

eventAllTestsStart = {
  loadJavascriptTestTypes()
}

eventPackagePluginsEnd = {
  loadJavascriptTestTypes()
}
