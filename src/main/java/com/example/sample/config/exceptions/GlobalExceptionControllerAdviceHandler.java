package com.example.sample.config.exceptions;

import com.example.sample.address.controller.AddressController;
import com.example.sample.todo.controller.TodoController;
import com.example.sample.user.controller.ProfileController;
import com.example.sample.user.controller.UserController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice(basePackages = "com.example.sample", assignableTypes = {UserController.class, AddressController.class, TodoController.class, ProfileController.class})
public class GlobalExceptionControllerAdviceHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> globalExceptionHandler(Exception exception) {
        return new ResponseEntity<>("An internal error happened" + exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Object> nullPointerExceptionHandler(NullPointerException exception) {
        return new ResponseEntity<>("Null value error happened", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> validationExceptionHandler(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<Object> illegalStateExceptionHandler(Exception exception) {
        return new ResponseEntity<>("An error happened" + exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SystemException.class)
    public ResponseEntity<Object> systemExceptionHandler(SystemException exception) {
        Map<String, Object> errors = new HashMap<>();
        errors.put("details", exception.getMessage());
        errors.put("code", exception.getCode());
        return new ResponseEntity<>(errors, exception.getStatus());
    }

}

//package com.example.sample.utilities.exceptions;
//
//import org.springframework.beans.propertyeditors.CustomDateEditor;
//import org.springframework.web.bind.WebDataBinder;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.InitBinder;
//
//import java.sql.Date;
//import java.text.SimpleDateFormat;
//
//@ControllerAdvice
//public class GlobalBindingAdvice {
//
//    @InitBinder
//    public void initBinder(WebDataBinder binder) {
//        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("dd-MM-yyyy"), false));
//    }
//}


//package com.example.sample.utilities.exceptions;
//
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ModelAttribute;
//
//@ControllerAdvice
//public class GlobalModelAttributes {
//
//    @ModelAttribute
//    public String getAppName() {
//        return "App name";
//    }
//}

