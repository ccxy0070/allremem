package cn.ar.allremem.web.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ar.allremem.common.utils.ServiceResult;
import cn.ar.allremem.vo.Users;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "UsersController", description = "用户操作", tags = { "Users Interface" })
@Controller
@RequestMapping("users")
public class UserController {
	// @Resource
	// private UserService userService;

	@ApiOperation(value = "一个测试API", notes = "第一个测试API",position=0)
	@ResponseBody
	@RequestMapping(value = "/product", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ApiResponses(value = { @ApiResponse(code = 405, message = "invalid  input") })
	public String product(@ApiParam(name = "id", value = "主用户Id") @RequestParam("id") Integer id) {
		
		Users user = new Users();
		user.setUserName("七级滤芯净水器1");
		user.setEmail("seven_filters@11.com");
		user.setUserPwd(12345);
		return new ServiceResult().setCode(1).setMessage("成功").setData(user).toString();
	}

	@ApiOperation(value = "获取用户信息", notes = "第一个测试API",position=1)
	@ResponseBody
	@RequestMapping(value = "getUserInfo", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public Users getUserInfo(@ApiParam(name = "id", value = "用户id", required = true) Long id,
			@ApiParam(name = "username", value = "用户名") String username) {
		Users user = new Users();
		user.setUserName("七级滤芯净水器");
		user.setEmail("seven_filters@11.com");
		user.setUserPwd(123456);
		return user;
	}

	@ApiOperation(value = "更改用户信息", notes = "第二个测试API",position=2)
	@ResponseBody
	@RequestMapping(value = "updateUserInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String updateUserInfo(@RequestBody @ApiParam(name = "用户对象", value = "传入json格式", required = true) Users user) {
		return new ServiceResult().setCode(1).setMessage("成功").setData(0).toString();
	}

	@ApiOperation(value = "添加用户信息", notes = "第三个测试API",position=3)
	@ResponseBody
	@RequestMapping(value = "saveUser", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String saveUser(@RequestBody @ApiParam(name = "user", value = "json fromat", required = true) Users user) {
		return new ServiceResult().setCode(1).setMessage("成功").toStringNotEmpty();
	}
}
