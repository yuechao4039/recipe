package com.sndj.recipe.scan;

import java.io.IOException;
import java.util.List;

public interface PackageScanner {
    public List<String> getFullyQualifiedClassNameList() throws IOException;
}