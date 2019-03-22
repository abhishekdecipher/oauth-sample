package com.decipherzone.oauthsample.core.util.security;

import com.decipherzone.oauthsample.core.exception.InitializationException;
import org.apache.commons.lang3.ArrayUtils;

import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.IvParameterSpec;
import javax.xml.bind.DatatypeConverter;

public class PlainTextPayload extends Payload {

  private final byte[] data;
  private IvParameterSpec ivParameterSpec;

  public PlainTextPayload(final String payload, final String secret) {
    super(secret);
    this.data = payload.getBytes(StandardCharsets.UTF_8);

    SecureRandom randomSecureRandom = null;
    try {
      randomSecureRandom = SecureRandom.getInstance("SHA1PRNG");
    } catch (NoSuchAlgorithmException e) {
      throw new InitializationException(e);
    }
    byte[] iv = new byte[16];
    randomSecureRandom.nextBytes(iv);
    ivParameterSpec = new IvParameterSpec(getIVHex(iv));
  }

  @Override
  public String getCipherText() {
    try {
      cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
    } catch (InvalidKeyException | InvalidAlgorithmParameterException e) {
      throw new InitializationException(e);
    }

    try {
      return encode(
          new String(ArrayUtils.addAll(ivParameterSpec.getIV(), Base64.getEncoder().encode(cipher.doFinal(data))),
              StandardCharsets.UTF_8));
    } catch (BadPaddingException | IllegalBlockSizeException e) {
      throw new InitializationException(e);
    }
  }

  private byte[] getIVHex(final byte[] plain) {
    return Arrays.copyOfRange(DatatypeConverter.printHexBinary(plain).getBytes(), 0, 16);
  }

  @Override
  public String getPlainText() {
    return new String(data, StandardCharsets.UTF_8);
  }
}
