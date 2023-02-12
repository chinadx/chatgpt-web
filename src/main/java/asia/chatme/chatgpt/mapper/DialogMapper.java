package asia.chatme.chatgpt.mapper;

import asia.chatme.chatgpt.model.Dialog;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface DialogMapper {

    @Insert("INSERT INTO dialog(session_id, ask, answer, tokens, create_time) " +
            "VALUES(#{sessionId}, #{ask}, #{answer}, #{tokens}, #{createTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Dialog model);

    @Select("SELECT * FROM dialog WHERE id = #{id}")
    Dialog select(int id);

    @Select("SELECT * FROM dialog")
    List<Dialog> selectAll();

    @Select("select * from dialog where session_id = #{sessionId} order by create_time desc")
    List<Dialog> selectBySessionId(String sessionId);

    @Delete("DELETE FROM dialog WHERE id=#{id}")
    int delete(Integer id);
}
