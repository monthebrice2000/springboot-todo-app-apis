package springboottodoappapis.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboottodoappapis.entities.RoleEntity;
import springboottodoappapis.models.RoleModel;
import springboottodoappapis.repositories.RoleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public RoleModel createRole(RoleModel roleModel) {
        RoleEntity roleEntity = new RoleEntity();
        BeanUtils.copyProperties( roleModel, roleEntity);
        try{
            RoleEntity roleEntity1 = this.roleRepository.save( roleEntity );
            BeanUtils.copyProperties( roleEntity1, roleModel );
            return roleModel;
        }catch (Exception err){
            throw err;
        }
    }

    @Override
    public List<RoleModel> getAllRoles() {

        try{
            List<RoleEntity> roleEntities = this.roleRepository.findAll();
            List<RoleModel> roleModels = new ArrayList<RoleModel>();
            RoleModel roleModel = null;
            for(RoleEntity roleEntity: roleEntities){
                roleModel = new RoleModel();
                BeanUtils.copyProperties( roleEntity, roleModel );
                roleModels.add( roleModel );
            }
            return roleModels;
        }catch (Exception err){
            throw err;
        }
    }

    @Override
    public RoleModel getRolebyId(Long id) {
        try{
            Optional<RoleEntity> roleEntity = this.roleRepository.findById( id );
            System.out.println( roleEntity.get() );
            RoleModel roleModel = new RoleModel();
            BeanUtils.copyProperties( roleEntity.get(), roleModel );
            return roleModel;
        }catch (Exception err){
            throw err;
        }
    }

    @Override
    public void deleteRoleById(Long id) {
        try{
            this.roleRepository.deleteById( id );
        }catch(Exception err){
            throw err;
        }
    }
}
