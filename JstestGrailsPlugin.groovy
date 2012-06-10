class JstestGrailsPlugin {
    def version = "0.2-SNAPSHOT"
    def grailsVersion = "2.0 > *"
    def dependsOn = [:]
    def pluginExcludes = [
        "grails-app/views/error.gsp"
    ]

    def title = "jsTest Plugin"
    def author = "Finn Johnsen"
    def authorEmail = "finn.johnsen@gmail.com"
    def description = '''\
	This plugin runs javascript tests in the test phase. 

    Check out the readme in the source at github for the documentation:
	http://github.com/finnjohnsen/jstest 
'''

    def documentation = "http://grails.org/plugin/jstest"
    def license = "APACHE"
    def developers = [ 
		[ name: "Ronny Løvtangen", email: "ronny@lovtangen.com" ],
		[ name: "Fredrik Aubert", email: "fredrik.aubert@gmail.com" ]
		]
//    def issueManagement = [ system: "JIRA", url: "http://jira.grails.org/browse/GPMYPLUGIN" ]
    def scm = [ url: "https://github.com/finnjohnsen/jstest" ]
}
