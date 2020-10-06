package com.mycompany.spheroiddemo;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.Scanner;
import java.util.ArrayList;


/**
 * Name :Muhaddatha Abdulghani
 * Professor: John Baugh
 * Class: CIS 296-001
 * Description: This program takes two radii values for a spheroid and calculates
 * calculaets its volume and classifies it. This information is printed to the screen.
 */
public class SpheroidDemo {
    
    public static void main(String[] args){
        /*step 1: check if input.txt exists
        test cases related to the file:
        1. The file does not exist
        2. The file is empty
       */
        
      
       float value1 = 0;
       //This variable holds the radius value read from a file
       float value2 = 0;
       String lineFromInFile = "";
       boolean debugging = false;
       ArrayList<Spheroid> spheroidCollection = new ArrayList<> ();
       
       try{ 
           
           File inputFile = new File("input.txt");
           
            Scanner inFile = new Scanner(inputFile);
          
           
           if(inFile.hasNext()){ //checking if file is empty
               
               if(debugging){
                   System.out.println("Testing if file is open: " + inFile.hasNext());
               }
               
               while(inFile.hasNext()){
                   
                   
                   lineFromInFile = inFile.nextLine(); //gets one line at a time 
                   //from a file
                   
                   if(debugging){
                       System.out.println("Testing: line from file: " + lineFromInFile);
                   }
                   
                   
                   if(!lineFromInFile.contains("//")){
                       //checking to see if the line from input.txt is a comment
                       
                       
                       String[] fileLineInfo = lineFromInFile.split(" ");
                        //This line splits the words and numbers in the file line
                        
                        //checking if inputs are valid aka are numbers
                        //The first statement inside the if paranthesis 
                        //helps skip lines with only one input
                        if(lineFromInFile.length() > 1 && (fileLineInfo[0].chars().allMatch(Character::isDigit) || 
                                fileLineInfo[0].matches("[-+]?[0-9]*\\.?[0-9]+")) &&
                                (fileLineInfo[1].chars().allMatch(Character::isDigit) || 
                                fileLineInfo[1].matches("[-+]?[0-9]*\\.?[0-9]+"))){
                            //checking if the two inputs are number: integers or floats
                            
                            value1 = Float.parseFloat(fileLineInfo[0]);
                            value2 = Float.parseFloat(fileLineInfo[1]);
                            
                            if(debugging){
                                System.out.println("Found a valid input! value1: " + value1 + " value2: " + value2);
                            }
                            
                            
                            
                            //input into array
                            //constrait: the radii values must be positive and greater than 0
                            if(value1 > 0 && value2 > 0){
                                Spheroid s = new Spheroid(value1, value2);
                                //s = new Spheroid(value1, value2);
                                
                                s.setERadius(value1);
                                s.setPRadius(value2);
                                
                                spheroidCollection.add(s);
                                
                                if(debugging){
                                    
                                    System.out.print("Testing spheroid's print mehtod: ");
                                    String[] spheroidInfo;
                                    
                                    spheroidInfo = s.printInfo();
                                    
                                    for(int i = 0; i < 4; i++){
                                        System.out.print(spheroidInfo[i] + "\t");
                                    }
                                    
                                    System.out.println();
                                }
                                
                               spheroidCollection.add(s); //adding a spheroid to the array list
                            }
                            else{
                                System.out.println("Invalid input: " + lineFromInFile);
                            }
   
                        }
                        else if(!lineFromInFile.isEmpty()){
                            System.out.println("Invalid input: " + lineFromInFile);
                        }
              
                   }
                   else{
                       System.out.println("A comment incoutered in input.txt: " + lineFromInFile);
                    }
                   
         
               }
               
               if(spheroidCollection.size() == 0){
                       System.out.println("No valid inputs for a spheroid found in the file.");
                   }
               else{
                   for(int i = 0; i < spheroidCollection.size(); i++){
                       System.out.print(spheroidCollection.get(i).calculateVolume() + "\t");
                       System.out.println(spheroidCollection.get(i).classifyType());
                       
                   }
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
        
    }//end of main
 
    
    private static class Spheroid{
        //class attributes
        private float equatorialRadius;
        private float polarRadius;
        private double volume;
        private String spheroidType;
        public String[] spheroidInfo;
        
        //class methods
        
        //Class constructor
        Spheroid(float eR, float pR){
            equatorialRadius = eR;
            polarRadius = pR;
            volume = 0;
            spheroidType = "";
            spheroidInfo = new String[4];
            this.classifyType();
            this.calculateVolume();
        }
        
        
        /*
        pre-condition: The instance of the spheroid object exists
        post-condition: The equatorial radius of the spheroid is returned
        */
        public float getERadius(){
            return equatorialRadius;
        }
        
        
        /*
        pre-condition: The instance of the spheroid object exists
        post-condition: The polar radius of the spheroid is returned
        */
        public float getPRadius(){
            return polarRadius;
        }
        
        
        /*
        pre-condition: The instance of the spheroid object exists
        post-condition: The equatorial radius is set to a non-zero and positive
                        user specified value.
                        The shpereInfo array contains the information about the
                        equatorial radius length.
        */
        void setERadius(float equatorialRadius){
            this.equatorialRadius = equatorialRadius;
            spheroidInfo[0] = Float.toString(equatorialRadius);
        }
        
        
        /*
        pre-condition: The instance of the spheroid object exists
        post-condition: The polar radius is set to a non-zero and positive 
                        user specified value.
                        The shpereInfo array contains the information about the
                        polar radius length.
        */
        void setPRadius(float polarRadius){
            this.polarRadius = polarRadius;
            spheroidInfo[1] = Float.toString(polarRadius);
        }
        
        
        /*
        pre-condition: The instance of the spheroid object exists. The radii
                       values are non-zero and positive.
        post-condition: The type of the spheroid is returned. The spheroidInfo
                        contains information about the type of spheroid.
        */
        public String classifyType(){
            if(equatorialRadius > polarRadius){
                spheroidType = "Oblate";
            }
            else if(polarRadius > equatorialRadius){
                spheroidType = "Prolate";
            }
            else{
                spheroidType = "Sphere";
            }
            
            spheroidInfo[3] = spheroidType;
            return spheroidType;
            
        }
        
        
        /*
        pre-condition: The instance of the spheroid object exists. The radii
                       values are non-zero and positive.
        post-condition: The volume of the spheroid is retunred.
        */
        public double calculateVolume(){
           volume = (4/3)*Math.PI*Math.pow(equatorialRadius, 2)*polarRadius;
           spheroidInfo[2] = Double.toString(volume);
           return volume;
        }
        
        
        /*
        pre-condition: The instance of the spheroid object exists. The radii
        are non-zero and positive. The calculateMethod has been called previously.
        post-condition: A String array containing information about the spheroid
                        is returned.
        */
        public String[] printInfo(){
            return spheroidInfo;
        }
        
    }
}
