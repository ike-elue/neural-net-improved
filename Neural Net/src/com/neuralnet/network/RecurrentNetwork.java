/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neuralnet.network;

import com.neuralnet.layer.FeedForwardHiddenLayer;
import com.neuralnet.layer.FeedForwardInputLayer;
import com.neuralnet.layer.FeedForwardOutputLayer;
import com.neuralnet.layer.RecurrentContextLayer;
import com.neuralnet.util.Data;

/**
 *
 * @author CSLAB313-1740
 */
public class RecurrentNetwork extends Network{

    public RecurrentNetwork(int type, double learningRate, int biggestRecurrentData) {
        super(type, learningRate, biggestRecurrentData);
    }

    @Override
    protected void initLayers(int activationType, int biggestRecurrentData) {
        if(neuronCounts.size() < 3) {
            System.out.println("Not Enough Layers");
            return;
        }
        layers.add(new FeedForwardInputLayer(neuronCounts.get(0), neuronCounts.get(1), activationType, biggestRecurrentData));
        for(int i = 1; i < neuronCounts.size() - 1; i++) {
            layers.add(new RecurrentContextLayer(neuronCounts.get(i) - 1, neuronCounts.get(i), activationType, biggestRecurrentData));
            layers.add(new FeedForwardHiddenLayer(neuronCounts.get(i), neuronCounts.get(i + 1), activationType, biggestRecurrentData));
        }
        layers.add(new FeedForwardOutputLayer(neuronCounts.get(neuronCounts.size() - 1), 0, activationType, biggestRecurrentData));
    }
    
    @Override
    protected void connect() {
        if(layers.isEmpty())
                return;
        layers.get(0).connectTo(layers.get(2), true);
        for(int i = 1; i < layers.size() - 1; i += 2) {
            layers.get(i).connectTo(layers.get(i + 1), i < layers.size() - 2);
            layers.get(i + 1).connectTo(layers.get(i + 2), i + 1 < layers.size() - 2);
        }
    }
    
    @Override
    protected void addData(Data input, Data output) {
        for(int i = 0; i < input.length; i++) {
            trainingDataIn.add(input.get(i));
            trainingDataOut.add(output.get(i));
        }
    }
    
}
