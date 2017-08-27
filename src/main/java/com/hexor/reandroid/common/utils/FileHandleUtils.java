package com.hexor.reandroid.common.utils;

import com.hexor.reandroid.common.utils.exception.UtilsException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by karan on 26/9/14.
 */
public class FileHandleUtils {

    /*
    Creates a zip in memory
     */
    public static byte[] compress(byte[] encHeader, byte[] encIpek, String firmwareFileName, String ipekFileName) {

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ZipOutputStream zipfile = new ZipOutputStream(bos);
        ZipEntry zipentry;
        try {
            if(encHeader != null) {
                zipentry = new ZipEntry(firmwareFileName);
                zipfile.putNextEntry(zipentry);
                zipfile.write(encHeader);
            }
            if(encIpek != null) {
                zipentry = new ZipEntry(ipekFileName);
                zipfile.putNextEntry(zipentry);
                zipfile.write(encIpek);
            }
            zipfile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] result = bos.toByteArray();

       return result;
    }

    /*
   Creates a zip in memory
    */
    public static byte[] compress(byte[] data, String name) throws UtilsException {

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ZipOutputStream zipfile = new ZipOutputStream(bos);
        try {
            zipfile.putNextEntry(new ZipEntry(name));
            zipfile.write(data);
            zipfile.close();

           return bos.toByteArray();
        } catch (Exception e) {
            throw new UtilsException(e.getMessage());
        } finally {
            try {
                zipfile.close();
            } catch (Exception e) {
                //Nothing to do
            }
        }
    }
}
