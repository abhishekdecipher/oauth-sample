package com.decipherzone.oauthsample.core.util.security;

import com.decipherzone.oauthsample.core.exception.InitializationException;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

abstract class Payload {
  private static final String AES_CBC_PKCS5_PADDING = "AES/CBC/PKCS5Padding";
  private static final String DEFAULT_ENC = "AES";
  private static final String[] URL_FRIENDLY_SEARCH = new String[] {"+", "/", "="};
  private static final String[] URL_FRIENDLY_REPLACEMENT = new String[] {"-", "_", "*"};
  private final byte[] secret;
  protected Cipher cipher;
  protected SecretKeySpec secretKeySpec;

  protected Payload(String secret) {
    this.secret = secret.getBytes(StandardCharsets.UTF_8);
    try {
      cipher = Cipher.getInstance(AES_CBC_PKCS5_PADDING);
    } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
      throw new InitializationException(e);
    }

    secretKeySpec = new SecretKeySpec(Arrays.copyOfRange(this.secret, 0, 16), DEFAULT_ENC);
  }

  public abstract String getCipherText();

  public abstract String getPlainText();

  protected String encode(String txt) {
    return StringUtils.replaceEach(txt, URL_FRIENDLY_SEARCH, URL_FRIENDLY_REPLACEMENT);
  }

  protected String decode(String txt) {
    return StringUtils.replaceEach(txt, URL_FRIENDLY_REPLACEMENT, URL_FRIENDLY_SEARCH);
  }

}
