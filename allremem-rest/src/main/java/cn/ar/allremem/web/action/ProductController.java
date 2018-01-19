package cn.ar.allremem.web.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ar.allremem.basics.user.entity.Users;
import cn.ar.allremem.common.utils.ServiceResult;

@Controller
@RequestMapping("/api")
public class ProductController {
	@ResponseBody
	@RequestMapping(value = "product", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String product(@RequestParam(value = "id", required = true) String id) {
		Users user = new Users();
		user.setId(1L);
		user.setUserName("七级滤芯净水器");
		user.setEmail("seven_filters@11.com");
		user.setUserPwd("T12345");
		return new ServiceResult().setCode(1).setMessage("成功").setData(user).toString();
	}
}
