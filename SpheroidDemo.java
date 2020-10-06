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
        /*step 1: check if input.txt exists
        test cases related to the file:
        1. The file does not exist
        2. The file is empty
       */
        
      
       float value1 = 0;
       //This variable holds the radius value read from a file
       float value2 = 0;
       String lineFromInFile = "";
       boolean debugging = true;
       
       
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
        
    }//end of main
 
    
    class Spheroid{
        //class attributes
        private float equatorialRadius;
        private float polarRadius;
        private double volume;
        private String spheroidType;
        public String[] spheroidInfo;
        
        //class methods
        
        //Class constructor
        Spheroid(float eR, float pR){
            this.setERadius(eR);
            this.setPRadius(pR);
            volume = 0;
            spheroidType = "";
            spheroidInfo = new String[4];
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
        private void setERadius(float equatorialRadius){
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
        private void setPRadius(float polarRadius){
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
