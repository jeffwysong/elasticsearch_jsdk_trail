/*
 * Copyright (c) 2014, Stormpath, Inc.
 * All rights reserved.
 */
package com.jeff.es.data;

import java.math.BigInteger;
import java.util.UUID;

/**
 * @author Jeff Wysong
 * @since 10/2/14 10:20 AM
 */
public class Base62TimeUuidCreator {

    public static final BigInteger BASE = BigInteger.valueOf(62);
    public static final String DIGITS_AND_LETTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    public static String createBase62TimeUuid() {
        UUID uuid = UUID.fromString(new com.eaio.uuid.UUID().toString());
        long msb = uuid.getMostSignificantBits();
        long lsb = uuid.getLeastSignificantBits();
        //create the byte array of actual uuid.
        byte[] buffer = new byte[16];
        for (int i = 0; i < 8; i++) {
            buffer[i] = (byte) (msb >>> 8 * (7 - i));
        }
        for (int i = 8; i < 16; i++) {
            buffer[i] = (byte) (lsb >>> 8 * (7 - i));
        }
        //pad the byte array with leading zero to ensure positive numbers due to BigInteger's two's complement representation.
        byte[] positive = new byte[17];
        byte[] zeroBit = new byte[]{0};

        System.arraycopy(zeroBit, 0, positive, 0, 1);
        System.arraycopy(buffer, 0, positive, 1, buffer.length);
        BigInteger number = new BigInteger(positive);
        StringBuilder result = new StringBuilder();
        while (number.compareTo(BigInteger.ZERO) == 1) { // number > 0
            BigInteger[] divmod = number.divideAndRemainder(BASE);
            number = divmod[0];
            int digit = divmod[1].intValue();
            result.insert(0, DIGITS_AND_LETTERS.charAt(digit));
        }
        return (result.length() == 0) ? DIGITS_AND_LETTERS.substring(0, 1) : result.toString();
    }

}
