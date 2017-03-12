/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neuralnet.main;

import com.neuralnet.network.FeedForwardNetwork;
import com.neuralnet.network.Network;
import java.util.Arrays;

/**
 *
 * @author Jonathan Elue
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
// XOR SIGMOID
        Network net = new FeedForwardNetwork(Network.SIGMOID, 1);
        net.addLayers(new int[] {2, 4, 1});
        double[][] in = new double[][] {{0,0},{0,1},{1,0},{1,1}};
        double[][] out = new double[][] {{0},{1},{1},{0}};
        net.train(in, out, 50000, 10000);
        System.out.println(Arrays.deepToString(net.predict(new double[][]{{0,1},{1,1},{1,0}}, 1)));
        


// XOR HYPERTAN
//        Network net = new FeedForwardNetwork(Network.HYPERTAN, 1);
//        net.addLayers(new int[] {2, 4, 1});
//        double[][] in = new double[][] {{-1,-1},{-1,1},{1,-1},{1,1}};
//        double[][] out = new double[][] {{-1},{1},{1},{-1}};
//        net.train(in, out, 50000, 10000);
//        System.out.println(Arrays.deepToString(net.predict(new double[][]{{-1,1},{1,1},{1,-1}}, 1)));
    }
    
}
