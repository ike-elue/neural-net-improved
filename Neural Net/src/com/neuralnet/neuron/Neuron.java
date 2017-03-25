/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neuralnet.neuron;

import com.neuralnet.network.Network;

/**
 *
 * @author Jonathan Elue
 */
public abstract class Neuron {
    
    protected Neuron[] connections;
    protected double[][] weights;
    protected double[] sum, error, nodeDelta;
    private double numberHolder;
    private int pointer;
    private final int activationType;
    
    public Neuron(int connections, int biggestRecurrentData, int activationType) {
        this.connections = new Neuron[connections]; 
        this.weights = new double[connections][biggestRecurrentData];
        this.activationType = activationType;
        pointer = 0;
        numberHolder = 0;
        sum = new double[biggestRecurrentData];
        error = new double[biggestRecurrentData];
        nodeDelta = new double[biggestRecurrentData];
    }
    
    public abstract void forward(int timestep);
    public abstract void backward(double learningRate, int timestep);
    
    public final void finalBackward() {
        for(int i = 0; i < weights.length; i++) { // Each single value
            for(int j = 0; j < weights[0].length; j++) { // Each timestep per single value
                numberHolder += weights[i][j]; 
            }
            weights[i][0] = numberHolder/weights[0].length;
            numberHolder = 0;
        }
        for(int i = 0; i < weights.length; i++) {
            for(int j = 1; j < weights[0].length; j++) {
                weights[i][j] = weights[i][0];
            }
        }
    }
    
    public void connectToMultiple(Neuron[] ns, boolean useBias) {
        if(pointer != 0)
            return;
        int i = ns.length;
        if(useBias)
            i--;
        for(int index = 0; index < i; index++)
            connectTo(ns[index]);
    }
    public void connectTo(Neuron n) {
        if(pointer >= connections.length)
            return;
        connections[pointer] = n;
        weights[pointer][0] = generateRand();
        for(int i = 1; i < weights[0].length; i++) {
            weights[pointer][i] = weights[pointer][0];
        }
        pointer++;
    }
    
    public void addToSum(double value, int timestep) {
        sum[timestep] += value;
    }
    
    public double activationFunc(double sum) {
        if(activationType == Network.SIGMOID)
            return 1.0 / (1 + Math.exp(-1.0 * sum));
        if(activationType == Network.HYPERTAN)
            return (Math.exp(sum) - Math.exp(-sum)) / (Math.exp(sum) + Math.exp(-sum));
        System.out.println("Error In Activation Function Type");
        return 0;
    }
    
    public double activationPrimeFunc(double sum) {
        if(activationType == Network.SIGMOID)
            return sum * (1 - sum);
        if(activationType == Network.HYPERTAN)
            return 1 - (sum * sum);
        System.out.println("Error In Activation Function Type");
        return 0;
    }
    
    public double generateRand() {
        return Math.random();
    }
    
    public final void reset() {
       for(int i = 0; i < error.length; i++) {
           error[i] = 0;
           nodeDelta[i] = 0;
       }
    }
    
    public final void fullReset() {
       for(int i = 0; i < error.length; i++) {
           sum[i] = 0;
           error[i] = 0;
           nodeDelta[i] = 0;
       }
    }

    public double getNodeDelta(int timestep) {
        return nodeDelta[timestep];
    }
    
    public double getError(int timestep) {
        return error[timestep];
    }
    
    public double getSum(int timestep) {
        return sum[timestep];
    }
    
    public double getSumLast() {
        return sum[sum.length - 1];
    }
    
    public void setError(double error, int timestep) {
        this.error[timestep] = error;
    }
    
    public void setSum(double sum, int timestep) {
        this.sum[timestep] = sum;
    }
    
    public void hasConnections() {
        System.out.println(connections != null);
    }
    
    public void connectionLength() {
        System.out.println(connections.length);
    }
    
    @Override
    public String toString() {
        String str = "Weights: ";
        for(int i = 0; i < weights.length; i++) 
            str += weights[i][0] + ", ";
        return str.substring(0, str.length() - 2);
    }
}
