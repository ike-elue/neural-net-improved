/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neuralnet.main;

import com.neuralnet.network.Network;
import com.neuralnet.network.RecurrentNetwork;
import com.neuralnet.util.Data;
import com.neuralnet.util.Util;

/**
 *
 * @author Jonathan Elue
 */
public class Main {

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) {
// Recurrent SIGMOID
        Network net = new RecurrentNetwork(Network.SIGMOID, 5, 7);
        net.addLayers(new int[] {26, 27, 26});
        String[] words = new String[] {"hello", "its", "me", "that", "youre", "looking", "for"};
        Data in = new Data(words.length);
        Data out = new Data(words.length);
        for(String str : words) {
            in.add(Util.createMulti(str.substring(0, str.length() - 1)));
            out.add(Util.createMulti(str.substring(1)));
        }
        net.train(in, out, 20000, 5000);
        String str = "";
        for(int i = 0; i < words.length; i++)
            str += Util.concat(Util.getCharacter(in.get(i))) +  Util.concat(Util.getCharacter(net.predict(in.get(i), 50))) + " ";
        System.out.println("Created Sentence: " + str);
        
// XOR SIGMOID
//        Network net = new FeedForwardNetwork(Network.SIGMOID, 1);
//        net.addLayers(new int[] {2, 4, 1});
//        Data in = new Data(new double[][] {{0,0},{0,1},{1,0},{1,1}});
//        Data out = new Data(new double[][]{{0},{1},{1},{0}});
//        net.train(in, out, 100000, 20000);
//        Data predict = new Data(new double[][] {{0,0},{0,1},{1,1},{1,0},{1,1},{0,0},{0,1},{1,1}});
//        System.out.println("Input:\n" + predict + "\nOutput: " + Arrays.deepToString(
//                //Util.round
//        (net.predict(predict, 1))));
       
// XOR HYPERTAN
//        Network net = new FeedForwardNetwork(Network.HYPERTAN, 1);
//        net.addLayers(new int[] {2, 4, 1});
//        Data in = new Data(new double[][] {{-1,-1},{-1,1},{1,-1},{1,1}});
//        Data out = new Data(new double[][]{{-1},{1},{1},{-1}});
//        net.train(in, out, 100000, 20000, 1);
//        Data predict = new Data(new double[][] {{-1,-1},{-1,1},{1,1},{1,-1},{1,1},{-1,-1},{-1,1},{1,1}});
//        System.out.println("Input:\n" + predict + "\nOutput: " + Arrays.deepToString(
//                //Util.roundMore
//        (net.predict(predict, 1))));
    }
    
}
