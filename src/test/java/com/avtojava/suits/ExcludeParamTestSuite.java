package com.avtojava.suits;

import org.junit.platform.suite.api.ExcludeTags;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectPackages("com.avtojava")
@ExcludeTags("param")
public class ExcludeParamTestSuite {
}
