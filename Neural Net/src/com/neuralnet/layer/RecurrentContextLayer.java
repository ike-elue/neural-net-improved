/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neuralnet.layer;

import com.neuralnet.neuron.RecurrentContext;

/**
 *
 * @author CSLAB313-1740
 */
public class RecurrentContextLayer extends Layer {
    public RecurrentContextLayer(int neuronCount, int nextLayerNeuronCount, int activationType, int biggestRecurrentData) {
        super(neuronCount, nextLayerNeuronCount, activationType, biggestRecurrentData);
    }

    @Override
    public void initNeurons(int neuronCount, int nextLayerNeuronCount, int activationType, int biggestRecurrentData) {
        for(int i = 0; i < neuronCount; i++) {
            addNeuron(new RecurrentContext(nextLayerNeuronCount, biggestRecurrentData, activationType));
        }
    }
}
