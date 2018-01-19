package cn.ar.allremem.service.dao.impl;

import org.springframework.stereotype.Repository;

import cn.ar.allremem.basics.user.entity.Users;
import cn.ar.allremem.common.core.dao.BaseDaoImpl;
import cn.ar.allremem.service.dao.UserDao;



/**
 * 
 * @Title UserDaoImpl.java <br/>
 * @Description 用户表数据访问层接口实现类 <br/>
 * @Package: cn.ar.allremem.service.dao.impl <br/>
 * @version ver1.0 <br/>
 * @since JDK 1.8 <br/>
 * @author zhangxy<br/>
 * @date: 2018年1月18日 上午11:19:21 <br/>
 */

@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<Users> implements UserDao {

	/**
	 * 根据用户登录名获取用户信息.
	 * 
	 * @param loginName
	 *            .
	 * @return user .
	 */

	public Users findByUserNo(String userNo) {
		return super.getSqlSession().selectOne(getStatement("findByUserNo"), userNo);
	}

}
