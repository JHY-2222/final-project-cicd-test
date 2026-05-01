package com.merge.final_project.global.service;

import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.kms.KmsClient;
import software.amazon.awssdk.services.kms.model.DecryptRequest;

import java.util.Base64;

@Service
public class KmsSecretService {

    private final KmsClient kmsClient;

    public KmsSecretService(KmsClient kmsClient) {
        this.kmsClient = kmsClient;
    }

    public String decryptSecret(String encryptedSecret) {
        byte[] cipherBytes = Base64.getDecoder().decode(encryptedSecret);

        return kmsClient.decrypt(
                DecryptRequest.builder()
                        .ciphertextBlob(SdkBytes.fromByteArray(cipherBytes))
                        .build()
        ).plaintext().asUtf8String();
    }
}
