package com.hercules.cloud.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.REQUEST_URI_KEY;

@Slf4j
@Component
public class ABTestingFilter extends ZuulFilter {

	private static final UrlPathHelper URL_PATH_HELPER = new UrlPathHelper();

	@Autowired
	private RouteLocator routeLocator;

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		final RequestContext ctx = RequestContext.getCurrentContext();
		final HttpServletResponse response = ctx.getResponse();
		final HttpServletRequest request = ctx.getRequest();

		final String requestURI = URL_PATH_HELPER.getPathWithinApplication(
				RequestContext.getCurrentContext().getRequest());
		Route rote = routeLocator.getMatchingRoute(requestURI);
		System.out.println(rote);

		ctx.put(REQUEST_URI_KEY, "/serviceB");

		log.info("nonsense filter");
		return null;
	}

}
