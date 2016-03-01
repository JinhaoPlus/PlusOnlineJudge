package com.jinhaoplus.oj.common;

import java.io.IOException;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class LoginFilter extends HttpServlet implements Filter{

	//顾虑器配置信息
		private FilterConfig filterConfig;
		//不需要过滤的路径
		private String[] freePages;
		//不需要过滤的请求后缀
		private String[] wildCard;
		//过滤后不符合时自动跳转的页面
		private String toPage = null;
		
		
		/**
		 * 初始化filter（这里重写父类的方法）
		 * 
		 * @param filterConfig
		 *            FilterConfig filter 配置对象
		 * @throws ServletException
		 */
		public void init(FilterConfig filterConfig) throws ServletException {
			int i = 0;
			int j = 0;

			this.filterConfig = filterConfig;
			
			// 以下从配置文件获取配置信息
			//过滤不成功需要跳转的页面
			this.toPage = filterConfig.getInitParameter("toPage");
			String pages = filterConfig.getInitParameter("freePages");
		
			if (toPage == null || pages == null || toPage.trim().length() == 0
					|| pages.trim().length() == 0) {
				throw new ServletException(
						"Haven't init SessionFilter parameter\"toPage\" or \"freePage\" in web.xml.");
			}
			
			//将不需要过滤的路径转换成StringTokenizer;
			StringTokenizer strTokenizer = new StringTokenizer(pages, ";");
			this.freePages = new String[strTokenizer.countTokens()];
			while (strTokenizer.hasMoreTokens()) {
				freePages[i++] = strTokenizer.nextToken();
			}
			
			
			String wildCardStr = filterConfig.getInitParameter("wildCard");
			if (wildCardStr == null || wildCardStr == null || wildCardStr.trim().length() == 0
					|| wildCardStr.trim().length() == 0) {
				throw new ServletException(
						"Haven't init SessionFilter parameter\"toPage\" or \"freePage\" in web.xml.");
			}
			
			strTokenizer = new StringTokenizer(wildCardStr, ";");
			this.wildCard = new String[strTokenizer.countTokens()];
			while (strTokenizer.hasMoreTokens()) {
				wildCard[j++] = strTokenizer.nextToken();
			}
			
			if (!isFreePage(toPage)) {
				throw new ServletException(
						"SessionFilter init parameter in web.xml \"toPage\" must in\"freePage\".");
			}
		}
		
		
		/**
		 * 判断一个请求URI是否是不过滤的页面
		 * 
		 * @param requestURI
		 *            String 请求URI
		 * @return boolean 返回true为不过滤页面
		 */

		private boolean isFreePage(String requestURI) {
			boolean isFree = false;
			//检查是否为需要顾虑的路径
			for (int i = 0; i < freePages.length; i++) {
				//判断是否为保护页面
				if (requestURI.endsWith(freePages[i])) {
					//不需要过滤页面
					return true;
				}
			}
			//检查是否为需要过滤的后缀名
			for (int j = 0; j < wildCard.length; j++) {
				//判断当前后缀是否需要保护
				if (requestURI.endsWith(wildCard[j])) {
					//不需要保护的后缀文件
					return true;
				}
			}
			
			return isFree;
		}
		
		
		

		/**
		 * 判断请求是否为有效Session
		 * 
		 * @param request
		 *            ServletRequest 请求对象
		 * @return boolean 返回true为有效Session
		 */
		private boolean isValidSession(ServletRequest request) {
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			
			HttpSession userSession = httpRequest.getSession();
		    Object o = userSession.getAttribute("loginuser");
			
		    if(o != null){
				return true;
			}
			return false;
		}
		
		
		
	  
		//filter 方法体
		@Override
		public void doFilter(ServletRequest request,
				ServletResponse response, FilterChain chain)
				throws ServletException, IOException {
	        HttpServletRequest httpRequest = (HttpServletRequest) request;
	        HttpServletResponse httpResponse = (HttpServletResponse) response;
			
	        String currentURL = httpRequest.getRequestURI();
	        //如果请求的路径是以/结尾就去掉最后的/，否则按原路径计算
	        currentURL = currentURL.endsWith("/") ? currentURL.substring(0,
					(currentURL.length() - 1)) : currentURL;
	        
	        
	        if (!isFreePage(currentURL)) { // 如果是保护页面
				if (!isValidSession(request)) { // 如果Session无效
					String toPageURL = null;
					try {
						toPageURL = httpRequest.getContextPath() + toPage;
						httpResponse.encodeRedirectURL(toPageURL);
						httpResponse.sendRedirect(toPageURL); // 转发响应
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				}
			}

			if (!httpResponse.isCommitted()) { // 如果响应未提交,交给过滤器链
				try {
					chain.doFilter(request, response);
				} catch (ServletException sx) {
					filterConfig.getServletContext().log(sx.getMessage());
				} catch (IOException iox) {
					filterConfig.getServletContext().log(iox.getMessage());
				}
			}
		}
		
		
		
		// 父类的方法
		public void destroy() {
			
		}
		public FilterConfig getFilterConfig() {
			return this.filterConfig;
		}

		public void setFilterConfig(FilterConfig filterConfig) {
			this.filterConfig = filterConfig;
		}

}
