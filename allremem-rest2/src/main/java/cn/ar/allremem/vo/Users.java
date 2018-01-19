package cn.ar.allremem.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "用户对象user")
public class Users {

	@ApiModelProperty(value = "用户名", name = "userName")
	private String userName; // 姓名
	@ApiModelProperty(value = "状态", name = "userPwd", required = true)
	private Integer userPwd; // 连续输错密码次数（连续5次输错就冻结帐号）
	private String mobileNo; // 手机号
	private String email;// 邮箱
	private Integer status; // 状态(100:可用，101:不可用 )

	public Users() {

	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(Integer userPwd) {
		this.userPwd = userPwd;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
