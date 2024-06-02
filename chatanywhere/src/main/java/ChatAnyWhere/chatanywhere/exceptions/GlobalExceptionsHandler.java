package ChatAnyWhere.chatanywhere.exceptions;

import ChatAnyWhere.chatanywhere.models.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionsHandler {

    @ExceptionHandler(IncorrectUsernameException.class)
    public ResponseEntity<ErrorDetails>  handleIncorrectUsernameException(IncorrectUsernameException e, WebRequest request)
    {
        ErrorDetails errorDetails= new ErrorDetails(new Date(), e.getMessage(), String.valueOf(HttpStatus.BAD_REQUEST),request.getDescription(false));
        return  new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(UsernameAlreadyExsits.class)
    public  ResponseEntity<ErrorDetails> handleUsernameAlreadyExistsException(UsernameAlreadyExsits e,WebRequest request)
    {
        ErrorDetails errorDetails=new ErrorDetails(new Date(),e.getMessage(),String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR),request.getDescription(false));
        return  new ResponseEntity<>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(UserNotFoundException.class)
    public  ResponseEntity<ErrorDetails> handleUsernameNotFoundException(UserNotFoundException e,WebRequest request)
    {
        ErrorDetails errorDetails=new ErrorDetails(new Date(),e.getMessage(),String.valueOf(HttpStatus.UNAUTHORIZED),request.getDescription(false));
        return  new ResponseEntity<>(errorDetails,HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(AlreadyFriendException.class)
    public  ResponseEntity<ErrorDetails> handleAlreadyFriendException( AlreadyFriendException e,WebRequest request)
    {
        ErrorDetails errorDetails=new ErrorDetails(new Date(),e.getMessage(),String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR),request.getDescription(false));
        return  new ResponseEntity<>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(NotFriendsException.class)
    public  ResponseEntity<ErrorDetails> handleNotFriendsExceptionException( NotFriendsException e,WebRequest request)
    {
        ErrorDetails errorDetails=new ErrorDetails(new Date(),e.getMessage(),String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR),request.getDescription(false));
        return  new ResponseEntity<>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(AuthenticationException.class)
    public  ResponseEntity<ErrorDetails> handleAuthenticationException( AuthenticationException e,WebRequest request)
    {
        ErrorDetails errorDetails=new ErrorDetails(new Date(),e.getMessage(),String.valueOf(HttpStatus.UNAUTHORIZED),request.getDescription(false));
        return  new ResponseEntity<>(errorDetails,HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(OtpFailureException.class)
    public ResponseEntity<ErrorDetails> handleOtpFailureException(OtpFailureException e,WebRequest request)
    {
        ErrorDetails errorDetails= new ErrorDetails(new Date(),e.getMessage(),String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR),request.getDescription(false));
        return  new ResponseEntity<>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}



