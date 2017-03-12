/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neuralnet.layer;

import com.neuralnet.neuron.FeedForwardOutput;

/**
 *
 * @author CSLAB313-1740
 */
public class FeedForwardOutputLayer extends Layer {

    public FeedForwardOutputLayer(int neuronCount, int nextLayerNeuronCount, int activationType) {
        super(neuronCount - 1, nextLayerNeuronCount, activationType);
    }

    @Override
    public void initNeurons(int neuronCount, int nextLayerNeuronCount, int activationType) {
        for(int i = 0; i < neuronCount; i++) {
            addNeuron(new FeedForwardOutput(0, activationType));
        }
    }
}
