package de.mp.TSP.domain.clustering;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import de.mp.TSP.domain.positions.Position;

@SuppressWarnings("unused")
public abstract class Cluster<E>
{
	// Change here the strategy of list-Implementation
	// e.g. LinkedList or ArrayList or whatever
	protected List<E> cluster = new ArrayList<E>();

	@SuppressWarnings("unchecked")
	public List<Position> getCluster() {
		return (List<Position>) cluster;
	}

	@SuppressWarnings("unchecked")
	public void setCluster(List<Position> cluster) {
		this.cluster = (List<E>) cluster;
	}

	@Override
	public String toString() {
		return "ClusterList [cluster=" + cluster + "]";
	}
}
