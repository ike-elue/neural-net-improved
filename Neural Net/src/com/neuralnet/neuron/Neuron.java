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
    protected double[] weights;
    protected double sum, error, nodeDelta;
    private int pointer;
    private final int activationType;
    
    public Neuron(int connections, int activationType) {
        this.connections = new Neuron[connections]; 
        this.weights = new double[connections];
        this.activationType = activationType;
        pointer = 0;
        sum = 0;
        error = 0;
        nodeDelta = 0;
    }
    
    public abstract void forward();
    public abstract void backward(double learningRate);
    
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
        weights[pointer] = generateRand();
        pointer++;
    }
    
    public void addToSum(double value) {
        sum += value;
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
       sum = 0;
       error = 0;
       nodeDelta = 0;
    }

    public double getNodeDelta() {
        return nodeDelta;
    }
    
    public double getError() {
        return error;
    }
    
    public double getSum() {
        return sum;
    }
    
    public void setError(double error) {
        this.error = error;
    }
    
    public void setSum(double sum) {
        this.sum = sum;
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
            str += weights[i] + ", ";
        return str.substring(0, str.length() - 2);
    }
}
