package mblog.nblog.mapper;

import mblog.nblog.entity.Person;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PersonMapper {

    Person selectPerson(int id);
}
