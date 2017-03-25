/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neuralnet.network;

import com.neuralnet.layer.Layer;
import com.neuralnet.util.Data;
import com.neuralnet.util.Util;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

/**
 *
 * @author CSLAB313-1740
 */
public abstract class Network {
    public static final int SIGMOID = 0, HYPERTAN = 1;
    private final int activationType;
    private final int biggestRecurrentData; // Has to be the biggest size of rows for a single Data entry
    protected final double learningRate;
    protected double[] error;
    protected BigDecimal errorPercent;
    
    protected final ArrayList<Layer> layers;
    protected final ArrayList<Integer> neuronCounts;
    protected final ArrayList<double[][]> trainingDataIn, trainingDataOut;
    
    public Network(int type, double learningRate, int biggestRecurrentData) {
        layers = new ArrayList<>();
        neuronCounts = new ArrayList<>();
        trainingDataIn = new ArrayList<>();
        trainingDataOut = new ArrayList<>();
        this.learningRate = learningRate;
        this.activationType = type;
        this.biggestRecurrentData = biggestRecurrentData;
        error = null;
    }
    
    protected abstract void initLayers(int activationType, int biggestRecurrentData);
    
    public void addLayer(int neuronCount) {
        neuronCounts.add(neuronCount);
    }
    
    public void addLayers(int[] neuronCounts) {
        for(int neuronCount : neuronCounts) 
            this.neuronCounts.add(neuronCount);
    }
    
    private void init(Data input, Data output) {
        addData(input, output);
        initLayers(activationType, biggestRecurrentData);
        connect();
    }
    
    // Can be Overriden
    protected void connect() {
        if(layers.isEmpty())
            return;
        for(int i = 0; i < layers.size() - 1; i++) {
            layers.get(i).connectTo(layers.get(i + 1), i < layers.size() - 2);
        }
    }
    
    // Can be overriden
    protected boolean conditional(Data inputs, Data outputs) {
        return inputs.length != outputs.length;
    }
    
    // Can be overriden
    protected void addData(Data input, Data output) {
        for(int i = 0; i < input.length; i++) {
            trainingDataIn.add(input.get(i));
            trainingDataOut.add(output.get(i));
        }
    }
    
    public final void train(Data inputs, Data outputs, int iterations, int everyNth) { 
        if(conditional(inputs, outputs)) {
            System.out.println("Bad Data");
            return;
        }
        init(inputs, outputs);
        if(layers.isEmpty())
            return;
        error = new double[trainingDataIn.size()];
        for(int i = 0; i < iterations + 1; i++) {
            update(i, everyNth);
        }
        System.out.println("\nFinal Sturcture For Neural Network " + this);
    }
    
    // Can override
    public double[][] predict(Data inputs, int acceptablePercent) {
        if(layers.isEmpty())
            return null;
        if(errorPercent != null && (int)Double.parseDouble(errorPercent.toString()) > acceptablePercent) {
            System.out.println("Neural Net is not Good Enough Based on Percentage");
            return null;
        }
        double[][] outputs = new double[inputs.length][getLastLayer().getNeurons().length];
        for(int i = 0; i < inputs.length; i++) {
            for(int j = 0; j < inputs.get(i).length; j++) { // Goes through each timestep
                for(int k = 0; k < inputs.get(i)[j].length; k++) { // Goes through each data point per timestep
                    layers.get(0).getNeuron(k).setSum(inputs.get(i)[j][k], j);
                }
                forward(j);
            }
            for(int j = 0; j < outputs[i].length; j++) { //Goes through each timestep output
                outputs[i][j] = getLastLayer().getNeuron(j).getSum(inputs.get(i).length - 1);
            }
            resetValues();
        }
        
        return outputs;
    }
    
    public double[][] predict(double[][] inputs, int acceptablePercent) {
        if(layers.isEmpty())
            return null;
        if(errorPercent != null && (int)Double.parseDouble(errorPercent.toString()) > acceptablePercent) {
            System.out.println("Neural Net is not Good Enough Based on Percentage");
            return null;
        }
        double[][] outputs = new double[1][getLastLayer().getNeurons().length];
        for(int j = 0; j < inputs.length; j++) { // Goes through each timestep
            for(int k = 0; k < inputs[j].length; k++) { // Goes through each data point per timestep
                layers.get(0).getNeuron(k).setSum(inputs[j][k], j);
            }
            forward(j);
        }
        for(int j = 0; j < outputs[0].length; j++) { //Goes through each timestep output
            outputs[0][j] = getLastLayer().getNeuron(j).getSum(inputs.length - 1);
        }
        resetValues();
        
        return outputs;
    }
    
    protected void update(int iterationCount, int everyNth) {
        for(int i = 0; i < trainingDataIn.size(); i++) {
            error[i] = 0;
            for(int j = 0; j < trainingDataIn.get(i).length; j++) { // Goes through each timestep
                for(int k = 0; k < trainingDataIn.get(i)[j].length; k++) { // Goes through each data point per timestep
                    layers.get(0).getNeuron(k).setSum(trainingDataIn.get(i)[j][k], j);
                }
                forward(j);
            }
            
            for(int j = trainingDataOut.get(i).length - 1; j > -1; j--) { // Goes through each timestep
                for(int k = 0; k < trainingDataOut.get(i)[j].length; k++) { // Goes through each data point per timestep
                    error[i] += 0.5 * ((trainingDataOut.get(i)[j][k] - getLastLayer().getNeuron(k).getSum(j)) *(trainingDataOut.get(i)[j][k] - getLastLayer().getNeuron(k).getSum(j)));
                    getLastLayer().getNeuron(k).setError(getLastLayer().getNeuron(k).getSum(j) - trainingDataOut.get(i)[j][k], j);
                }
                backward(j);
            }
            finalBackward();
            resetValues();
        }
        if(iterationCount % everyNth == 0) {
            double e = Util.average(error);
            errorPercent = new BigDecimal(e * 100).setScale(3, RoundingMode.DOWN);
            System.out.println(iterationCount + "th iteration --> Error: " + errorPercent + "%");
        }
    }
    
    private void forward(int timestep) {
        layers.stream().forEach((layer) -> {
            layer.forward(timestep);
        });
    }
    
    private void backward(int timestep) {
        for(int i = layers.size() - 1; i > -1; i--) {
            layers.get(i).backward(learningRate, timestep);
        }
    }
    
    private void finalBackward() {
        for(int i = layers.size() - 1; i > -1; i--) {
            layers.get(i).finalBackward();
        }
    }
    
    protected void resetValues() {
        layers.stream().forEach((layer) -> { 
            layer.fullReset();
        });
    }
    
    protected Layer getLastLayer() {
        return layers.get(layers.size() - 1);
    }
    
    @Override
    public String toString() {
        int counter = 0;
        String str = "";
        for(int i = 0; i < layers.size() - 1; i++) 
            str += "\n\nLayer " + counter++ + ": " + layers.get(i);
        return str + "\n\nError in Network: " + errorPercent + "%";
    }
}
