
includeTargets << grailsScript("_GrailsEvents")

target ('default': "Copy QUnit files to test dir") {

	def qunitVersion = '1.5.0pre'
	def toDir = "test/unit/js"
	event("StatusUpdate", ["Copying QUnit files to $toDir"])
	ant.copy(todir:"${basedir}/$toDir" ) {
		fileset(dir:"${jstestPluginDir}/src/qunit-sample-test")
	}
	event("StatusFinal", ["Copying of QUnit files to $toDir finished"])

}

