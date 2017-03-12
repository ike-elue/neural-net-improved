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

    public FeedForwardBias(int connections, int activationType) {
        super(connections, activationType);
        sum = 1;
    }
    
    @Override
    public void forward() {
        sum = 1;
        super.forward();
    }
}
