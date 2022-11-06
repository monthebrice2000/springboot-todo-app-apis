package springboottodoappapis.services;

import springboottodoappapis.entities.RoleEntity;
import springboottodoappapis.models.RoleModel;

import java.util.List;

public interface RoleService {

    public RoleModel createRole(RoleModel roleModel);

    public List<RoleModel> getAllRoles();

    public RoleModel getRolebyId(Long id);

    public void deleteRoleById(Long id);


}
