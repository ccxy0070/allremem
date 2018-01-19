package cn.ar.allremem.web.base;

import cn.ar.allremem.basics.user.entity.Users;

public interface UserLoginedAware {

	/**
	 * 取得登录的用户
	 * 
	 * @return
	 */
	public Users getLoginedUser();
}
