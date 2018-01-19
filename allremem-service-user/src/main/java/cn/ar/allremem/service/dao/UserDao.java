package cn.ar.allremem.service.dao;

import cn.ar.allremem.basics.user.entity.Users;
import cn.ar.allremem.common.core.dao.BaseDao;

/**
 * 
 * @Title UserDao.java <br/>
 * @Description �û������ݷ��ʲ�ӿ�. <br/>
 * @Package: cn.ar.allremem.service.dao <br/>
 * @version ver1.0 <br/>
 * @since JDK 1.8 <br/>
 * @author zhangxy<br/>
 * @date: 2018��1��18�� ����11:19:33 <br/>
 */
public interface UserDao extends BaseDao<Users> {

	/**
	 * �����û���¼����ȡ�û���Ϣ.
	 * 
	 * @param loginName
	 *            .
	 * @return user .
	 */
	Users findByUserNo(String userNo);

}
