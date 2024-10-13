package util;

import java.io.FileNotFoundException;
import java.util.Scanner;

public interface InputHelperUtil {
    void takeInput() ;
    default int initialPrompt() {
        System.out.println("How do you want to provide input ?");
        System.out.println("Enter 1 to run solution using already present input file");
        System.out.println("Enter 2 to provide manual input ");
        Scanner input = new Scanner(System.in) ;
        int optionSelected = input.nextInt() ;
        while(optionSelected != 1 && optionSelected != 2){
            System.out.println("-- Please enter either 1 or 2 --");
            optionSelected = input.nextInt() ;
        }
        return optionSelected ;
    }
}
