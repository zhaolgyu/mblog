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

import mblog.base.lang.Consts;
import mblog.modules.blog.data.PostVO;
import mblog.modules.blog.entity.Channel;
import mblog.modules.blog.service.ChannelService;
import mblog.modules.blog.service.PostService;
import mblog.nblog.entity.IP;
import mblog.nblog.service.IPSerivce;
import mblog.nblog.utils.ipUtils;
import mblog.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * Channel 主页
 * @author langhsu
 *
 */
@Controller
public class ChannelController extends BaseController {
	@Autowired
	private ChannelService channelService;
	@Autowired
	private PostService postService;
	@Autowired
	private IPSerivce ipSerivce;
	
	@RequestMapping("/channel/{id}")
	public String channel(@PathVariable Integer id, ModelMap model,
			HttpServletRequest request) {
		// init params
		String order = ServletRequestUtils.getStringParameter(request, "order", Consts.order.NEWEST);
		int pn = ServletRequestUtils.getIntParameter(request, "pn", 1);

		Channel channel = channelService.getById(id);
		// callback params
		model.put("channel", channel);
		model.put("order", order);
		model.put("pn", pn);
		if(id==5){
			return view("/channel/space");
		}
		return view(Views.ROUTE_POST_INDEX);
	}

	@RequestMapping("/view/{id}")
	public String view(@PathVariable Long id, ModelMap model,HttpServletRequest request) {
		PostVO view = postService.get(id);

		Assert.notNull(view, "该文章已被删除");

		postService.identityViews(id);
		model.put("view", view);
		IP ip = saveip(request,view.getTitle());
		ipSerivce.saveIP(ip);
		return view(Views.ROUTE_POST_VIEW);
	}

	public static IP saveip(HttpServletRequest request,String title){
		IP ip = new IP();
		ip.setIPAddress(ipUtils.getIpAddr(request));
		ip.setLoginTime(LocalDateTime.now().toString());
		ip.setPostTitle(title);
		try {
			ip.setIPAddressName(ipUtils.getAddresses("ip="+ipUtils.getIpAddr(request),"utf-8"));
		}
		catch (Exception e){}
		return ip;
	}
}
