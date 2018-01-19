package cn.ar.allremem.common.utils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加密解密工具
 * 
 * @author liu5
 *
 */
public class EncryptUtil {
	/*向量参数 */
	private static final String IV = "cn.com.ar-c.app";

	/**
	 * AES加密
	 * @param text 需要加密的字符串
	 * @param key 秘钥 AES固定格式为128/192/256 bits.即：16/24/32bytes
	 * @param iv 向量参数 AES 为16bytes
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws InvalidAlgorithmParameterException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @author liuchengbiao 2017-05-02
	 * @throws UnsupportedEncodingException 
	 */
	public static String AESEncrypt(String text, String key, String iv) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
		Key keySpec = new SecretKeySpec(key.getBytes(), "AES"); // 两个参数，第一个为私钥字节数组，
		IvParameterSpec ivSpec = new IvParameterSpec(iv.getBytes());
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");// 实例化加密类，参数为加密方式，要写全
		cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
		byte[] b = cipher.doFinal(text.getBytes("UTF-8"));// 加密操作,返回加密后的字节数组，然后需要编码。
		//String rets = (new BASE64Encoder()).encode(b); // Base64、HEX等编解码
		String ret = Base64.encodeBase64String(b);
		return ret;
	}

	/**
	 * AES加密
	 * @param text
	 * @param key
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws InvalidAlgorithmParameterException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @author liuchengbiao 创建于 2017年5月2日
	 * @throws UnsupportedEncodingException 
	 */
	public static String AESEncrypt(String text, String key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
		return AESEncrypt(text, key, IV);
	}
	
	/**
	 * MD5加密
	 * @param content
	 * @return
	 * @throws Exception
	 * @author zhangxy
	 */
	public static String MD5Encrypt(String content) throws Exception {
		if (StringUtils.isEmpty(content))
			return null;
		MessageDigest digestInstance = MessageDigest.getInstance("MD5");
		digestInstance.update(content.getBytes("utf-8"));
		byte[] md = digestInstance.digest();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < md.length; i++) {
			int val = (md[i]) & 0xff;
			if (val < 16)
				sb.append("0");
			sb.append(Integer.toHexString(val));
		}
		return sb.toString().toUpperCase();
	}
	
	/**
	 * AES解密
	 * @param text 需要解密的字符串
	 * @param key 秘钥 加密时的秘钥
	 * @param iv 加密时的向量参数
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws InvalidAlgorithmParameterException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException author 刘成标 创建于 2017年5月2日
	 * @throws IOException
	 */
	public static String AESDecrypt(String text, String key, String iv) throws NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, IOException, NoSuchAlgorithmException {
		//byte[] bytes = (new BASE64Decoder()).decodeBuffer(text);// 用Base64解码
		byte[] bytes = Base64.decodeBase64(text);
		IvParameterSpec ivSpec = new IvParameterSpec(iv.getBytes());
		Key k = new SecretKeySpec(key.getBytes(), "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, k, ivSpec); // 与加密时不同MODE:Cipher.DECRYPT_MODE
		byte[] ret = cipher.doFinal(bytes);
		return new String(ret, "utf-8");
	}
	public static String AESDecrypt(String text, String key) throws NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, IOException, NoSuchAlgorithmException {
		return AESDecrypt(text, key, IV);
	}

	/**
	 * byte数组转化为16进制字符串
	 * 
	 * @param bytes
	 * @return
	 */
	public static String byteToHexString(byte[] bytes) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			String hex = Integer.toHexString(bytes[i] & 0xFF);
			if (hex.length() == 1) {
				sb.append("0");
			}
			sb.append(hex);
		}
		return sb.toString();
	}
	/** 
	 * 生成密钥 
	 * @return 
	 * @throws Exception 
	 */  
	public static String getKey(String strKey) throws Exception{ 
		byte[] arrB = new byte[8];
		byte[] arrBTmp = strKey.getBytes();
		
		// 将原始字节数组转换为8位
		for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
			arrB[i] = arrBTmp[i];
		}
		// 生成密钥
		Key key = new SecretKeySpec(arrB, "AES");
		return byteToHexString(key.getEncoded());
	} 
	public static String getKey() throws Exception{ 
		return getKey(Long.toHexString(System.currentTimeMillis()));
	} 
	
}
