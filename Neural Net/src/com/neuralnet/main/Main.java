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
// Recurrent SIGMOID (HYPERTAN doesn't work yet when trying to implement)
        Network net = new RecurrentNetwork(Network.SIGMOID, 1, 4);
        net.addLayers(new int[] {26, 27, 26});
        Data in = new Data(2);
        in.add(Util.createMulti(new char[]{'h','e','l','l'}));
        in.add(Util.createMulti(new char[]{'w','o','r','l'}));
        Data out = new Data(2);
        out.add(Util.createMulti(new char[]{'e','l','l','o'}));
        out.add(Util.createMulti(new char[]{'o','r','l','d'}));
        net.train(in, out, 100000, 20000);
        System.out.println("Input -> " + Util.concat(Util.getCharacter(in.get(0))) + "]" + Util.concat(Util.getCharacter(net.predict(in, 10))).substring(0,1) + "<- Output");
        System.out.println("Input -> " + Util.concat(Util.getCharacter(in.get(1))) + "]" + Util.concat(Util.getCharacter(net.predict(in, 10))).substring(1) + "<- Output");
        
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
