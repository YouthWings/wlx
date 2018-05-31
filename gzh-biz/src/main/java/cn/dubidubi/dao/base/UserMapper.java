package cn.dubidubi.dao.base;

import cn.dubidubi.model.DepartmentInfo;
import cn.dubidubi.model.base.UserDO;
import cn.dubidubi.model.dto.ClientUserDTO;

public interface UserMapper {
	// 得到密码
	String getPasswordByAccount(String account);

	// 得到用户对象
	UserDO getUserDOByAccount(String account);

	void updateUserByAccount(ClientUserDTO clientUserDTO);

	void delOneByAccount(String account);

	void updateDepartmentByDepId(DepartmentInfo departmentInfo);
}
