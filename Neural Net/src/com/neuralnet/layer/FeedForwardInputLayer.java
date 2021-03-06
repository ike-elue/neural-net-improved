/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neuralnet.layer;

import com.neuralnet.neuron.FeedForwardBias;
import com.neuralnet.neuron.FeedForwardInput;

/**
 *
 * @author CSLAB313-1740
 */
public class FeedForwardInputLayer extends Layer {
    public FeedForwardInputLayer(int neuronCount, int nextLayerNeuronCount, int activationType, int biggestRecurrentData) {
        super(neuronCount, nextLayerNeuronCount, activationType, biggestRecurrentData);
    }

    @Override
    public void initNeurons(int neuronCount, int nextLayerNeuronCount, int activationType, int biggestRecurrentData) {
        for(int i = 0; i < neuronCount - 1; i++) {
            addNeuron(new FeedForwardInput(nextLayerNeuronCount, biggestRecurrentData, activationType));
        }
        addNeuron(new FeedForwardBias(nextLayerNeuronCount, biggestRecurrentData, activationType));
    }
}
