/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neuralnet.layer;

import com.neuralnet.neuron.Neuron;

/**
 *
 * @author Jonathan Elue
 */
public abstract class Layer {
    private final Neuron[] neurons;
    private int pointer;
    
    public Layer(int neuronCount, int nextLayerNeuronCount, int activationType) {
        neurons = new Neuron[neuronCount + 1];
        initNeurons(neuronCount + 1, nextLayerNeuronCount, activationType);
    }
    
    public abstract void initNeurons(int neuronCount, int nextLayerNeuronCount, int activationType);

    public void addNeuron(Neuron n) {
        if(pointer >= neurons.length)
            return;
        neurons[pointer] = n;
        pointer++;
    }

    public void forward() {
        for(Neuron neuron : neurons)
            neuron.forward(); 
    }
    public void backward(double learningRate) {
        for(Neuron neuron : neurons)
            neuron.backward(learningRate); 
    }
    
    public void connectTo(Layer layer, boolean useBias) {
        for (Neuron neuron : neurons) {
            neuron.connectToMultiple(layer.getNeurons(), useBias);
        }
    }
    
    public void reset() {
        for(Neuron neuron : neurons)
            neuron.reset();
    }
    
    public Neuron[] getNeurons() {
        return neurons;
    }
    
    public Neuron getNeuron(int i) {
        return neurons[i];
    }
    
    @Override
    public String toString() {
        int counter = 0;
        String str = "";
        for (Neuron neuron : neurons) {
            str += "\nNeuron " + counter++ + "\n" + neuron;
        }
        return str;
    }
}
