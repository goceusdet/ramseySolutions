package com.ramseySolutions.utils;

import java.util.Base64;

public class Base64Util {

    /**
     * Method takes one string parameter and returns encoded String version of it.
     * @param str
     * @return
     */
    public static String encode(String str){
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] encoded = encoder.encode(str.getBytes());
        return new String(encoded);
    }

    /**
     * Method takes two string parameters and returns encoded String versions of them.
     * @param str
     * @param str2
     * @return
     */
    public static String encode(String str, String str2){
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] encoded = encoder.encode(str.getBytes());
        return new String(encoded);
    }

    /**
     * Method takes two string parameters and one int parameter and returns encoded String version of them.
     * @param str
     * @param str2
     * @param number
     * @return
     */
    public static String encode(String str, String str2, int number){
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] encoded = encoder.encode(str.getBytes());
        return new String(encoded);
    }

    /**
     * Method takes one int parameter and returns encoded String version of it.
     * @param number
     * @return
     */
    public static String encodeNumAsString(int number){
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] encoded = encoder.encode(Integer.toString(number).getBytes());
        return new String(encoded);
    }

    /**Method takes one string parameter and returns decoded String version of it.
     *
     * @param str
     * @return
     */
    public static String decode(String str){
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decoded = decoder.decode(str.getBytes());
        return new String(decoded);
    }

    /**
     * Method takes two string parameters and returns decoded String versions of them.
     * @param str
     * @param str2
     * @return
     */
    public static String decode(String str, String str2){
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decoded = decoder.decode(str.getBytes());
        return new String(decoded);
    }

    /**
     * Method takes two string parameters and one int parameter and returns decoded String versions of them.
     * @param str
     * @param str2
     * @param number
     * @return
     */
    public static String decode(String str, String str2, int number){
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decoded = decoder.decode(str.getBytes());
        return new String(decoded);
    }

    /**
     * Method takes one int parameter and returns decoded String version of it.
     * @param number
     * @return
     */
    public static String decodeNumAsString(int number){
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decoded = decoder.decode(Integer.toString(number).getBytes());
        return new String(decoded);
    }

}
