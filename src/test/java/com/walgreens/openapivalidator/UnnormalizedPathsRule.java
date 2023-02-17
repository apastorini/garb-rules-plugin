package com.walgreens.openapivalidator;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Paths;
import io.swagger.v3.oas.models.media.Schema;
import org.intellij.lang.annotations.RegExp;
import org.springdoc.api.OpenApiCustomiser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class UnnormalizedPathsRule implements OpenApiCustomiser {

    public final List<String> errors= new ArrayList<>();



    @Override
    public void customise(OpenAPI openApi) {

        Paths paths = openApi.getPaths();

        paths.forEach((name, pathItem) -> {
             errors.addAll(checkIfPropertyHasExampleValue(name));
        });
    }

    private List<String> checkIfPropertyHasExampleValue(String path) {
        List<String> errores = new ArrayList<>();
        String regexTrailing = "^(/[a-z0–9/_-]*(?<!/))([^/]*)$";
        if (path == null) {
            throw new IllegalArgumentException("the path is not there");
        }

        Pattern p = Pattern.compile(regexTrailing);
        if (!p.matcher(path).matches()) {
            errores.add("the path " + path + " have trailing slashes");
        }

        return errores;
    }

    public List<String> getErrors() {
        return errors;
    }
}
