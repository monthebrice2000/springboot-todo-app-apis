package springboottodoappapis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springboottodoappapis.entities.RoleEntity;
import springboottodoappapis.models.Errormessage;
import springboottodoappapis.models.RoleModel;
import springboottodoappapis.models.SuccessMessage;
import springboottodoappapis.services.RoleService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/roles")
    public ResponseEntity<?> createRole(@RequestBody RoleModel roleModel){
        try{
            RoleModel roleModel1 = this.roleService.createRole( roleModel );
            return new ResponseEntity<RoleModel>(roleModel,HttpStatus.OK);
        }catch (Exception err){
            return new ResponseEntity<Errormessage>(new Errormessage(err.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/roles")
    public ResponseEntity<?> getRoles(){
        try{
            List<RoleModel> roleModels = this.roleService.getAllRoles();
            System.out.println( roleModels );
            return new ResponseEntity<List<RoleModel>>( roleModels, HttpStatus.OK );
        }catch (Exception err){
            return new ResponseEntity<Errormessage>(new Errormessage(err.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/roles/{roleId}")
    public ResponseEntity<?> getRoleById(@PathVariable Long roleId){
        try{
            RoleModel roleModel = this.roleService.getRolebyId( roleId );
            return new ResponseEntity<RoleModel>( roleModel, HttpStatus.OK );
        }catch (Exception err){
            return new ResponseEntity<Errormessage>(new Errormessage(err.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/roles/{roleId}")
    public ResponseEntity<?> deleteRoleById(@PathVariable Long roleId){
        try{
            this.roleService.deleteRoleById( roleId );
            return new ResponseEntity<SuccessMessage>( new SuccessMessage("Role was delete successfuly"), HttpStatus.OK );
        }catch (Exception err){
            return new ResponseEntity<Errormessage>(new Errormessage(err.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }
}
