package org.earlspilner.mapper;

import org.earlspilner.models.UserRole;
import org.mapstruct.Mapper;

/**
 * @author Alexander Dudkin
 */
@Mapper(componentModel = "spring")
public interface UserRoleMapper {

    default UserRole map(String role) {
        return UserRole.valueOf(role.toUpperCase());
    }

}
