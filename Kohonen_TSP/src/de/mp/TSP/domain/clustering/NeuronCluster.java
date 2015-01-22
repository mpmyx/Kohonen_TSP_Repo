package de.mp.TSP.domain.clustering;

import de.mp.TSP.domain.positions.Neuron;

public class NeuronCluster extends Cluster<Neuron>
{
	private static NeuronCluster singleInstance = null;
	
	private NeuronCluster()
	{
		// def. const. for singleton
	}
	
	public static NeuronCluster getInstance()
	{
		if (singleInstance == null)
			singleInstance = new NeuronCluster();
		return singleInstance;
	}

	@Override
	public String toString() {
		return "NeuronCluster [cluster=" + cluster + "]";
	}

}
