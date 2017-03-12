/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neuralnet.network;

import com.neuralnet.layer.Layer;
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
    private final double learningRate;
    private double error;
    private BigDecimal errorPercent;
    
    protected final ArrayList<Layer> layers;
    protected final ArrayList<Integer> neuronCounts;
    private final ArrayList<double[]> trainingDataIn, trainingDataOut;
    
    public Network(int type, double learningRate) {
        layers = new ArrayList<>();
        neuronCounts = new ArrayList<>();
        trainingDataIn = new ArrayList<>();
        trainingDataOut = new ArrayList<>();
        this.learningRate = learningRate;
        this.activationType = type;
        error = 0;
    }
    
    protected abstract void initLayers(int activationType);
    
    public void addLayer(int neuronCount) {
        neuronCounts.add(neuronCount);
    }
    
    public void addLayers(int[] neuronCounts) {
        for(int neuronCount : neuronCounts) 
            this.neuronCounts.add(neuronCount);
    }
    
    private void init(double[][] input, double[][] output) {
        for(int i = 0; i < input.length; i++) {
            trainingDataIn.add(input[i]);
            trainingDataOut.add(output[i]);
        }
        initLayers(activationType);
        connect();
    }
    
    private void connect() {
        if(layers.isEmpty())
            return;
        for(int i = 0; i < layers.size() - 1; i++) {
            layers.get(i).connectTo(layers.get(i + 1), i < layers.size() - 2);
        }
    }
    
    public void train(double[][] inputs, double[][] outputs, int iterations, int everyNth) {
        if(inputs.length != outputs.length) {
            System.out.println("Data Mismatch");
            return;
        }
        init(inputs, outputs);
        if(layers.isEmpty())
            return;
        for(int i = 0; i < iterations; i++) {
            update(i, everyNth);
        }
        System.out.println("\nFinal Sturcture For Neural Network " + this);
    }
    
    public double[][] predict(double[][] inputs, int acceptablePercent) {
        if(layers.isEmpty())
            return null;
        if(errorPercent != null && (int)Double.parseDouble(errorPercent.toString()) > acceptablePercent) {
            System.out.println("Neural Net is not Good Enough Based on Percentage");
            return null;
        }
        double[][] outputs = new double[inputs.length][getLastLayer().getNeurons().length];
        for(int i = 0; i < inputs.length; i++) {
            for(int j = 0; j < inputs[i].length; j++) {
                layers.get(0).getNeuron(j).setSum(inputs[i][j]);
            }
            forward(); 
            for(int j = 0; j < outputs[i].length; j++) {
                outputs[i][j] = getLastLayer().getNeuron(j).getSum();
            }
        }
        return outputs;
    }
    
    private void update(int iterationCount, int everyNth) {
        for(int i = 0; i < trainingDataIn.size(); i++) {
            error = 0;
            for(int j = 0; j < trainingDataIn.get(i).length; j++) {
                layers.get(0).getNeuron(j).setSum(trainingDataIn.get(i)[j]);
            }
            
            forward(); 
            
            for(int j = 0; j < trainingDataOut.get(i).length; j++) {
                error += 0.5 * ((trainingDataOut.get(i)[j] - getLastLayer().getNeuron(j).getSum()) *(trainingDataOut.get(i)[j] - getLastLayer().getNeuron(j).getSum()));
                getLastLayer().getNeuron(j).setError(getLastLayer().getNeuron(j).getSum() - trainingDataOut.get(i)[j]);
            }
            
            backward();
            resetValues();
        }
        if(iterationCount % everyNth == 0) {
            errorPercent = new BigDecimal(error * 100).setScale(3, RoundingMode.DOWN);
            System.out.println(iterationCount + "th iteration --> Error: " + errorPercent + "%");
        }
    }
    
    private void forward() {
        layers.stream().forEach((layer) -> {
            layer.forward();
        });
    }
    
    private void backward() {
        for(int i = layers.size() - 1; i > -1; i--) {
            layers.get(i).backward(learningRate);
        }
    }
    
    private void resetValues() {
        layers.stream().forEach((layer) -> { 
            layer.reset();
        });
    }
    
    public Layer getLastLayer() {
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
