package de.mp.TSP.test;

import de.mp.TSP.domain.clustering.NeuronCluster;
import de.mp.TSP.domain.clustering.TownCluster;
import de.mp.TSP.domain.positions.Neuron;
import de.mp.TSP.domain.positions.Town;
import de.mp.TSP.util.Initializer;

public class TestSimple 
{

	public static void main(String[] args) 
	{
		TownCluster tc =TownCluster.getInstance();
		tc.getCluster().add(new Town(0, 10));
		tc.getCluster().add(new Town(10, 0));
		tc.getCluster().add(new Town(0, -10));
		tc.getCluster().add(new Town(-10, 0));
		tc.getCluster().add(new Town(30, 10));
		tc.getCluster().add(new Town(10, 40));
		tc.getCluster().add(new Town(20, -10));
		tc.getCluster().add(new Town(-10, 20));
		
		System.out.println(tc);
		
		Neuron n1 = new Neuron(4, 4);
		Neuron n2 = new Neuron(5, 5);
		NeuronCluster nc = NeuronCluster.getInstance();
		nc.getCluster().add(n1);
		nc.getCluster().add(n2);
		
		System.out.println(nc);
		
		Initializer comp = Initializer.getInstance();
		comp.initializeNeuronCluster(0, tc);
	}

}
