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
public class FeedForwardOutput extends Neuron{

    public FeedForwardOutput(int connections, int activationType) {
        super(connections, activationType);
    }
    
    @Override
    public void forward() {
        sum = activationFunc(sum);
    }

    //Requires a previous calc of error (so, real - sum)
    @Override
    public void backward(double learningRate) {
        nodeDelta = error * activationPrimeFunc(sum);
    }
}
