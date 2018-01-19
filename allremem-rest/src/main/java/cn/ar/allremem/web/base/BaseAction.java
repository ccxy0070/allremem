package cn.ar.allremem.web.base;

import cn.ar.allremem.basics.user.entity.Users;
/**
 * 
 * @描述: Web系统权限模块基础支撑Action.
 * @作者: WuShuicheng.
 * @创建: 2014-8-13,下午5:19:28
 * @版本: V1.0
 */
public class BaseAction extends ActionSupport implements UserLoginedAware {

	/**
	 * 取出当前登录用户对象
	 */
	public Users getLoginedUser() {
		//TODO 注销登录 restfull 需要另外实现
		Users user = null;
		return user;
	}

}
