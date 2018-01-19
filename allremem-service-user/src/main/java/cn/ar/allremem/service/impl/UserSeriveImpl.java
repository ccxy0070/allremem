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
 * @Description 用户Dubbo服务接口实现  <br/>
 * @Package: cn.ar.allremem.service.impl <br/>
 * @version ver1.0 <br/>
 * @since JDK 1.8 <br/>
 * @author zhangxy<br/>
 * @date: 2018年1月18日 上午11:19:43 <br/>
 */
@Service("userSerive")
public class UserSeriveImpl implements UserSerive {
	@Autowired
	private UserBiz userBiz;
	
	/**
	 * 保存用户信息.
	 * @param pmsUser
	 */
	public void create(Users pmsUser) {
		userBiz.create(pmsUser);
	}
	
	/**
	 * 根据ID获取用户信息.
	 * @param userId
	 * @return
	 */
	public Users getById(Long userId) {
		return userBiz.getById(userId);
	}

	/**
	 * 根据登录名取得用户对象
	 */
	public Users findUserByUserNo(String userNo) {
		return userBiz.findUserByUserNo(userNo);
	}

	/**
	 * 根据ID删除一个用户，同时删除与该用户关联的角色关联信息. type="1"的超级管理员不能删除.
	 * 
	 * @param id
	 *            用户ID.
	 */
	public void deleteUserById(long userId) {
		userBiz.deleteUserById(userId);
	}

	
	/**
	 * 更新用户信息.
	 * @param user
	 */
	public void update(Users user) {
		userBiz.update(user);
	}
	
	/**
	 * 根据用户ID更新用户密码.
	 * 
	 * @param userId
	 * @param newPwd
	 *            (已进行SHA1加密)
	 */
	public void updateUserPwd(Long userId, String newPwd, boolean isTrue) {
		userBiz.updateUserPwd(userId, newPwd, isTrue);
	}



	/**
	 * 查询并分页列出用户信息.
	 * @param pageParam
	 * @param paramMap
	 * @return
	 */
	public PageBean listPage(PageParam pageParam, Map<String, Object> paramMap) {
		return userBiz.listPage(pageParam, paramMap);
	}
}
