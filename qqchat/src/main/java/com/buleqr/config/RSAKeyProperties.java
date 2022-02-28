package com.buleqr.config;

import com.buleqr.utils.security.RSAUtils;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * RSA工具封装类
 */
@Data
@Component
@ConfigurationProperties(prefix = "rsa.key")
public class RSAKeyProperties {

    private String publicKeyFile;
    private String privateKeyFile;

    private PublicKey publicKey;
    private PrivateKey privateKey;
    private String secret;
    @PostConstruct
    public void createRSAKey() throws Exception {
        RSAUtils.generateKey(publicKeyFile, privateKeyFile, secret, 0);
        this.publicKey = RSAUtils.getPublicKey(publicKeyFile);
        this.privateKey = RSAUtils.getPrivateKey(privateKeyFile);
    }
}
