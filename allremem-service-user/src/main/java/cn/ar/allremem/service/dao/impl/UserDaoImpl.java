package cn.ar.allremem.service.dao.impl;

import org.springframework.stereotype.Repository;

import cn.ar.allremem.basics.user.entity.Users;
import cn.ar.allremem.common.core.dao.BaseDaoImpl;
import cn.ar.allremem.service.dao.UserDao;



/**
 * 
 * @Title UserDaoImpl.java <br/>
 * @Description �û������ݷ��ʲ�ӿ�ʵ���� <br/>
 * @Package: cn.ar.allremem.service.dao.impl <br/>
 * @version ver1.0 <br/>
 * @since JDK 1.8 <br/>
 * @author zhangxy<br/>
 * @date: 2018��1��18�� ����11:19:21 <br/>
 */

@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<Users> implements UserDao {

	/**
	 * �����û���¼����ȡ�û���Ϣ.
	 * 
	 * @param loginName
	 *            .
	 * @return user .
	 */

	public Users findByUserNo(String userNo) {
		return super.getSqlSession().selectOne(getStatement("findByUserNo"), userNo);
	}

}
