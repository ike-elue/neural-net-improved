/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neuralnet.util;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author CSLAB313-1740
 */
public class Data {
    public final int length;
    private final ArrayList<double[][]> datas;
    private int pointer;
    
    public Data(int length) {
        this.length = length;
        datas = new ArrayList<>();
        pointer = 0;
    }
    
    public Data(double[][] data) {
        this.length = data.length;
        datas = new ArrayList<>();
        pointer = 0;
        for(int i = 0; i < data.length; i++) { // Iterates through {0,0}
            datas.add(new double[1][data[i].length]);
            for(int j = 0; j < data[i].length; j++) { // Iterates through 0 then 1
                datas.get(datas.size() - 1)[0][j] = data[i][j];
            }
        }
    }
    
    public void add(double[][] data) {
        if(pointer > length - 1)
            return;
        datas.add(data);
        pointer++;
    }
    
    public double[][] get(int index) {
        return datas.get(index);
    }
    
    @Override
    public String toString() {
        String str = "";
        str = datas.stream().map((data) -> Arrays.deepToString(data) + "\n").reduce(str, String::concat);
        return str;
    }
}
