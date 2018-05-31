package cn.dubidubi.dao.base;

import cn.dubidubi.model.dto.AddRoleDTO;

public interface UserRoleMapper {
	Integer GetRoleIdByUserId(int id);

	void addUserRole(AddRoleDTO addRoleDTO);
}
