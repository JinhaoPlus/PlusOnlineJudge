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

	//������������Ϣ
		private FilterConfig filterConfig;
		//����Ҫ���˵�·��
		private String[] freePages;
		//����Ҫ���˵������׺
		private String[] wildCard;
		//���˺󲻷���ʱ�Զ���ת��ҳ��
		private String toPage = null;
		
		
		/**
		 * ��ʼ��filter��������д����ķ�����
		 * 
		 * @param filterConfig
		 *            FilterConfig filter ���ö���
		 * @throws ServletException
		 */
		public void init(FilterConfig filterConfig) throws ServletException {
			int i = 0;
			int j = 0;

			this.filterConfig = filterConfig;
			
			// ���´������ļ���ȡ������Ϣ
			//���˲��ɹ���Ҫ��ת��ҳ��
			this.toPage = filterConfig.getInitParameter("toPage");
			String pages = filterConfig.getInitParameter("freePages");
		
			if (toPage == null || pages == null || toPage.trim().length() == 0
					|| pages.trim().length() == 0) {
				throw new ServletException(
						"Haven't init SessionFilter parameter\"toPage\" or \"freePage\" in web.xml.");
			}
			
			//������Ҫ���˵�·��ת����StringTokenizer;
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
		 * �ж�һ������URI�Ƿ��ǲ����˵�ҳ��
		 * 
		 * @param requestURI
		 *            String ����URI
		 * @return boolean ����trueΪ������ҳ��
		 */

		private boolean isFreePage(String requestURI) {
			boolean isFree = false;
			//����Ƿ�Ϊ��Ҫ���ǵ�·��
			for (int i = 0; i < freePages.length; i++) {
				//�ж��Ƿ�Ϊ����ҳ��
				if (requestURI.endsWith(freePages[i])) {
					//����Ҫ����ҳ��
					return true;
				}
			}
			//����Ƿ�Ϊ��Ҫ���˵ĺ�׺��
			for (int j = 0; j < wildCard.length; j++) {
				//�жϵ�ǰ��׺�Ƿ���Ҫ����
				if (requestURI.endsWith(wildCard[j])) {
					//����Ҫ�����ĺ�׺�ļ�
					return true;
				}
			}
			
			return isFree;
		}
		
		
		

		/**
		 * �ж������Ƿ�Ϊ��ЧSession
		 * 
		 * @param request
		 *            ServletRequest �������
		 * @return boolean ����trueΪ��ЧSession
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
		
		
		
	  
		//filter ������
		@Override
		public void doFilter(ServletRequest request,
				ServletResponse response, FilterChain chain)
				throws ServletException, IOException {
	        HttpServletRequest httpRequest = (HttpServletRequest) request;
	        HttpServletResponse httpResponse = (HttpServletResponse) response;
			
	        String currentURL = httpRequest.getRequestURI();
	        //��������·������/��β��ȥ������/������ԭ·������
	        currentURL = currentURL.endsWith("/") ? currentURL.substring(0,
					(currentURL.length() - 1)) : currentURL;
	        
	        
	        if (!isFreePage(currentURL)) { // ����Ǳ���ҳ��
				if (!isValidSession(request)) { // ���Session��Ч
					String toPageURL = null;
					try {
						toPageURL = httpRequest.getContextPath() + toPage;
						httpResponse.encodeRedirectURL(toPageURL);
						httpResponse.sendRedirect(toPageURL); // ת����Ӧ
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				}
			}

			if (!httpResponse.isCommitted()) { // �����Ӧδ�ύ,������������
				try {
					chain.doFilter(request, response);
				} catch (ServletException sx) {
					filterConfig.getServletContext().log(sx.getMessage());
				} catch (IOException iox) {
					filterConfig.getServletContext().log(iox.getMessage());
				}
			}
		}
		
		
		
		// ����ķ���
		public void destroy() {
			
		}
		public FilterConfig getFilterConfig() {
			return this.filterConfig;
		}

		public void setFilterConfig(FilterConfig filterConfig) {
			this.filterConfig = filterConfig;
		}

}
