/**
 * Created by maistrenko on 18.05.2017.
 */
public class Calc {
    public int add(int a, int b){
        return a+b;
    }
    public int del(int a,int b ){
        if(b==0){
            throw new IllegalArgumentException();
        }
        return a/b;
    }
    public int add3(int a, int b, int c){
        return a+b+c;
    }
    public int negative(int a, int b){
        return a-b;
    }
}
