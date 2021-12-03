package int222.backend.models.exceptions;

public class UnsupportedMediaTypeException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public UnsupportedMediaTypeException(String msg) {
        super(msg);
    }
}
