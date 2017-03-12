/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neuralnet.neuron;

/**
 *
 * @author Jonathan Elue
 */
public class FeedForwardInput extends Neuron{

    public FeedForwardInput(int connections, int activationType) {
        super(connections, activationType);
    }
    
    @Override
    public void forward() {
        for(int i = 0; i < connections.length; i++) {
            connections[i].addToSum(sum * weights[i]);
        }
    }
    
    @Override
    public void backward(double learningRate) {
        for(int i = 0; i < connections.length; i++) {
            weights[i] -= learningRate * connections[i].getNodeDelta() * sum;
            error += weights[i] * connections[i].getNodeDelta();
        }
        nodeDelta = error * activationPrimeFunc(sum);
    }
}
