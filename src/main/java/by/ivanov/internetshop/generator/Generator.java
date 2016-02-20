package by.ivanov.internetshop.generator;


import java.util.Random;

public class Generator {

    public static int generatorId(){

        return new Random().nextInt()/100+2;
    }
}
