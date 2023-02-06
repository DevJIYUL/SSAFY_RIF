package team.a501.rif.exception;

public class NotEnoughPoints extends RuntimeException{

    public NotEnoughPoints(){
        super("포인트가 부족합니다");
    }

    public NotEnoughPoints(String s){
        super(s);
    }
}
