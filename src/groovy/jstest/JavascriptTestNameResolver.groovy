package jstest

class JavascriptTestNameResolver {

	/**
	 * Calculates the test name for a given JavaScript test file.
	 * Needs to follow the pattern xxx(yyy.zzz) to integrate with JUnit runner in IntelliJ IDEA.
	 * @param file
	 * @return test name
	 */
	static String resolveTestNameForFile(File file) {
		String relativePath = file.path.split('test/unit/')[1]
		String relativePathWithoutExtension = relativePath.split('.js$')[0]
		String testClassName = relativePathWithoutExtension.replaceAll('/', '.')
		"allTests(${testClassName})"
	}

}
