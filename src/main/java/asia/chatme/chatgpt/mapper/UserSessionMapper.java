package asia.chatme.chatgpt.mapper;

import asia.chatme.chatgpt.model.UserSession;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserSessionMapper {
    @Insert("INSERT INTO user_session(session_id, user_agent, ip, summary, rounds, tokens, create_time) " +
            "VALUES(#{sessionId}, #{userAgent}, #{ip}, #{summary}, #{rounds}, #{tokens}, #{createTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(UserSession model);

    @Select("SELECT * FROM user_session WHERE id = #{id}")
    UserSession select(int id);

    @Select("select * from user_session where user_agent = #{userAgent} and ip = #{ip}")
    UserSession selectUser(String userAgent, String ip);

    @Select("select * from user_session where session_id = #{sessionId}")
    UserSession selectSession(String sessionId);

    @Select("SELECT * FROM user_session")
    List<UserSession> selectAll();

    @Delete("DELETE FROM user_session WHERE id=#{id}")
    int delete(Integer id);
}
