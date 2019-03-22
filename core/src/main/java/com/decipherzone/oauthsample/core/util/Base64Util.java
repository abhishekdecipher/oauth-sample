package com.decipherzone.oauthsample.core.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Base64Util {

  private Base64Util() {
  }

  public static String decryptBase64String(final String encoded) {
    return new String(decryptBase64Bytes(encoded), StandardCharsets.UTF_8);
  }

  public static byte[] decryptBase64Bytes(final String encoded) {
    return Base64.getDecoder().decode(encoded);
  }

  public static byte[] decryptBase64Bytes(final byte[] encoded) {
    return Base64.getDecoder().decode(encoded);
  }

  public static String encryptBase64String(final String plainText) {
    return new String(encryptBase64Bytes(plainText), StandardCharsets.UTF_8);
  }

  public static String encryptBase64String(final byte[] plainText) {
    return new String(encryptBase64Bytes(plainText), StandardCharsets.UTF_8);
  }

  public static byte[] encryptBase64Bytes(final String plainText) {
    return encryptBase64Bytes(plainText.getBytes(StandardCharsets.UTF_8));
  }

  public static byte[] encryptBase64Bytes(final byte[] plainText) {
    return Base64.getEncoder().encode(plainText);
  }


}
