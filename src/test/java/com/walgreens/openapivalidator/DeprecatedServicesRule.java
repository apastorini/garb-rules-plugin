package com.walgreens.openapivalidator;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.Paths;
import org.springdoc.api.OpenApiCustomiser;

import java.util.regex.Pattern;

public class DeprecatedServicesRule implements OpenApiCustomiser {

    @Override
    public void customise(OpenAPI openApi) {
        Paths paths = openApi.getPaths();
        paths.forEach(this::checkIfServiceIsDeprecated);
    }

    private void checkIfServiceIsDeprecated(String path, PathItem item) {
        if (item.getGet() != null) {
            System.out.println("the GET services for: " + path + " is deprecated: " + verifyValue(item.getGet().getDeprecated()));
        }
        if (item.getPost() != null) {
            System.out.println("the POST services for: " + path + " is deprecated: " + verifyValue(item.getPost().getDeprecated()));
        }
        if (item.getPut() != null) {
            System.out.println("the PUT services for: " + path + " is deprecated: " + verifyValue(item.getPut().getDeprecated()));
        }
        if (item.getDelete() != null) {
            System.out.println("the DELETE services for: " + path + " is deprecated: " + verifyValue(item.getPut().getDeprecated()));
        }
    }

    private boolean verifyValue(Boolean value) {
        if (value != null) {
            return value;
        } else {
            return false;
        }
    }
}
