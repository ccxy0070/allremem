package cn.ar.allremem.service.biz;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ar.allremem.basics.user.entity.Users;
import cn.ar.allremem.common.page.PageBean;
import cn.ar.allremem.common.page.PageParam;
import cn.ar.allremem.service.dao.UserDao;



/**
 * 
 * @Title UserBiz.java <br/>
 * @Description �û���--�����ӿ�  <br/>
 * @Package: cn.ar.allremem.service.biz <br/>
 * @version ver1.0 <br/>
 * @since JDK 1.8 <br/>
 * @author zhangxy<br/>
 * @date: 2018��1��18�� ����11:18:51 <br/>
 */
@Service("userBiz")
public class UserBiz {

	@Autowired
	private UserDao pmsUserDao;
	
	/**
	 * �����û���Ϣ.
	 * @param pmsUser
	 */
	public void create(Users user) {
		pmsUserDao.insert(user);
	}
	
	/**
	 * ����ID��ȡ�û���Ϣ.
	 * @param userId
	 * @return
	 */
	public Users getById(Long userId) {
		return pmsUserDao.getById(userId);
	}

	/**
	 * ���ݵ�¼��ȡ���û�����
	 */
	public Users findUserByUserNo(String userNo) {
		return pmsUserDao.findByUserNo(userNo);
	}

	/**
	 * ����IDɾ��һ���û���ͬʱɾ������û������Ľ�ɫ������Ϣ. type="1"�ĳ�������Ա����ɾ��.
	 * 
	 * @param id
	 *            �û�ID.
	 */
	public void deleteUserById(long userId) {
		Users pmsUser = pmsUserDao.getById(userId);
		if (pmsUser != null) {
			if ("1".equals(pmsUser.getUserType())) {
				throw new RuntimeException("��" + pmsUser.getUserNo() + "��Ϊ��������Ա������ɾ����");
			}
			pmsUserDao.deleteById(pmsUser.getId());
		}
	}

	
	/**
	 * �����û���Ϣ.
	 * @param user
	 */
	public void update(Users user) {
		pmsUserDao.update(user);
	}
	
	/**
	 * �����û�ID�����û�����.
	 * 
	 * @param userId
	 * @param newPwd  (�ѽ���SHA1����)
	 */
	public void updateUserPwd(Long userId, String newPwd, boolean isTrue) {
		Users pmsUser = pmsUserDao.getById(userId);
		pmsUser.setUserPwd(newPwd);
		pmsUser.setPwdErrorCount(0); // ��������������Ϊ0
		pmsUser.setIsChangedPwd(isTrue); // ��������Ϊ���޸Ĺ�
		pmsUserDao.update(pmsUser);
	}



	/**
	 * ��ѯ����ҳ�г��û���Ϣ.
	 * @param pageParam
	 * @param paramMap
	 * @return
	 */
	public PageBean listPage(PageParam pageParam, Map<String, Object> paramMap) {
		return pmsUserDao.listPage(pageParam, paramMap);
	}



}