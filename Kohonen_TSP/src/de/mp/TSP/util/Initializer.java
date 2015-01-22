package de.mp.TSP.util;

import de.mp.TSP.domain.clustering.NeuronCluster;
import de.mp.TSP.domain.clustering.TownCluster;
import de.mp.TSP.domain.positions.Neuron;
import de.mp.TSP.domain.positions.Position;
import de.mp.TSP.logic.Computation;

public class Initializer {

    private static Initializer singleInstance = null;
    
    private Initializer() {
        // def. const. for singleton
    }
    
    public static Initializer getInstance() {
        if (singleInstance == null) {
            singleInstance = new Initializer();
        }
        return singleInstance;
    }
    
    public Double[] computeBarycenter(TownCluster input) {
        int sumX = 0;
        int sumY = 0;
        for (Position t : input.getCluster()) {
            sumX += t.getPosition_x();
            sumY += t.getPosition_y();
        }
        Double centerX = (double) sumX / (double) input.getCluster().size();
        Double centerY = (double) sumY / (double) input.getCluster().size();
        Double[] barycenter = {centerX, centerY};
        return barycenter;
    }
    
    public Double computeRadius(TownCluster input) {
        double tempMin = Double.MAX_VALUE;
        double tempMax = Double.MIN_VALUE;
        double tempWidth = 0.0d;
        double tempHeight = 0.0d;
        // computes the maximum spread of the x-dimension
        for (Position t : input.getCluster()) {
            if (t.getPosition_x() < tempMin) {
                tempMin = t.getPosition_x();
            }
            if (t.getPosition_x() > tempMax) {
                tempMax = t.getPosition_x();
            }
        }
        tempWidth = tempMax - tempMin;

        // same for the y-dimension
        tempMin = Double.MAX_VALUE;
        tempMax = Double.MIN_VALUE;
        for (Position t : input.getCluster()) {
            if (t.getPosition_y() < tempMin) {
                tempMin = t.getPosition_y();
            }
            if (t.getPosition_y() > tempMax) {
                tempMax = t.getPosition_y();
            }
        }
        tempHeight = tempMax - tempMin;

        // if the spread of the y-dimension is bigger 
        // than the one of the x-dimension, return y-dimension's spread
        if (tempHeight < tempWidth) {
            return tempWidth / 2;
        } // same vice versa
        else {
            return tempHeight / 2;
        }
    }
    
    public void initializeNeuronCluster(int amountOfNeurons, TownCluster input) {
        // get the barycenter of the Towncluster
        Double[] baryCenter = this.computeBarycenter(input);
        System.out.println("Barycenter = " + baryCenter[0] + " | " + baryCenter[1]);
        // get initial baryCenter-angle 
        Double angle = 0.0d;
        // now generate step-by-step n neurons within a ring-topology
        for (int i = 0; i < amountOfNeurons; i++) {
            Neuron neuron = new Neuron(
                    (int) (Math.sin(angle * (Math.PI / 180)) * computeRadius(input).intValue() + baryCenter[0]),
                    (int) (Math.cos(angle * (Math.PI / 180)) * computeRadius(input).intValue() + baryCenter[1]));
            // System.out.println("Pos = "+neuron.getPosition_x()+" | "+neuron.getPosition_y());
            // add to cluster
            NeuronCluster.getInstance().getCluster().add(neuron);
            // compute next angle to set the following neuron
            angle = angle + 360.d / amountOfNeurons;
        }
        Computation.getInstance().chainingTopology(amountOfNeurons);
    }
    
    public void reset()
    {
        TownCluster.getInstance().getCluster().clear();
        NeuronCluster.getInstance().getCluster().clear();
    }
}
