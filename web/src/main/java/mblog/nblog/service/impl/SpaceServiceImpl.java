package mblog.nblog.service.impl;

import com.alibaba.fastjson.JSON;
import mblog.modules.blog.entity.Post;
import mblog.nblog.mapper.SpaceMapper;
import mblog.nblog.service.SpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpaceServiceImpl implements SpaceService {

    @Autowired
    SpaceMapper spaceMapper;

    @Override
    public Post findLyuPostByAuthorId(int id) {
        Post lyuPost = new Post();
        lyuPost = spaceMapper.findLyuPostByAuthorId(2);
        System.out.println("Post对象：" + lyuPost.getTitle());
        return lyuPost;
    }

    @Override
    public long saveLyuPost(Post post) {
        System.out.println(JSON.toJSONString(post));
        long result = spaceMapper.saveLyuPost(post);
        long id = post.getId();
        return id;
    }
}
