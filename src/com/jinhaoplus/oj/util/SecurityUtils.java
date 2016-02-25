package com.jinhaoplus.oj.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class SecurityUtils {
	public static String SHA1(String decript) {
        try {
            MessageDigest digest = java.security.MessageDigest
                    .getInstance("SHA-1");
            digest.update(decript.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            // �ֽ�����ת��Ϊ ʮ������ ��
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();
 
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
	
	public static String AESEncrypt(String plainText) {
		try {
			// ˫��Э���ļ�����Կ�ַ���
			byte[] key = new String("testtesttesttest").getBytes("utf-8");
			// ��ʼ��������
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			// ��ʼ����Կ
			SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
			// ������ע������
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			// ִ��AES����
			byte[] cipherText = cipher.doFinal(plainText.getBytes("UTF8"));
			// ִ��Base64���ܴ���
			String encryptedString = new String(Base64.encodeBase64(cipherText));
			return encryptedString;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String AESDecrypt(String encryptedText) {
		try {
			// ˫��Э���ļ�����Կ�ַ���
			byte[] key = new String("testtesttesttest").getBytes("utf-8");
			// ��ʼ��������
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
			// ��ʼ����Կ
			SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
			// ������ע������
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			// ִ��Base64���ܴ���
			byte[] cipherText = Base64.decodeBase64(encryptedText
					.getBytes("utf-8"));
			// ִ��AES����
			String decryptedString = new String(cipher.doFinal(cipherText),
					"UTF-8");
			return decryptedString;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
