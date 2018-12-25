package mblog.nblog.service;

import mblog.modules.blog.entity.Post;

public interface SpaceService {

    Post findLyuPostByAuthorId(int id);
    long saveLyuPost(Post post);
}
