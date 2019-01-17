/*
+--------------------------------------------------------------------------
|   Mblog [#RELEASE_VERSION#]
|   ========================================
|   Copyright (c) 2014, 2015 mtons. All Rights Reserved
|   http://www.mtons.com
|
+---------------------------------------------------------------------------
*/
package mblog.web.controller.site;

import javax.servlet.http.HttpServletRequest;

import mblog.nblog.entity.IP;
import mblog.nblog.service.IPSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import mblog.base.lang.Consts;
import mblog.web.controller.BaseController;

import java.net.InetAddress;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import mblog.nblog.utils.ipUtils;

/**
 * @author langhsu
 *
 */
@Controller
public class IndexController extends BaseController{

	@Autowired
	private IPSerivce ipSerivce;

	@RequestMapping(value= {"/", "/index"})
	public String root(ModelMap model, HttpServletRequest request) {
		String order = ServletRequestUtils.getStringParameter(request, "order", Consts.order.NEWEST);
		int pn = ServletRequestUtils.getIntParameter(request, "pn", 1);
		model.put("order", order);
		model.put("pn", pn);
		System.out.println("欢迎页！");
		IP ip = new IP();
		ip.setIPAddress(ipUtils.getIpAddr(request));
		ip.setLoginTime(LocalDateTime.now().toString());
		try {
			ip.setIPAddressName(ipUtils.getAddresses("ip="+ipUtils.getIpAddr(request),"utf-8"));
		}
		catch (Exception e){}

		System.out.println("ip:" + ip.getIPAddress());
		System.out.println("时间:" + ip.getLoginTime());
		int id = ipSerivce.saveIP(ip);
		System.out.println("id:" + id);
		return view(Views.INDEX);
	}


//	public static String getIpAddr(HttpServletRequest request) {
//		String ipAddress = null;
//		try {
//			ipAddress = request.getHeader("x-forwarded-for");
//			if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
//				ipAddress = request.getHeader("Proxy-Client-IP");
//			}
//			if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
//				ipAddress = request.getHeader("WL-Proxy-Client-IP");
//			}
//			if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
//				ipAddress = request.getRemoteAddr();
//				if (ipAddress.equals("127.0.0.1")) {
//					// 根据网卡取本机配置的IP
//					InetAddress inet = null;
//					try {
//						inet = InetAddress.getLocalHost();
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//					ipAddress = inet.getHostAddress();
//				}
//			}
//			// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
//			if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
//				// = 15
//				if (ipAddress.indexOf(",") > 0) {
//					ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
//				}
//			}
//		} catch (Exception e) {
//			ipAddress="";
//		}
//		// ipAddress = this.getRequest().getRemoteAddr();
//
//		return ipAddress;
//	}

}
