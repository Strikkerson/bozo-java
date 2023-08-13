package bozoemjava;

public class teste{
    static int var;
    public static void mudarVar(){
        var = 5;
    }
    public static void main(String[] args){

        var = 2;
        System.out.println(var);

        mudarVar();

        System.out.println(var);
    }
} 
