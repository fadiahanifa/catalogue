package id.co.nds.catalogue.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import id.co.nds.catalogue.entities.UserEntity;
import id.co.nds.catalogue.entities.UserInfoEntity;
import id.co.nds.catalogue.exceptions.ClientException;
import id.co.nds.catalogue.exceptions.NotFoundException;
import id.co.nds.catalogue.models.ResponseModel;
import id.co.nds.catalogue.models.UserModel;
import id.co.nds.catalogue.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/add")
    public ResponseEntity<ResponseModel> postUserController(@Valid @RequestBody UserModel userModel) {
        try {
            // Request
            UserEntity user = userService.add(userModel);

            // Response
            ResponseModel response = new ResponseModel();
            response.setMsg("New user is successfully added");
            response.setData(user);

            return ResponseEntity.ok(response);
            }
        // catch (ClientException e) {
        //    ResponseModel response = new ResponseModel();
        //    response.setMsg(e.getMessage());
        //    return ResponseEntity.badRequest().body(response);
        //}
        catch (Exception e) {
            ResponseModel response = new ResponseModel();
            response.setMsg("Sorry, there is a failure on our server" + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(response);
        }
    }

    @GetMapping(value = "/get")
    public ResponseEntity<ResponseModel> getAllUsersController() {
        try {
            // Request
            List<UserEntity> users = userService.getAllUser();

            // Response
            ResponseModel response = new ResponseModel();
            response.setMsg("Request succesful");
            response.setData(users);

            return ResponseEntity.ok(response);
        }
        catch (Exception e) {
                ResponseModel response = new ResponseModel();
                response.setMsg("Sorry, there is a failure on our server");
                e.printStackTrace();
                return ResponseEntity.internalServerError().body(response);
        }
    }

    @GetMapping(value = "/get/search")
    public ResponseEntity<ResponseModel> searchUsersController(@RequestBody UserModel userModel) {
        try {
            // Request
            List<UserEntity> users = userService.getAllUserByCriteria(userModel);

            // Response
            ResponseModel response = new ResponseModel();
            response.setMsg("Request succesful");
            response.setData(users);

            return ResponseEntity.ok(response);
        }
        catch (Exception e) {
                ResponseModel response = new ResponseModel();
                response.setMsg("Sorry, there is a failure on our server");
                e.printStackTrace();
                return ResponseEntity.internalServerError().body(response);
        }
    }

    @GetMapping(value = "/get/info")
    public ResponseEntity<ResponseModel> findUsersByRoleNameWhereNoActiveController(@RequestParam String roleName) {
        try {
            // Request
            List<UserInfoEntity> users = userService.findUsersByRoleNameWhereNoActive(roleName);

            // Response
            ResponseModel response = new ResponseModel();
            response.setMsg("Request succesful");
            response.setData(users);

            return ResponseEntity.ok(response);
        }
        catch (ClientException e) {
            ResponseModel response = new ResponseModel();
            response.setMsg(e.getMessage());
            e.printStackTrace();
            return ResponseEntity.badRequest().body(response);
        }
        catch (NotFoundException e) {
            ResponseModel response = new ResponseModel();
            response.setMsg(e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        catch (Exception e) {
                ResponseModel response = new ResponseModel();
                response.setMsg("Sorry, there is a failure on our server");
                e.printStackTrace();
                return ResponseEntity.internalServerError().body(response);
        }
    }

    @GetMapping(value = "/get/role")
    public ResponseEntity<ResponseModel> getUsersByRoleController(@RequestParam String roleName) {
        try {
            // Request
            List<UserEntity> users = userService.findUsersByRoleName(roleName);

            // Response
            ResponseModel response = new ResponseModel();
            response.setMsg("Request succesful");
            response.setData(users);

            return ResponseEntity.ok(response);
        }
        catch (ClientException e) {
            ResponseModel response = new ResponseModel();
            response.setMsg(e.getMessage());
            e.printStackTrace();
            return ResponseEntity.badRequest().body(response);
        }
        catch (NotFoundException e) {
            ResponseModel response = new ResponseModel();
            response.setMsg(e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        catch (Exception e) {
                ResponseModel response = new ResponseModel();
                response.setMsg("Sorry, there is a failure on our server");
                e.printStackTrace();
                return ResponseEntity.internalServerError().body(response);
        }
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<ResponseModel> searchUserbyIdController(@PathVariable Integer id) {
        try {
            // Request
            UserEntity user = userService.getUserById(id);

            // Response
            ResponseModel response = new ResponseModel();
            response.setMsg("Request succesful");
            response.setData(user);

            return ResponseEntity.ok(response);
        }
        catch (ClientException e) {
            ResponseModel response = new ResponseModel();
            response.setMsg(e.getMessage());
            e.printStackTrace();
            return ResponseEntity.badRequest().body(response);
        }
        catch (NotFoundException e) {
            ResponseModel response = new ResponseModel();
            response.setMsg(e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        catch (Exception e) {
                ResponseModel response = new ResponseModel();
                response.setMsg("Sorry, there is a failure on our server");
                e.printStackTrace();
                return ResponseEntity.internalServerError().body(response);
        }
    }

    @PutMapping(value = "/update")
    public ResponseEntity<ResponseModel> putUserController(@RequestBody UserModel userModel) {
        try {
            // Request
            UserEntity user = userService.edit(userModel);

            // Response
            ResponseModel response = new ResponseModel();
            response.setMsg("User is succesfully updated");
            response.setData(user);

            return ResponseEntity.ok(response);
        }
        catch (ClientException e) {
            ResponseModel response = new ResponseModel();
            response.setMsg(e.getMessage());
            e.printStackTrace();
            return ResponseEntity.badRequest().body(response);
        }
        catch (NotFoundException e) {
            ResponseModel response = new ResponseModel();
            response.setMsg(e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        catch (Exception e) {
                ResponseModel response = new ResponseModel();
                response.setMsg("Sorry, there is a failure on our server");
                e.printStackTrace();
                return ResponseEntity.internalServerError().body(response);
        }
    }
    
    @DeleteMapping(value = "/delete")
    public ResponseEntity<ResponseModel> deleteUserController(@RequestBody UserModel userModel) {
        try {
            // Request
            UserEntity user = userService.delete(userModel);

            // Response
            ResponseModel response = new ResponseModel();
            response.setMsg("User is succesfully deleted");
            response.setData(user);

            return ResponseEntity.ok(response);
        }
        catch (ClientException e) {
            ResponseModel response = new ResponseModel();
            response.setMsg(e.getMessage());
            e.printStackTrace();
            return ResponseEntity.badRequest().body(response);
        }
        catch (NotFoundException e) {
            ResponseModel response = new ResponseModel();
            response.setMsg(e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        catch (Exception e) {
                ResponseModel response = new ResponseModel();
                response.setMsg("Sorry, there is a failure on our server");
                e.printStackTrace();
                return ResponseEntity.internalServerError().body(response);
        }
    }

}
