package springboottodoappapis.services;

import com.fasterxml.jackson.databind.util.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import springboottodoappapis.entities.RoleEntity;
import springboottodoappapis.entities.UserEntity;
import springboottodoappapis.models.RoleModel;
import springboottodoappapis.models.UserModel;
import springboottodoappapis.repositories.RoleRepository;
import springboottodoappapis.repositories.UserRepository;

import java.util.*;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UserDetails register(UserModel userModel){
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties( userModel, userEntity );

        userModel.getRoles().forEach( roleModel -> {
            Optional<RoleEntity> roleEntity = this.roleRepository.findById( roleModel.getId() );
            userEntity.addRole( roleEntity.get() );
        });
        userEntity.setPassword( this.passwordEncoder.encode( userModel.getPassword() ) );
        UserEntity userEntity1 = this.userRepository.save( userEntity );
        BeanUtils.copyProperties( userEntity1, userModel);
        Set<RoleModel> roleModels = new HashSet<>();
        userEntity1.getRoles().forEach( roleEntity -> {
            RoleModel roleModel = new RoleModel();
            roleModel.setId( roleEntity.getId() );
            roleModel.setRoleName( roleEntity.getRoleName() );
            roleModels.add( roleModel );
        });
        userModel.setRoles( roleModels );
        return userModel;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try{
            UserEntity userEntity = this.userRepository.findByUsername( username );
            /*UserEntity userEntity = new UserEntity();
            System.out.println( this.userRepository.findByUsername( username ) );
            for (UserEntity userEntity1 : this.userRepository.findAll()) {
                if( userEntity1.getUsername().equals( username ) ){
                    userEntity = userEntity1;
                    System.out.println( userEntity );
                    break;
                }
            }*/

            UserModel userModel = new UserModel();
            BeanUtils.copyProperties( userEntity, userModel);
            Set<RoleModel> roleModels = new HashSet<>();
            userEntity.getRoles().forEach( roleEntity -> {
                RoleModel roleModel = new RoleModel();
                roleModel.setId( roleEntity.getId() );
                roleModel.setRoleName( roleEntity.getRoleName() );
                roleModels.add( roleModel );
            });
            userModel.setRoles( roleModels );
            return userModel;
        }catch(Exception err){
            throw new UsernameNotFoundException("User does not exist");
        }
        /*if(username.equals("John") ){
            return new User("John", "secret", new ArrayList<>() );
        }else{
            throw new UsernameNotFoundException("User does not exist");
        }*/
    }
}
