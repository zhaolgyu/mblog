package mblog.nblog.mapper;

import mblog.nblog.entity.IP;
import mblog.nblog.entity.Person;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ipMapper {

    //IP selectIP(int id);
    int saveIP(IP ip);
    List<IP> searchIP();
}
