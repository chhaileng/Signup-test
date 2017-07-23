package info.chhaileng.app.repository.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import info.chhaileng.app.model.User;

@Repository
public interface UserRepository {
	@Select("SELECT id, first_name, last_name, gender, phone, uid, photo FROM tb_user")
	List<User> findAllUsers();
	
	@Select("SELECT id, first_name, last_name, gender, phone, uid, photo FROM tb_user WHERE uid=#{uid}")
	User findUserByUid(String uid);
}
