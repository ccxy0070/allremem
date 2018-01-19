package cn.ar.allremem.common.utils;

import java.io.*;
import java.net.*;
import java.util.*;
import net.sf.json.*;
import java.util.regex.*;

/**
 * StringUtils
 * 
 * @author zhangxy
 */

public final class StringUtils {
	public static final String[] EMPTY_STRING_ARRAY = new String[0];
	private static final String CHARSET_NAME = "UTF-8";
	private static final Pattern KVP_PATTERN = Pattern.compile("([_.a-zA-Z0-9][-_.a-zA-Z0-9]*)[=](.*)"); // key
	private static final Pattern INT_PATTERN = Pattern.compile("^\\d+$");
	private static final Pattern DOUBLE_PATTERN = Pattern.compile("^[-\\+]?[.\\d]*$");

	private StringUtils() {
	}
	
	/**
	 * is empty string.
	 * 
	 * @param str
	 *            source string.
	 * @return is empty.
	 */
	public static boolean isEmpty(String string) {
		if (string == null || string.length() == 0 || string.equalsIgnoreCase(""))
			return true;
		return false;
	}

	/**
	 * is not empty string.
	 * 
	 * @param str
	 *            source string.
	 * @return is not empty.
	 */
	public static boolean isNotEmpty(String string) {
		return string != null && string.length() > 0;
	}

	/**
	 * is not empty list
	 * 
	 * @param list
	 * @return
	 */
	public static boolean isNotEmpty(@SuppressWarnings("rawtypes") List list) {
		return null != list && !list.isEmpty();
	}

	/**
	 * is not empty map
	 * 
	 * @param map
	 * @return
	 */
	public static boolean isNotEmpty(@SuppressWarnings("rawtypes") Map map) {
		return null != map && !map.isEmpty();
	}

	/**
	 * is not empty Object
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isNotEmpty(Object obj) {
		return null != obj;
	}

	/**
	 * is not empty arry
	 * 
	 * @param array
	 * @return
	 */
	public static boolean isNotEmpty(String[] array) {
		return null != array && array.length > 0;
	}

	/**
	 * is not empty String...
	 * 
	 * @param properties
	 * @return
	 */
	public static boolean isNotEmptyForVail(String... properties) {
		for (String property : properties) {
			if (StringUtils.isNotEmpty(property)) {// 可变字符串判断
				return false;
			}
		}
		return true;
	}

	/**
	 * 转换为字节数组
	 * 
	 * @param str
	 * @return
	 */
	public static byte[] getBytes(String string) {
		if (string != null) {
			try {
				return string.getBytes(CHARSET_NAME);
			} catch (UnsupportedEncodingException e) {
				return null;
			}
		} else {
			return null;
		}
	}

