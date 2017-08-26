package com.hexor.reandroid.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.security.web.authentication.www.DigestAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.NonceExpiredException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by milanashara on 5/1/16.
 */
public class PlainTextDigestAuthenticationEntryPoint extends DigestAuthenticationEntryPoint{
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        long expiryTime = System.currentTimeMillis() + (long)(getNonceValiditySeconds() * 1000);
        String signatureValue = md5Hex(expiryTime + ":" + this.getKey());
        String nonceValue = expiryTime + ":" + signatureValue;
        String nonceValueBase64 = new String(Base64.encode(nonceValue.getBytes()));
        String authenticateHeader = "Digest realm=\"" + this.getRealmName() + "\", " + "qop=\"auth\", nonce=\"" + nonceValueBase64 + "\"";
        if(authException instanceof NonceExpiredException) {
            authenticateHeader = authenticateHeader + ", stale=\"true\"";
        }

        response.addHeader("WWW-Authenticate", authenticateHeader);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        PrintWriter writer = response.getWriter();
        writer.println(authException.getMessage());
    }

    private String md5Hex(String data) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException var3) {
            throw new IllegalStateException("No MD5 algorithm available!");
        }

        return new String(Hex.encode(digest.digest(data.getBytes())));
    }
}
