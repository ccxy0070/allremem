package cn.ar.allremem.common.enums;

import cn.ar.allremem.common.constants.MsgConstants;

/**
 * @Title: ResultCode.java
 * @Package: com.cic.constants
 * @Description: 结果码枚举
 * @author:
 * @date: 2015年12月30日 下午8:12:07
 * @version: V1.0
 */
public enum ResultCode {
	
	SUCCESS(1, MsgConstants.SUCCESS), 
	FAIL(0, MsgConstants.FAIL),
	EXCEPTION(-1, MsgConstants.EXCEPTION);
	
	
	private int code;
	private String message;

	private ResultCode(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
