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

    public FeedForwardInput(int connections, int biggestRecurrentData, int activationType) {
        super(connections, biggestRecurrentData, activationType);
    }
    
    @Override
    public void forward(int timestep) {
        for(int i = 0; i < connections.length; i++) {
            connections[i].addToSum(sum[timestep] * weights[i][timestep], timestep);
        }
    }
    
    @Override
    public void backward(double learningRate, int timestep) {
        for(int i = 0; i < connections.length; i++) {
            weights[i][timestep] -= learningRate * connections[i].getNodeDelta(timestep) * sum[timestep];
            error[timestep] += weights[i][timestep] * connections[i].getNodeDelta(timestep);
        }
        nodeDelta[timestep] += error[timestep] * activationPrimeFunc(sum[timestep]);
    }
}
