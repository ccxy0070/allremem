package cn.ar.allremem.service.dao;

import cn.ar.allremem.basics.user.entity.Users;
import cn.ar.allremem.common.core.dao.BaseDao;

/**
 * 
 * @Title UserDao.java <br/>
 * @Description 用户表数据访问层接口. <br/>
 * @Package: cn.ar.allremem.service.dao <br/>
 * @version ver1.0 <br/>
 * @since JDK 1.8 <br/>
 * @author zhangxy<br/>
 * @date: 2018年1月18日 上午11:19:33 <br/>
 */
public interface UserDao extends BaseDao<Users> {

	/**
	 * 根据用户登录名获取用户信息.
	 * 
	 * @param loginName
	 *            .
	 * @return user .
	 */
	Users findByUserNo(String userNo);

}
