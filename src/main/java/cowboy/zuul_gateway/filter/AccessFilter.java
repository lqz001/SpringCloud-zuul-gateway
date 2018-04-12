package cowboy.zuul_gateway.filter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class AccessFilter extends ZuulFilter{

	private static Logger log = LoggerFactory.getLogger(AccessFilter.class);
	@Override
	public Object run() {
		//具体逻辑
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		
		log.info("send {} request to {}",request.getMethod(),request.getRequestURI().toString());
		
		Object accessToken = request.getParameter("accessToken");
		if(accessToken == null){
			log.warn("尚未授权！");
			ctx.setSendZuulResponse(false);
			ctx.setResponseStatusCode(401);
			return null;
		}
		log.info("授权成功！");
		return null;
	}

	@Override
	public boolean shouldFilter() {
		//判断是否执行
		return true;
	}

	@Override
	public int filterOrder() {
		// 过滤器执行顺序
		return 0;
	}

	@Override
	public String filterType() {
// 		过滤器类型，决定请求在哪个生命周期中执行。
//		pre：可以在请求被路由之前调用
//		route：在路由请求时候被调用
//		post：在route和error过滤器之后被调用
//		error：处理请求时发生错误时被调用
		return "pre";
	}

}
