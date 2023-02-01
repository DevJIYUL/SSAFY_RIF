package team.a501.rif.exception;

public class NoSuchEntityException extends RuntimeException{

    public NoSuchEntityException(){}
    public NoSuchEntityException(String s){
        super(s);
    }
}
