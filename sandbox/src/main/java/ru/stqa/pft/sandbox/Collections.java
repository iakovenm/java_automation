package ru.stqa.pft.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collections {
    public static void main (String[] args){
        String[] langs ={"Java","C#", "Python","PHP"};
        //List<String> languages = new ArrayList<String>();

        List<String> languages = Arrays.asList("Java","C#", "Python","PHP");
        //languages.add("Java");
       // languages.add("C#");
        //languages.add("Python");

        //alternative of cycle in array and arraylist created specially for collections
        //for (String l: languages) {
            //System.out.println("Want to learn " + l);

       //for cycle on array
        //for (int i = 0; i < langs.length; i++){
            //System.out.println("Want to learn " + langs[i]);
        //for cycle in arraylist
        for (int i = 0; i < languages.size(); i++){
                System.out.println("Want to learn " + languages.get(i));

                //when no type of data is defined
                // List languages = Arrays.asList("Java","C#", "Python","PHP");
            //for (Object l: languages) {
                //System.out.println("Want to learn " + l);

        }
    }
}
