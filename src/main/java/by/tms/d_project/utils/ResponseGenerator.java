package by.tms.d_project.utils;

import by.tms.d_project.model.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

import static by.tms.d_project.constant_reference_etc.HttpCode.*;
import static by.tms.d_project.constant_reference_etc.Message.*;

@Component
public class ResponseGenerator {

//    private final GenericResponseService responseBuilder;
//
//    public ResponseGenerator(GenericResponseService responseBuilder) {
//        this.responseBuilder = responseBuilder;
//    }

    public ResponseEntity<?> errorReplay(int code) {
        Response response = new Response();
        switch (code) {
            case FORBIDDEN_CODE:
                response.setCode(FORBIDDEN_CODE);
                response.setMessage(NO_RIGHTS_MESSAGE);
                return ResponseEntity.status(FORBIDDEN_CODE).body(response);
            case NOT_FOUND_CODE:
                response.setCode(NOT_FOUND_CODE);
                response.setMessage(NOT_FOUND_MESSAGE);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            case INTERNAL_SERVER_ERROR_CODE:
                response.setCode(INTERNAL_SERVER_ERROR_CODE);
                response.setMessage(INTERNAL_SERVER_ERROR_MESSAGE);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            default:
                response.setCode(BAD_REQUEST_CODE);
                response.setMessage(BAD_REQUEST_MESSAGE);
                return ResponseEntity.status(BAD_REQUEST_CODE).body(response);
        }
    }

    public ResponseEntity<?> errorReplay(BindingResult bindingResult) {
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        List<String> errorsMessages = new ArrayList<>();
        for (FieldError fieldError : fieldErrors) {
            switch (fieldError.getField()) {
                case "username" -> errorsMessages.add(BAD_USERNAME_MESSAGE);
                case "password" -> errorsMessages.add(BAD_PASSWORD_MESSAGE);
                case "shaftSize" -> errorsMessages.add(BAD_SHAFT_SIZE_MESSAGE);
                default -> errorsMessages.add(BAD_REQUEST_MESSAGE);
            }
        }
        Response response = new Response();
        response.setCode(BAD_REQUEST_CODE);
        response.setMessage(errorsMessages.toString());
        return ResponseEntity.status(BAD_REQUEST_CODE).body(response);
    }
}