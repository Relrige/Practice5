package com.avtojava.suits;

import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectPackages("com.avtojava")
@IncludeTags("param")
public class ParamTestSuite {
}
