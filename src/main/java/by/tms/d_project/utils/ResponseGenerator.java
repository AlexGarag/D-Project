package by.tms.d_project.utils;

import by.tms.d_project.model.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import static by.tms.d_project.constant_reference_etc.HttpCode.*;
import static by.tms.d_project.constant_reference_etc.Message.*;

@Component
public class ResponseGenerator {

    public ResponseEntity<?> replay(int code) {
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
            default:
                response.setCode(BAD_REQUEST_CODE);
                response.setMessage(BAD_REQUEST_MESSAGE);
                return ResponseEntity.status(BAD_REQUEST_CODE).body(response);
        }
    }
}