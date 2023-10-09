package agh.ics.oop;

public class World {
    public static void main(String args[]){
        System.out.println("Start");
        run(args);
        System.out.println("Stop");
    }
    public static void run(String args[]){
        System.out.println("Zwierzak idzie do przodu");
        int index = 0;
        for(String arg: args){
            if(index < args.length - 1) System.out.print(arg + ", ");
            else System.out.println(arg);
            index++;
        }
    }
}
