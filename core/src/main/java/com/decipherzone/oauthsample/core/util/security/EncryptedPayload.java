package com.decipherzone.oauthsample.core.util.security;

import com.decipherzone.oauthsample.core.exception.InitializationException;
import com.decipherzone.oauthsample.core.util.Base64Util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.IvParameterSpec;

public class EncryptedPayload extends Payload {

  private final byte[] data;
  private final byte[] iv;
  private IvParameterSpec ivParameterSpec;

  public EncryptedPayload(String payload, String secret) {
    super(secret);
    payload = decode(payload);
    this.iv = payload.substring(0, 16).getBytes(StandardCharsets.UTF_8);
    this.data = Base64Util.decryptBase64Bytes(payload.substring(16));
    this.init();
  }

  private void init() {
    ivParameterSpec = new IvParameterSpec(iv, 0, cipher.getBlockSize());
  }

  @Override
  public String getPlainText() {
    try {
      cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
    } catch (InvalidKeyException | InvalidAlgorithmParameterException e) {
      throw new InitializationException(e);
    }

    try {
      return new String(cipher.doFinal(data)).trim();
    } catch (IllegalBlockSizeException | BadPaddingException e) {
      throw new InitializationException(e);
    }

  }

  @Override
  public String getCipherText() {
    final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    try {
      outputStream.write(iv);
      outputStream.write(Base64Util.encryptBase64Bytes(data));
      return new String(outputStream.toByteArray(), StandardCharsets.UTF_8);
    } catch (IOException e) {
      throw new InitializationException(e);
    }
  }

}
