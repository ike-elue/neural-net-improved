/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neuralnet.neuron;

/**
 *
 * @author CSLAB313-1740
 */
public class RecurrentContext extends FeedForwardInput{

    public RecurrentContext(int connections, int biggestRecurrentData, int activationType) {
        super(connections, biggestRecurrentData, activationType);
    }
    
    @Override
    public void forward(int timestep) {
        if(timestep == 0)
            return;
        for(int i = 0; i < connections.length; i++) {
            sum[timestep] = connections[i].getSum(timestep - 1);
            connections[i].addToSum(sum[timestep] * weights[i][timestep], timestep);
        }
    }
}
