package com.walgreens.openapivalidator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.parser.OpenAPIV3Parser;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springdoc.api.OpenApiCustomiser;

public class SpringDocOpenApiCoreTest {


    @Test
    void testUnnormalizedPathsRule() throws IOException {
        UnnormalizedPathsRule unnormalizedPathsRule = new UnnormalizedPathsRule();

        contractContent().forEach(content-> {
            OpenAPI contract = new OpenAPIV3Parser().readContents(content).getOpenAPI();
            unnormalizedPathsRule.customise(contract);
        });


        Assertions.assertEquals(0, unnormalizedPathsRule.getErrors().size(),unnormalizedPathsRule.getErrors().toString());

    }

    @Test
    void testDeprecatedServicesRule() throws IOException {
        OpenApiCustomiser deprecatedServicesRule = new DeprecatedServicesRule();
        contractContent().forEach(content-> {
            OpenAPI contract = new OpenAPIV3Parser().readContents(content).getOpenAPI();
            deprecatedServicesRule.customise(contract);
        });
    }

    private List<String> contractContent() throws IOException {
        String yamlPath = System.getProperty("yaml-path");
        Path path = Paths.get(yamlPath);


        return Files.list(path).map(url-> {
            try {
                System.out.println(url);
                return new String(Files.readAllBytes(url));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
    }
}
