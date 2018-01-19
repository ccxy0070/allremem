package cn.ar.allremem.common.utils;

import java.io.Serializable;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.*;
import cn.ar.allremem.common.constants.Constants;
import cn.ar.allremem.common.enums.ResultCode;

/**
 * 
 * @Title 返回值类型组装类
 * @ClassName: ServiceResult
 * @Package: com.cic.util
 * @version V1.0
 * @since JDK 1.7
 * @author zhangxy
 * @date: 2016年1月5日 下午7:42:30
 */
public class ServiceResult implements Serializable {
	private static final long serialVersionUID = 1L;
	// successs
	public static ServiceResult SUCCESS = new ServiceResult().setCode(ResultCode.SUCCESS.getCode())
			.setMessage(ResultCode.SUCCESS.getMessage());
	// fail
	public static ServiceResult FAIL = new ServiceResult().setCode(ResultCode.FAIL.getCode())
			.setMessage(ResultCode.FAIL.getMessage());
	// exception
	public static ServiceResult EXCEPTION = new ServiceResult().setCode(ResultCode.EXCEPTION.getCode())
			.setMessage(ResultCode.EXCEPTION.getMessage());

	/** 标识服务返回码 **/
	private int code;

	/** 服务返回消息 */
	private String message;

	/** 服务返回数据 */
	private Object data;

	/** 服务返回secretKey */
	private String secretKey;

	/** 返回接口数据时，需要调用此方法 [调用此方法则显示null字段值转换为""] */
	@Override
	public String toString() {
		return this.toJSONString(this);
	}

	/** 调用此方法则兼容 @Override toString()的方法， 调用原生fastjson,null或""字段不显示 */
	public String toStringNotEmpty() {// 原生fastjson转换
		return JSON.toJSONString(this);
	}

	/**
	 * 设置返回码<br>
	 * 
	 * @param code
	 *            要设置的返回码
	 */
	public ServiceResult setCode(int code) {
		this.code = code;
		return this;
	}

	/**
	 * 设置返回消息<br>
	 * 
	 * @param message
	 *            要设置的返回消息
	 */
	public ServiceResult setMessage(String message) {
		this.message = message;
		return this;
	}

	/**
	 * 设置返回数据
	 * 
	 * @param data
	 *            返回的数据
	 */
	public ServiceResult setData(Object data) {
		this.data = data;
		return this;
	}

	/**
	 * 
	 * 使用秘钥加密
	 * 
	 * @param data
	 * @param userID
	 * @return
	 * @throws Throwable
	 * @since JDK 1.7
	 * @time 上午9:52:49
	 * @author zhangxy
	 */
	public ServiceResult setDataEncrypt(Object data, String is) {

		if (StringUtils.isNotEmpty(is) && is.equals("0")) {// 不为空并且为0则不加密
			this.data = data;
		} else {
			String key = Constants.DES_KEY;// secretKey_userid_key
			String resdata = this.toJSONString(data);
			try {
				this.data = EncryptUtil.AESEncrypt(resdata, key);
			} catch (Exception e) {
				this.data = null;
				this.code = 0;
				this.message = "访问失败，请稍后重试";
			}
		}
		return this;
	}

	/**
	 * 得到服务返回码
	 * 
	 * @return
	 */
	public int getCode() {
		return this.code;
	}

	/**
	 * 得到服务返回消息
	 * 
	 * @return
	 */
	public String getMessage() {
		return this.message;
	}

	/**
	 * 得到服务返回数据
	 * 
	 * @return
	 */
	public Object getData() {
		return data;
	}

	/**
	 * 得到一个实例
	 * 
	 * @return
	 */
	public static ServiceResult instance() {
		return new ServiceResult();
	}

	public ServiceResult setSecretKey(String secretKey) {
		this.secretKey = secretKey;
		return this;
	}

	public String getSecretKey() {
		return secretKey;
	}

	/** 关于fastjson对象为null的时候，处理变为"" zhangxy **/
	private String toJSONString(Object object, SerializerFeature... features) {
		SerializeWriter out = new SerializeWriter();
		String s;
		JSONSerializer serializer = new JSONSerializer(out);
		SerializerFeature arr$[] = features;
		int len$ = arr$.length;
		for (int i$ = 0; i$ < len$; i$++) {
			SerializerFeature feature = arr$[i$];
			serializer.config(feature, true);
		}
		serializer.getValueFilters().add(new ValueFilter() {
			public Object process(Object obj, String s, Object value) {
				if (null != value) {
					if (value instanceof java.util.Date) {
						return String.format("%1$tF %1tT", value);
					}
					return value;
				} else {
					if (s.equals("secretKey")) {// 如果secretKey为 null 则是个例外不显示
						return null;
					}
					return "";
				}
			}
		});
		serializer.write(object);
		s = out.toString();
		out.close();
		return s;
	}
}
