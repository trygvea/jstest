package grails.plugin.jstest

class JavaScriptTestNameResolver {

	/**
	 * Calculates the test name for a given JavaScript test file.
	 * Needs to follow the pattern xxx(yyy.zzz) to integrate with JUnit runner in IntelliJ IDEA.
	 * @param file
	 * @return test name
	 */
	static String resolveTestNameForFile(File file) {
		String pathWithoutExtension = file.path.split('.js$')[0]
		String testClassName
		if (pathWithoutExtension.contains("/")) { //unix, linux, mac
			String relativePathWithoutExtension = pathWithoutExtension.split('test/unit/')[1]
			testClassName = relativePathWithoutExtension.replaceAll('/', '.')
		} else { //windows
			String relativePathWithoutExtension = pathWithoutExtension.split("test\\\\unit\\\\")[1]
			testClassName = relativePathWithoutExtension.replaceAll('\\\\', '.')
		}
		"allTests(${testClassName})"
	}

}
