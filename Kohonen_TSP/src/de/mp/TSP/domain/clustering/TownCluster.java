package de.mp.TSP.domain.clustering;

import de.mp.TSP.domain.positions.Town;

public class TownCluster extends Cluster<Town>
{
	private static TownCluster singleInstance = null;
	
	private TownCluster()
	{
		// def. const. for singleton
	}
	
	public static TownCluster getInstance()
	{
		if (singleInstance == null)
			singleInstance = new TownCluster();
		return singleInstance;
	}

	@Override
	public String toString() {
		return "TownCluster [cluster=" + cluster + "]";
	}
}
