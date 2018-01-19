package cn.ar.allremem.common.interceptor;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class EncodingFilter implements Filter{
	// filter初始化参数
	  private FilterConfig config ;
	  private static Log log = LogFactory.getLog(EncodingFilter.class);

	  public void destroy() {
	  // do nothing
	  }

	  public void doFilter(ServletRequest request, ServletResponse response,
	      FilterChain chain) throws IOException, ServletException {
	    // 获取自定义编码
	    String encode = config.getInitParameter("encode");
	    if (encode == null){
	      encode = "utf-8";
	    }
	    HttpServletRequest req =(HttpServletRequest) request;
	    HttpServletResponse resp = (HttpServletResponse) response;
	    req.setCharacterEncoding("utf-8");
	    resp.setContentType("text/html;charset=utf-8");
	    // 使用装饰器
	    MyReq myReq = new MyReq(req);
	    chain.doFilter( myReq, resp);
	  }

	  public void init(FilterConfig filterConfig) throws ServletException {
	    this.config = filterConfig;
	  }

	  /**
	   * 装饰器模式
	   * @author: xlli
	   * @CreateDate: 2016-3-27 上午12:22:01
	   * @version: 1.0
	   */
	  class MyReq extends HttpServletRequestWrapper{
	    private HttpServletRequest req;

	    public MyReq(HttpServletRequest request) {
	      super(request);
	      this.req = request;
	    }

	    public String getParameter(String name) {
	      // post请求
	      if (req.getMethod().equalsIgnoreCase("post")){
	        return req.getParameter(name);
	      }
	      // get请求
	      String value = req.getParameter(name);
	      if (value == null){
	        return null;
	      }
	      try {
	        value = new String(req.getParameter(name).getBytes("iso8859-1"), "utf-8");
	      } catch (UnsupportedEncodingException e) {
	        log.error(new Date().getTime() + "EncodingFilter编码转化失败",e);
	      };
	      return value;
	    }
	  }
}
