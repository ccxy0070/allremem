package cn.ar.allremem.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ar.allremem.basics.user.entity.Users;
import cn.ar.allremem.basics.user.service.UserSerive;
import cn.ar.allremem.common.page.PageBean;
import cn.ar.allremem.common.page.PageParam;
import cn.ar.allremem.service.biz.UserBiz;


/**
 * 
 * @Title UserSeriveImpl.java <br/>
 * @Description �û�Dubbo����ӿ�ʵ��  <br/>
 * @Package: cn.ar.allremem.service.impl <br/>
 * @version ver1.0 <br/>
 * @since JDK 1.8 <br/>
 * @author zhangxy<br/>
 * @date: 2018��1��18�� ����11:19:43 <br/>
 */
@Service("userSerive")
public class UserSeriveImpl implements UserSerive {
	@Autowired
	private UserBiz userBiz;
	
	/**
	 * �����û���Ϣ.
	 * @param pmsUser
	 */
	public void create(Users pmsUser) {
		userBiz.create(pmsUser);
	}
	
	/**
	 * ����ID��ȡ�û���Ϣ.
	 * @param userId
	 * @return
	 */
	public Users getById(Long userId) {
		return userBiz.getById(userId);
	}

	/**
	 * ���ݵ�¼��ȡ���û�����
	 */
	public Users findUserByUserNo(String userNo) {
		return userBiz.findUserByUserNo(userNo);
	}

	/**
	 * ����IDɾ��һ���û���ͬʱɾ������û������Ľ�ɫ������Ϣ. type="1"�ĳ�������Ա����ɾ��.
	 * 
	 * @param id
	 *            �û�ID.
	 */
	public void deleteUserById(long userId) {
		userBiz.deleteUserById(userId);
	}

	
	/**
	 * �����û���Ϣ.
	 * @param user
	 */
	public void update(Users user) {
		userBiz.update(user);
	}
	
	/**
	 * �����û�ID�����û�����.
	 * 
	 * @param userId
	 * @param newPwd
	 *            (�ѽ���SHA1����)
	 */
	public void updateUserPwd(Long userId, String newPwd, boolean isTrue) {
		userBiz.updateUserPwd(userId, newPwd, isTrue);
	}



	/**
	 * ��ѯ����ҳ�г��û���Ϣ.
	 * @param pageParam
	 * @param paramMap
	 * @return
	 */
	public PageBean listPage(PageParam pageParam, Map<String, Object> paramMap) {
		return userBiz.listPage(pageParam, paramMap);
	}
}
