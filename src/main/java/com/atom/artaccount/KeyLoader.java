package com.atom.artaccount;

import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class KeyLoader {

    @Value("classpath:private.pem")
    private Resource privateKeyResource;

    public String loadPrivateKey() throws Exception {
        return new String(
                privateKeyResource.getInputStream().readAllBytes(),
                StandardCharsets.UTF_8
        );
    }
}