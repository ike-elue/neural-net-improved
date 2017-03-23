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
public class FeedForwardBias extends FeedForwardInput{

    public FeedForwardBias(int connections, int biggestRecurrentData, int activationType) {
        super(connections, biggestRecurrentData, activationType);
        for(int i = 0; i < sum.length; i++)
            sum[i] = 1;
    }
    
    @Override
    public void forward(int timestep) {
        sum[timestep] = 1;
        super.forward(timestep);
    }
}
