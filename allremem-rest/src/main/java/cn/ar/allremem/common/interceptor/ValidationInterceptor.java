package cn.ar.allremem.common.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.core.MethodParameter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.ar.allremem.common.annotation.SignValidation;
import cn.ar.allremem.common.utils.ServiceResult;
import cn.ar.allremem.common.utils.SignUtils;

/**
 * 签名验证 拦截器
 * @author 
 *
 */
public class ValidationInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HandlerMethod method = (HandlerMethod) handler;
		String sign = request.getParameter("sign");// 必须有参数
		// 判断 方法上是否有SignValidation注解
		SignValidation valid = method.getMethodAnnotation(SignValidation.class);
		if (null != valid) {
			MethodParameter[] parameters = method.getMethodParameters();
			HashMap<String, String> paramMap = new HashMap<String, String>();
			for (int i = 0; i < parameters.length; i++) {
				MethodParameter parameter = parameters[i];
				String pName = parameter.getParameterName();
				if ("sign".equals(pName))
					continue; // 忽略 sign参数
				String pVal = request.getParameter(pName);
				paramMap.put(pName, pVal);
			}
			// 签名验证
			if (SignUtils.VerifySignature(paramMap, sign)) {
				responeWrite(response, new ServiceResult().setCode(100).setMessage("签名验证错误").toString());
				return false;
			}
		}
		return true;
	}

	private void responeWrite(HttpServletResponse response, String data) {
		response.setContentType("text/javascript;charset=UTF-8");
		response.setHeader("Cache-Control", "no-store, max-age=0, no-cache, must-revalidate");
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		response.setHeader("Pragma", "no-cache");
		try {
			PrintWriter out = response.getWriter();
			out.write(data);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
