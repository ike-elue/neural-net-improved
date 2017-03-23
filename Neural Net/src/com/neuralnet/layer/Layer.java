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
    
    public Layer(int neuronCount, int nextLayerNeuronCount, int activationType, int biggestRecurrentData) {
        neurons = new Neuron[neuronCount + 1];
        initNeurons(neuronCount + 1, nextLayerNeuronCount, activationType, biggestRecurrentData);
    }
    
    public abstract void initNeurons(int neuronCount, int nextLayerNeuronCount, int activationType, int biggestRecurrentData);

    public void addNeuron(Neuron n) {
        if(pointer >= neurons.length)
            return;
        neurons[pointer] = n;
        pointer++;
    }

    public void forward(int timestep) {
        for(Neuron neuron : neurons)
            neuron.forward(timestep); 
    }
    public void backward(double learningRate, int timestep) {
        for(Neuron neuron : neurons)
            neuron.backward(learningRate, timestep); 
    }
    
    public final void finalBackward() {
        for(Neuron neuron : neurons)
            neuron.finalBackward(); 
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
    
    public void fullReset() {
        for(Neuron neuron : neurons)
            neuron.fullReset();
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
