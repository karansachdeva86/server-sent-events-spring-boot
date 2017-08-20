package com.hexor.reandroid.common.utils;

/**
 * Created by karan on 16/2/15.
 */
//Util class for handling data conversion
public class DataConversionUtils {

    public static String byteArrayToHex(byte[] data) {
        StringBuilder sb = new StringBuilder(data.length * 2);
        for(byte b: data)
            sb.append(String.format("%02x", b & 0xff));
        return sb.toString();
    }


    public static byte[] convertToByteArray(String data) {
        byte[] b = new byte[data.length() / 2];

        for (int i = 0, bStepper = 0; i < data.length() + 2; i += 2)
            if (i != 0)
                b[bStepper++] = ((byte) Integer.parseInt((data.charAt(i - 2) + "" + data.charAt(i - 1)), 16));

        return b;
    }

}