	/**
	 * 字节数组转换为字符串
	 * 
	 * @param str
	 * @return
	 */
	public static String toString(byte[] bytes) {
		try {
			return new String(bytes, CHARSET_NAME);
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}

	/**
	 * 序列化对象
	 * 
	 * @param object
	 * @return
	 */
	public static byte[] serialize(Object object) {
		ObjectOutputStream oos = null;
		ByteArrayOutputStream baos = null;
		try {
			if (object != null) {
				baos = new ByteArrayOutputStream();
				oos = new ObjectOutputStream(baos);
				oos.writeObject(object);
				return baos.toByteArray();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 反序列化对象
	 * 
	 * @param bytes
	 * @return
	 */
	public static Object unserialize(byte[] bytes) {
		ByteArrayInputStream bais = null;
		try {
			if (bytes != null && bytes.length > 0) {
				bais = new ByteArrayInputStream(bytes);
				ObjectInputStream ois = new ObjectInputStream(bais);
				return ois.readObject();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @param s1
	 * @param s2
	 * @return equals
	 */
	public static boolean isEquals(String s1, String s2) {
		if (s1 == null && s2 == null)
			return true;
		if (s1 == null || s2 == null)
			return false;
		return s1.equals(s2);
	}

	/**
	 * is integer string.
	 * 
	 * @param str
	 * @return is integer
	 */
	public static boolean isInteger(String str) {
		if (str == null || str.length() == 0)
			return false;
		return INT_PATTERN.matcher(str).matches();
	}

	/**
	 * String to Integer
	 * 
	 * @param str
	 * @return
	 */
	public static Integer parseInteger(String str) {
		if (!isInteger(str))
			return 0;
		return Integer.parseInt(str);
	}

	/**
	 * Returns true if s is a legal Java identifier.
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isJavaIdentifier(String s) {
		if (s.length() == 0 || !Character.isJavaIdentifierStart(s.charAt(0))) {
			return false;
		}
		for (int i = 1; i < s.length(); i++) {
			if (!Character.isJavaIdentifierPart(s.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * values[array] is dos't Contains string value
	 * 
	 * @param values
	 * @param value
	 * @return contains
	 */
	public static boolean isContains(String[] values, String value) {
		if (value != null && value.length() > 0 && values != null && values.length > 0) {
			for (String v : values) {
				if (value.equals(v)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * string is not Numeric
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		if (str == null) {
			return false;
		}
		int sz = str.length();
		for (int i = 0; i < sz; i++) {
			if (Character.isDigit(str.charAt(i)) == false) {
				return false;
			}
		}
		return true;
	}

	/**
	 * translat.
	 * 
	 * @param src
	 *            source string.
	 * @param from
	 *            src char table.
	 * @param to
	 *            target char table.
	 * @return String.
	 */
	public static String translat(String src, String from, String to) {
		if (isEmpty(src))
			return src;
		StringBuilder sb = null;
		int ix;
		char c;
		for (int i = 0, len = src.length(); i < len; i++) {
			c = src.charAt(i);
			ix = from.indexOf(c);
			if (ix == -1) {
				if (sb != null)
					sb.append(c);
			} else {
				if (sb == null) {
					sb = new StringBuilder(len);
					sb.append(src, 0, i);
				}
				if (ix < to.length())
					sb.append(to.charAt(ix));
			}
		}
		return sb == null ? src : sb.toString();
	}

	/**
	 * split.
	 * 
	 * @param ch
	 *            char.
	 * @return string array.
	 */
	public static String[] split(String str, char ch) {
		List<String> list = null;
		char c;
		int ix = 0, len = str.length();
		for (int i = 0; i < len; i++) {
			c = str.charAt(i);
			if (c == ch) {
				if (list == null)
					list = new ArrayList<String>();
				list.add(str.substring(ix, i));
				ix = i + 1;
			}
		}
		if (ix > 0)
			list.add(str.substring(ix));
		return list == null ? EMPTY_STRING_ARRAY : (String[]) list.toArray(EMPTY_STRING_ARRAY);
	}

	/**
	 * join string.
	 * 
	 * @param array
	 *            String array.
	 * @return String.
	 */
	public static String join(String[] array) {
		if (array.length == 0)
			return "";
		StringBuilder sb = new StringBuilder();
		for (String s : array)
			sb.append(s);
		return sb.toString();
	}

	/**
	 * join string like javascript.
	 * 
	 * @param array
	 *            String array.
	 * @param split
	 *            split
	 * @return String.
	 */
	public static String join(String[] array, char split) {
		if (array.length == 0)
			return "";
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < array.length; i++) {
			if (i > 0)
				sb.append(split);
			sb.append(array[i]);
		}
		return sb.toString();
	}

	/**
	 * join string like javascript.
	 * 
	 * @param array
	 *            String array.
	 * @param split
	 *            split
	 * @return String.
	 */
	public static String join(String[] array, String split) {
		if (array.length == 0)
			return "";
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < array.length; i++) {
			if (i > 0)
				sb.append(split);
			sb.append(array[i]);
		}
		return sb.toString();
	}

	public static String join(Collection<String> coll, String split) {
		if (coll.isEmpty())
			return "";
		StringBuilder sb = new StringBuilder();
		boolean isFirst = true;
		for (String s : coll) {
			if (isFirst)
				isFirst = false;
			else
				sb.append(split);
			sb.append(s);
		}
		return sb.toString();
	}

	/**
	 * parse key-value pair.
	 * 
	 * @param str
	 *            string.
	 * @param itemSeparator
	 *            item separator.
	 * @return key-value map;
	 */
	private static Map<String, String> parseKeyValuePair(String str, String itemSeparator) {
		String[] tmp = str.split(itemSeparator);
		Map<String, String> map = new HashMap<String, String>(tmp.length);
		for (int i = 0; i < tmp.length; i++) {
			Matcher matcher = KVP_PATTERN.matcher(tmp[i]);
			if (matcher.matches() == false)
				continue;
			map.put(matcher.group(1), matcher.group(2));
		}
		return map;
	}

	/**
	 * parse query string to Parameters.
	 * 
	 * @param qs
	 *            query string.
	 * @return Parameters instance.
	 */
	public static Map<String, String> parseQueryString(String qs) {
		if (qs == null || qs.length() == 0)
			return new HashMap<String, String>();
		return parseKeyValuePair(qs, "\\&");
	}

	public static String getQueryStringValue(String qs, String key) {
		Map<String, String> map = StringUtils.parseQueryString(qs);
		return map.get(key);
	}

	public static String toQueryString(Map<String, String> ps) {
		StringBuilder buf = new StringBuilder();
		if (ps != null && ps.size() > 0) {
			for (Map.Entry<String, String> entry : new TreeMap<String, String>(ps).entrySet()) {
				String key = entry.getKey();
				String value = entry.getValue();
				if (key != null && key.length() > 0 && value != null && value.length() > 0) {
					if (buf.length() > 0) {
						buf.append("&");
					}
					buf.append(key);
					buf.append("=");
					buf.append(value);
				}
			}
		}
		return buf.toString();
	}

	public static String camelToSplitName(String camelName, String split) {
		if (camelName == null || camelName.length() == 0) {
			return camelName;
		}
		StringBuilder buf = null;
		for (int i = 0; i < camelName.length(); i++) {
			char ch = camelName.charAt(i);
			if (ch >= 'A' && ch <= 'Z') {
				if (buf == null) {
					buf = new StringBuilder();
					if (i > 0) {
						buf.append(camelName.substring(0, i));
					}
				}
				if (i > 0) {
					buf.append(split);
				}
				buf.append(Character.toLowerCase(ch));
			} else if (buf != null) {
				buf.append(ch);
			}
		}
		return buf == null ? camelName : buf.toString();
	}

	@SuppressWarnings("unchecked")
	public static Map<String, Object> parseJSON2Map(String jsonStr) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 最外层解析
		JSONObject json = JSONObject.fromObject(jsonStr);
		for (Object k : json.keySet()) {
			Object v = json.get(k);
			// 如果内层还是数组的话，继续解析
			if (v instanceof JSONArray) {
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				Iterator<JSONObject> it = ((JSONArray) v).iterator();
				while (it.hasNext()) {
					JSONObject json2 = it.next();
					list.add(parseJSON2Map(json2.toString()));
				}
				map.put(k.toString(), list);
			} else {
				map.put(k.toString(), v);
			}
		}
		return map;
	}

	private final static String enc = "UTF-8";

	/**
	 * 使用指定的编码机制将字符串转换为 application/x-www-form-urlencoded 格式
	 * 
	 * @author zhangxy
	 * @param s
	 * @return
	 */
	public static String decode(String s) {
		String result = null;
		if (isEmpty(s))
			return result;
		try {
			result = URLDecoder.decode(s, enc);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 使用指定的编码机制对 application/x-www-form-urlencoded 字符串解码。
	 * 
	 * @author zhangxy
	 * @param s
	 * @return
	 */
	public static String encode(String s) {
		String result = null;
		if (isEmpty(s))
			return result;
		try {
			result = URLEncoder.encode(s, enc);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * to String UTF-8
	 * @param str
	 * @return
	 */
	public static String encodeStr(String str) {
		try {
			return new String(str.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static int transVersion(String versionName) {
		int version = 0;
		if (isEmpty(versionName)) {
			return version;
		}
		if (versionName.indexOf(".") != -1) {
			versionName = versionName.replace(".", "");
			version = Integer.valueOf(versionName).intValue();
		}
		return version;
	}

	// 判断浮点数(double和float)
	public static boolean isDouble(String str) {
		if (!isEmpty(str))
			return false;
		return DOUBLE_PATTERN.matcher(str).matches();
	}

	/*
	 * 去掉空格;回车;水平制表符;换行 注：\n 回车(\u000a) \t 水平制表符(\u0009) \s 空格(\u0008) \r 换行(\u000d)
	 */
	public static String replaceBlank(String str) {
		String dest = "";
		if (str != null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}

}