
includeTargets << grailsScript("_GrailsEvents")

target ('default': "Copy QUnit files to test dir") {

	def qunitVersion = '1.5.0pre'
	def files = ["qunit-${qunitVersion}.js", "qunit-boilerplate.js", "simpleTest.js"]
	def toDir = "test/unit/js"
	event("StatusUpdate", ["Copying QUnit files to $toDir"])

	ant.mkdir(dir:"${basedir}/$toDir")
	files.each {
		ant.copy(file: "${jstestPluginDir}/src/qunit/${it}",
				todir: "${basedir}/$toDir")
	}

	event("StatusFinal", ["Copying of QUnit files to $toDir finished"])

}

