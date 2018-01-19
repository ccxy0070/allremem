package cn.ar.allremem.web.base;

import cn.ar.allremem.basics.user.entity.Users;
import cn.ar.allremem.common.web.constants.SessionConstant;
import cn.ar.allremem.common.web.struts.Struts2ActionSupport;

/**
 * 
 * @描述: Web系统权限模块基础支撑Action.
 * @作者: WuShuicheng.
 * @创建: 2014-8-13,下午5:19:28
 * @版本: V1.0
 * 
 */
@SuppressWarnings("serial")
public class BaseAction extends Struts2ActionSupport implements UserLoginedAware {

	/**
	 * 取出当前登录用户对象
	 */
	public Users getLoginedUser() {
		Users user = (Users) this.getSessionMap().get(SessionConstant.USER_SESSION_KEY);
		return user;
	}

}
