/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neuralnet.util;

import java.util.Arrays;
import java.util.HashMap;

/**
 *
 * @author CSLAB313-1740
 */
public class Interpreter {
    private final HashMap<Character, double[]> map, advancedMap, advancedMapTan;
    public Interpreter() {
        map = new HashMap<>();
        advancedMap = new HashMap<>();
        advancedMapTan = new HashMap<>();
        init();
    }
    
    private void init() {
        map.put('h',new double[]{1,0,0,0});
        map.put('e',new double[]{0,1,0,0});
        map.put('l',new double[]{0,0,1,0});
        map.put('o',new double[]{0,0,0,1});
        
        advancedMap.put('a',new double[]{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0});
        advancedMap.put('b',new double[]{0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0});
        advancedMap.put('c',new double[]{0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0});
        advancedMap.put('d',new double[]{0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0});
        advancedMap.put('e',new double[]{0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0});
        advancedMap.put('f',new double[]{0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0});
        advancedMap.put('g',new double[]{0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0});
        advancedMap.put('h',new double[]{0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0});
        advancedMap.put('i',new double[]{0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0});
        advancedMap.put('j',new double[]{0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0});
        advancedMap.put('k',new double[]{0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0});
        advancedMap.put('l',new double[]{0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0});
        advancedMap.put('m',new double[]{0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0});
        advancedMap.put('n',new double[]{0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0});
        advancedMap.put('o',new double[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0});
        advancedMap.put('p',new double[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0});
        advancedMap.put('q',new double[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0});
        advancedMap.put('r',new double[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0});
        advancedMap.put('s',new double[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0});
        advancedMap.put('t',new double[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0});
        advancedMap.put('u',new double[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0});
        advancedMap.put('v',new double[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0});
        advancedMap.put('w',new double[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0});
        advancedMap.put('x',new double[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0});
        advancedMap.put('y',new double[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0});
        advancedMap.put('z',new double[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1});
        
        double[] d = new double[26];
        for(int i = 0; i < d.length; i++)
            d[i] = -1;
        int pointer = 0;
        for(char c : advancedMap.keySet()) {
            d[pointer] = 1;
            if(pointer != 0)
                d[pointer - 1] = -1;
            advancedMapTan.put(c, d);
            pointer++;
        }
    }
    
    public double[] access(char c) {
        return advancedMap.get(c);
    }
    
    public Character access(double[] array) {
        for(char c : advancedMap.keySet()) {
            if(compare(advancedMap.get(c), array))
                return c;
        }
        return null;
    }
    
    public double[] accessTan(char c) {
        return advancedMapTan.get(c);
    }
    
    public Character accessTan(double[] array) {
        for(char c : advancedMapTan.keySet()) {
            if(compare(advancedMapTan.get(c), array))
                return c;
        }
        return null;
    }
    
    public double[] accessTest(char c) {
        return map.get(c);
    }
    
    public Character accessTest(double[] array) {
        for(char c : map.keySet()) {
            if(compare(map.get(c), array))
                return c;
        }
        return null;
    }
    
    private boolean compare(double[] a1, double[] a2) {
        if(a1.length != a2.length)
            return false;
        int value = 0;
        for(int i = 0; i < a1.length; i++) {
            if(a2[i] >= .5)
                value = 1;
            if(a1[i] != value)
                return false;
            value = 0;
        }
        return true;
    }
}