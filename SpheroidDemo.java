package com.mycompany.spheroiddemo;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.Scanner;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


//temporary comment

/**
 *
 * @author 3pear
 */
public class SpheroidDemo {
    
    public static void main(String[] args){
        System.out.println("Hey, test");
        
        
        /*step 1: check if input.txt exists
        test cases related to the file:
        1. The file does not exist
        2. The file is empty
       */
        
       Scanner inFile;
       float value1;
       //This variable holds the radius value read from a file
       float value2;
       String lineFromInFile = "";
       boolean debugging = true;
       
       
       try{
           inFile = new Scanner(new File("input.txt")); //Opens the file
           
           if(inFile.hasNext()){ //checking if file is empty
               
               if(debugging){
                   System.out.println("Testing if file is open: " + inFile.hasNext());
               }
               
               while(inFile.hasNext()){
                   lineFromInFile = inFile.nextLine(); //gets one line at a time 
                   //from a file
                   String[] fileLineInfo = lineFromInFile.split(" ");
                   //This line splits the words and numbers in the file line
                   if(debugging){
                       System.out.println("Testing: line from file: " + lineFromInFile);
                   }
                   int counter = 0;
                   for(String ss : fileLineInfo){ //only the first two values 
                       //in the file are needed
                       if(counter == 0){
                           value1 = Float.parseFloat(ss);
                       }
                       else if (counter == 1){
                           value2 = Float.parseFloat(ss);
                       }
                       else{
                           break;
                       }
                       counter++;
                   }
                   
                   if(debugging){
                       System.out.println("Testing the values found in the file line \n"
                               + "value1: " + value1 + " value2: " + value2);
                   }
                   
                   //Checking to make sure value1 and value2 are float.
                   
                   
               }
           } 
           else {
               //file is empty
               System.out.println("The file is empty.");
           }
           inFile.close();
           
       }catch(FileNotFoundException ex){
           System.out.println("File was not found.");
       }
       
       
      
       
       
       
       
       
       //remember to close the file
        
    }
    
}
