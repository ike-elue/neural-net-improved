/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neuralnet.util;

/**
 *
 * @author CSLAB313-1740
 */
public class Util {
    public static double average(double[] array) {
        if(array == null)
            return 0;
        double average = 0;
        for(double value : array) {
            average += value;
        }
        return average/array.length;
    }
    
    public static int[][] round(double[][] array) {
        if(array == null)
            return null;
        int[][] a = new int[array.length][array[0].length];
        for(int i = 0; i < a.length; i++) {
            for(int j = 0; j < a[0].length; j++) {
                if(array[i][j] >= .5)
                    a[i][j] = 1;
            }
        }
        return a;
    }
    
    public static int[][] roundMore(double[][] array) {
        if(array == null)
            return null;
        int[][] a = new int[array.length][array[0].length];
        for(int i = 0; i < a.length; i++) {
            for(int j = 0; j < a[0].length; j++) {
                if(array[i][j] >= 0)
                    a[i][j] = 1;
                else
                    a[i][j] = -1;
            }
        }
        return a;
    }
    
    public static double[][] createMulti(char[] chars) {
        if(chars == null)
            return null;
        Interpreter inter = new Interpreter();
        double[][] values = new double[chars.length][4]; // For now
        for(int i = 0; i < values.length; i++) {
            values[i] = inter.access(chars[i]);
        }
        return values;
    }
    
    public static Character[] getCharacter(double[][] values) {
        if(values == null)
            return null;
        Interpreter inter = new Interpreter();
        Character[] chars = new Character[values.length];
        for(int i = 0; i < chars.length; i++) {
            chars[i] = inter.access(values[i]);
        }
        return chars;
    }
    
    public static String concat(Character[] chars) {
        String str = "";
        for(char c : chars)
            str += c;
        return str;
    } 
}
