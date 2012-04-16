//
// This script is executed by Grails after plugin was installed to project.
// This script is a Gant script so you can use all special variables provided
// by Gant (such as 'baseDir' which points on project base dir). You can
// use 'ant' to access a global instance of AntBuilder
//
// For example you can create directory under project tree:
//
//    ant.mkdir(dir:"${basedir}/grails-app/jobs")
//

def qunitVersion = '1.5.0pre'
def files = ["qunit-${qunitVersion}.js", "qunit-boilerplate.js", "simpleTest.js"]

ant.mkdir(dir:"${basedir}/test/unit/js")
files.each {
	ant.copy(file: "${pluginBasedir}/src/qunit/${it}",
			todir: "${basedir}/test/unit/js")
}