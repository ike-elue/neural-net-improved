/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neuralnet.network;

import com.neuralnet.layer.FeedForwardHiddenLayer;
import com.neuralnet.layer.FeedForwardInputLayer;
import com.neuralnet.layer.FeedForwardOutputLayer;

/**
 *
 * @author Jonathan Elue
 */
public class FeedForwardNetwork extends Network {

    public FeedForwardNetwork(int activationType, double learningRate) {
        super(activationType, learningRate);
    }
    
    @Override
    protected void initLayers(int activationType) {
        if(neuronCounts.size() < 3) {
            System.out.println("Not Enough Layers");
            return;
        }
        layers.add(new FeedForwardInputLayer(neuronCounts.get(0), neuronCounts.get(1), activationType));
        for(int i = 1; i < neuronCounts.size() - 1; i++) {
            layers.add(new FeedForwardHiddenLayer(neuronCounts.get(i), neuronCounts.get(i + 1), activationType));
        }
        layers.add(new FeedForwardOutputLayer(neuronCounts.get(neuronCounts.size() - 1), 0, activationType));
    }
    
}
