/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neuralnet.network;

/**
 *
 * @author CSLAB313-1740
 */
public class RecurrentNetwork extends Network{

    public RecurrentNetwork(int type, double learningRate) {
        super(type, learningRate);
    }

    @Override
    protected void initLayers(int activationType, int biggestRecurrentData) {
        
    }
    
}
