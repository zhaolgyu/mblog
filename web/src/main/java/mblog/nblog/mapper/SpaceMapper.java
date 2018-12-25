package mblog.nblog.mapper;

import mblog.modules.blog.entity.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SpaceMapper {
    Post findLyuPostByAuthorId(int id);
    long saveLyuPost(@Param("post") Post post);
}
