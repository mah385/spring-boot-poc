package com.neosoft.poc.restcontroller;

import com.neosoft.poc.domain.User;
import com.neosoft.poc.requestbody.CreateUserDTO;
import com.neosoft.poc.requestbody.UpdateUserDTO;
import com.neosoft.poc.service.UserService;
import com.neosoft.poc.utils.BindingResultValidationErrorUtil;
import com.neosoft.poc.utils.ResMsgConstants;
import com.neosoft.poc.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@RestController
@RequestMapping(value = "/api/users")
public class UserRestController {
    private static final Logger LOG = Logger.getLogger(UserRestController.class.getName());  //java.util.logging.Logger

    private final UserService userService;
    private final BindingResultValidationErrorUtil bindingResultValidationErrorUtil;

    @Autowired
    public UserRestController(UserService userService, BindingResultValidationErrorUtil bindingResultValidationErrorUtil) {
        this.userService = userService;
        this.bindingResultValidationErrorUtil = bindingResultValidationErrorUtil;
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ResponseMessage> createUser(@Valid @RequestBody final CreateUserDTO createUserDTO,
                                                      final BindingResult bindingResult) {
        final Map<String, String> mapBindingResultErrorProcessed = bindingResultValidationErrorUtil.getBindingResultValidationError(bindingResult);
        if (mapBindingResultErrorProcessed != null) {
            return new ResponseEntity<>(new ResponseMessage(mapBindingResultErrorProcessed, HttpStatus.BAD_REQUEST, ResMsgConstants.REQUEST_BODY_VALIDATION_FAILED), HttpStatus.BAD_REQUEST);
        }
        User createdUser = userService.createUser(createUserDTO);
        return new ResponseEntity<>(new ResponseMessage(createdUser, HttpStatus.CREATED, ResMsgConstants.SUCCESSFUL_USER_CREATION), HttpStatus.CREATED);
    }

    @GetMapping(value = "/dummyUserDTO", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ResponseMessage> dummyUserDTO() {
        CreateUserDTO createUserDTO = new CreateUserDTO();
        LOG.info("createUserDTO: " + createUserDTO);
        return new ResponseEntity<>(new ResponseMessage(createUserDTO, HttpStatus.OK, "Dummy User DTO"), HttpStatus.OK);
    }

    @PutMapping(value = "/{userId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ResponseMessage> updateUserById(@PathVariable(value = "userId") String userId,
                                                          @Valid @RequestBody final UpdateUserDTO updateUserDTO,
                                                          final BindingResult bindingResult) {
        final Map<String, String> mapBindingResultErrorProcessed = bindingResultValidationErrorUtil.getBindingResultValidationError(bindingResult);
        if (mapBindingResultErrorProcessed != null) {
            return new ResponseEntity<>(new ResponseMessage(mapBindingResultErrorProcessed, HttpStatus.BAD_REQUEST, ResMsgConstants.REQUEST_BODY_VALIDATION_FAILED), HttpStatus.BAD_REQUEST);
        }

        boolean isUserUpdated = userService.updateUserById(userId, updateUserDTO);
        return new ResponseEntity<>(new ResponseMessage(null, HttpStatus.OK, isUserUpdated ? ResMsgConstants.SUCCESSFUL_USER_UPDATE : ResMsgConstants.UNSUCCESSFUL_USER_UPDATE), HttpStatus.OK);
    }

    @DeleteMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ResponseMessage> deleteUserByIdAndDeleteType(@RequestParam(value = "delete-type", defaultValue = "soft") String deleteType,
                                                                       @RequestParam(value = "user-id") String userId) {
        boolean isUserDeleted = userService.deleteUserById(deleteType, userId);
        return new ResponseEntity<>(new ResponseMessage(null, HttpStatus.OK, isUserDeleted ? ResMsgConstants.SUCCESSFUL_USER_DELETION : ResMsgConstants.UNSUCCESSFUL_USER_DELETION), HttpStatus.OK);
    }

    @GetMapping(value = "/orderByDobDescDojDesc", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ResponseMessage> getAllUsersWithOrderByDobDescDojDesc() {
        List<User> allUsersWithOrderByDobDescDojDesc = userService.getAllUsersWithOrderByDobDescDojDesc();
        return new ResponseEntity<>(new ResponseMessage(allUsersWithOrderByDobDescDojDesc.isEmpty() ? ResMsgConstants.NO_USERS_FOUND : allUsersWithOrderByDobDescDojDesc, HttpStatus.OK, ResMsgConstants.SORTED_USER), HttpStatus.OK);
    }

    @GetMapping(value = "/searchByFirstNameOrLastNameOrPinCode", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ResponseMessage> getAllByFirstNameOrLastNameOrPinCode(@RequestParam(value = "searchValue") String searchValue) {
        List<User> allUsersByFirstNameOrLastNameOrPinCode = userService.getAllByFirstNameOrLastNameOrPinCode(searchValue, searchValue, searchValue);
        return new ResponseEntity<>(new ResponseMessage(allUsersByFirstNameOrLastNameOrPinCode.isEmpty() ? ResMsgConstants.NO_USERS_FOUND : allUsersByFirstNameOrLastNameOrPinCode, HttpStatus.OK, ResMsgConstants.SEARCHED_USER), HttpStatus.OK);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ResponseMessage> getAllUsers() {
        List<User> allUsers = userService.getAllUsers();
        return new ResponseEntity<>(new ResponseMessage(allUsers.isEmpty() ? ResMsgConstants.NO_USERS_FOUND : allUsers, HttpStatus.OK, ResMsgConstants.ALL_USERS), HttpStatus.OK);
    }

}
