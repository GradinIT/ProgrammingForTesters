package se.jensen.eazy;

public class Add {
    public static Integer staticIntegerInAdd = 4;
    public Integer execute( Integer number, Integer number2){
        return number+number2;
    }
    public static Integer execute2( Integer number, Integer number2){
        return number+number2;
    }
    public Integer getStaticIntegerInAdd() {
        return staticIntegerInAdd;
    }
}
