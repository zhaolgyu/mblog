package mblog.nblog.controller;

import mblog.modules.blog.data.FeedsVO;
import mblog.modules.blog.entity.Post;
import mblog.modules.blog.service.FeedsService;
import mblog.modules.user.data.AccountProfile;
import mblog.modules.user.data.BadgesCount;
import mblog.modules.user.data.UserVO;
import mblog.modules.user.service.NotifyService;
import mblog.modules.user.service.UserService;
import mblog.nblog.service.PersonService;
import mblog.nblog.service.SpaceService;
import mblog.shiro.authc.AccountSubject;
import mblog.web.controller.BaseController;
import mblog.web.controller.site.Views;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import mblog.web.controller.BaseController;

import javax.servlet.http.HttpSession;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import java.io.File;

@Controller
@ResponseBody
@RequestMapping("/1")
public class SpaceController extends BaseController {

    @Autowired
    PersonService personService;

    @Autowired
    SpaceService spaceService;

    @Autowired
    private FeedsService feedsService;

    @Autowired
    private UserService userService;

    @Autowired
    private NotifyService notifyService;

    @ResponseBody
    @RequestMapping("/space")
    public String selectPerson(){
        return "/channel/5";
    }

    @ResponseBody
    @RequestMapping("/test")
    public String test(@RequestParam String title,@RequestParam String content){
        System.out.println(title);
        System.out.println(content);
        Post lyuPost = spaceService.findLyuPostByAuthorId(2);
        Post lyuPost_insert = new Post();
        lyuPost_insert.setTitle(title);
        long result = spaceService.saveLyuPost(lyuPost_insert);
        System.out.println("插入title的Id为：" + result);
        System.out.println(lyuPost.getTitle() + "  --" + lyuPost.getAuthorId());
        String str = "{\"employees\": [{ \"firstName\":\"Bill\" , \"lastName\":\"Gates\" },{ \"firstName\":\"George\" , \"lastName\":\"Bush\" },{ \"firstName\":\"Thomas\" , \"lastName\":\"Carter\" }]}";
        return str;
    }

//    @ResponseBody
//    @RequestMapping(value="/myspace",method = RequestMethod.GET)
    @GetMapping(value="/user", params = "method=myspace")
    public String space(ModelMap model){
        System.out.println("space  here！！");
        Pageable pageable = wrapPageable();
        AccountSubject subject = getSubject();

        Page<FeedsVO> page = feedsService.findUserFeeds(pageable, subject.getProfile().getId());

        model.put("page", page);
        initUser(model);
        return "a";
    }

    @ResponseBody
    @RequestMapping(value ="/upload",method= RequestMethod.POST)
    public String uploadImages(@RequestParam(value="myFileName")MultipartFile  myFileName, HttpSession session, HttpServletRequest request)
            throws IOException {
        /**
         * 保存预览图片
         */
        String realName = "";
        String realPath = "";
        if (myFileName != null) {
            String fileName = myFileName.getOriginalFilename();
            String fileNameExtension = fileName.substring(fileName.indexOf("."), fileName.length());
            realName = UUID.randomUUID().toString() + fileNameExtension;


            realPath = "E:/temp/images/store/thumbs/";
            File uploadFile = new File(realPath, realName);
            myFileName.transferTo(uploadFile);
        }
        String  str =  realName;
        String data = "{\"data\": [{ \"url\":\""+ realPath + str +" \" }]}";
        System.out.println(data);
        String data1 = "{url:'"+  realPath + str  +"'}";
        return data1;
    }

    private void initUser(ModelMap model) {
        AccountProfile up = getSubject().getProfile();
        UserVO user = userService.get(up.getId());

        model.put("user", user);

        pushBadgesCount();
    }

    private void pushBadgesCount() {
        AccountProfile profile = (AccountProfile) session.getAttribute("profile");
        if (profile != null && profile.getBadgesCount() != null) {
            BadgesCount count = new BadgesCount();
            count.setNotifies(notifyService.unread4Me(profile.getId()));
            profile.setBadgesCount(count);
            session.setAttribute("profile", profile);
        }
    }
}
