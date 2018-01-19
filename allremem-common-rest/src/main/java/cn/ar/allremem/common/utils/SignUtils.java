package cn.ar.allremem.common.utils;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import cn.ar.allremem.common.constants.Constants;

public class SignUtils {

	/**
	 * 生成签名基础算法
	 * 
	 * @param params
	 * @param secret
	 * @return
	 * @throws IOException
	 */
	public static String getSignature(String params, String secret) throws IOException {
		// 遍历排序后的字典，将所有参数按"key=value"格式拼接在一起
		StringBuilder basestring = new StringBuilder();
		basestring.append(params);
		basestring.append(secret);
		// 使用SHA对待签名串求签
		byte[] bytes = null;
		try {
			// 获取签名串摘要
			MessageDigest sha = MessageDigest.getInstance("SHA");
			bytes = sha.digest(basestring.toString().getBytes("UTF-8"));
		} catch (GeneralSecurityException ex) {
			throw new IOException(ex);
		}
		// 将MD5输出的二进制结果转换为小写的十六进制
		StringBuilder sign = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			String hex = Integer.toHexString(bytes[i] & 0xFF);
			if (hex.length() == 1) {
				sign.append("0");
			}
			sign.append(hex);
		}
		return sign.toString();
	}

	/**
	 * 
	 * @param HashMap<String,String>
	 *            params 请求参数集，所有参数必须已转换为字符串类型
	 * @param String
	 *            secret 签名密钥
	 * @return 签名
	 * @throws IOException
	 */
	public static String getSignature(HashMap<String, String> params, String secret) throws IOException {
		// 先将参数以其参数名的字典序升序进行排序
		Map<String, String> sortedParams = new TreeMap<String, String>(params);
		Set<Entry<String, String>> entrys = sortedParams.entrySet();

		// 遍历排序后的字典，将所有参数按"key=value"格式拼接在一起
		StringBuilder basestring = new StringBuilder();
		for (Entry<String, String> param : entrys) {
			basestring.append(param.getKey()).append("=").append(param.getValue());
		}
		return getSignature(basestring.toString(), secret);
	}

	/**
	 * sign 签名验证 HashMap方式
	 * 
	 * @param arrayParam
	 * @param userID
	 * @param sign
	 * @return
	 * @throws IOException
	 * @since JDK 1.7
	 * @author zhangxy
	 */
	public static boolean VerifySignature(HashMap<String, String> params, String sign) throws IOException {
		String secretKey = Constants.DES_KEY;// 公用私钥key
		String newsign = SignUtils.getSignature(params, secretKey);
		if (!newsign.equals(sign) || !StringUtils.isNotEmpty(secretKey)) {
			return true;
		}
		// 通过验证
		return false;
	}

}
