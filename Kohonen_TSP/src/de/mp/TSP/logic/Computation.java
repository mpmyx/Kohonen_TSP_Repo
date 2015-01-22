package de.mp.TSP.logic;

import de.mp.TSP.domain.clustering.NeuronCluster;
import de.mp.TSP.domain.clustering.TownCluster;
import de.mp.TSP.domain.positions.Neuron;
import de.mp.TSP.domain.positions.Position;

import java.util.ArrayList;

public class Computation {
	private static Computation singleInstance = null;

	private Computation() {
		// def. const. for singleton
	}

	public static Computation getInstance() {
		if (singleInstance == null) {
			singleInstance = new Computation();
		}
		return singleInstance;
	}

	/**
	 * @param learningRate
	 *            the innitial learning rate
	 * @param empiricalFactor
	 *            the empirical factor which effects on the behavior of
	 *            topology-changes
	 * @param iterations
	 *            the number of iterations being processed
	 */
	public double runApproximation(double learningRate, double empiricalFactor,
			int iterations) {
		double runtime = System.currentTimeMillis();
		double startLearningRate = learningRate;
		double endLearningRate = learningRate / 100;
		int townCount = TownCluster.getInstance().getCluster().size();

		for (int i = 0; i < iterations; i++) {
			// choose random input stimulus
			Position randomTown = TownCluster.getInstance().getCluster()
					.get((int) (Math.random() * townCount));
			Neuron winner = (Neuron) getWinnerNeuron(randomTown);
			// compute neighbour-driven topology-changes on neuron-layer
			Neuron n = winner;
			// all 50 % neighbours after winner
			for (int z = 0; z < (NeuronCluster.getInstance().getCluster()
					.size()) / 2; z++) {
				n.setPosition_x(n.getPosition_x()
						- (int) ((n.getPosition_x() - randomTown
								.getPosition_x()) * learningRate * Math.pow(
								empiricalFactor, z)));
				n.setPosition_y(n.getPosition_y()
						- (int) ((n.getPosition_y() - randomTown
								.getPosition_y()) * learningRate * Math.pow(
								empiricalFactor, z)));
				n = n.getNeighbour_next();
			}
			n = winner;
			// all 50 % neighbours previous to winner
			for (int z = 0; z < (NeuronCluster.getInstance().getCluster()
					.size()) / 2; z++) {
				n.setPosition_x(n.getPosition_x()
						- (int) ((n.getPosition_x() - randomTown
								.getPosition_x()) * learningRate * Math.pow(
								empiricalFactor, z)));
				n.setPosition_y(n.getPosition_y()
						- (int) ((n.getPosition_y() - randomTown
								.getPosition_y()) * learningRate * Math.pow(
								empiricalFactor, z)));
				n = n.getNeighbour_prev();
			}
			// update learning rate
			learningRate = startLearningRate
					* Math.pow(((endLearningRate) / startLearningRate),
							(i / iterations));
		}
		return System.currentTimeMillis() - runtime;
	}

	/**
	 * @param p1
	 *            Position 1
	 * @param p2
	 *            Position 2
	 * @return the distance between p1 and p2
	 */
	public double getDistanceBetweenTwoPositions(Position p1, Position p2) {
		return Math.sqrt(Math.pow((p1.getPosition_x() - p2.getPosition_x()), 2)
				+ Math.pow(p1.getPosition_y() - p2.getPosition_y(), 2));
	}

	/**
	 * @return the length of all edges of the neuron-ring
	 */
	public double getRoundtripLenght() {
		double tsp_l = 0.0d;
		for (Position p : NeuronCluster.getInstance().getCluster()) {
			Neuron n = (Neuron) p;
			tsp_l += getDistanceBetweenTwoPositions(n, n.getNeighbour_next());
		}
		tsp_l = Math.round(tsp_l);
		return tsp_l;
	}

	public Position getWinnerNeuron(Position inputPosition) {
		Neuron winner = (Neuron) NeuronCluster.getInstance().getCluster()
				.get(0);
		for (Position p : NeuronCluster.getInstance().getCluster()) {
			Neuron n = (Neuron) p;
			{
				if (getDistanceBetweenTwoPositions(n, inputPosition) < getDistanceBetweenTwoPositions(
						winner, inputPosition)) {
					winner = n;
				}
			}
		}
		return winner;
	}

	public void consolidate() {
		for (Position p : TownCluster.getInstance().getCluster()) {
			Neuron n = (Neuron) getWinnerNeuron(p);
			n.setPosition_x(p.getPosition_x());
			n.setPosition_y(p.getPosition_y());
			n.setIsInFinalPosition(true);
		}
		ArrayList<Neuron> tempCluster = new ArrayList<>();
		for (int i = 0; i < NeuronCluster.getInstance().getCluster().size(); i++) {
			Neuron n = (Neuron) NeuronCluster.getInstance().getCluster().get(i);
			if (n.getIsInFinalPosition()) {
				tempCluster.add((Neuron) NeuronCluster.getInstance()
						.getCluster().get(i));
			}
		}
		NeuronCluster.getInstance().getCluster().clear();
		NeuronCluster.getInstance().getCluster().addAll(tempCluster);
		chainingTopology(NeuronCluster.getInstance().getCluster().size());
	}

	public void chainingTopology(int amountOfNeurons) {
		// chaining neurons together
		for (int i = 1; i < amountOfNeurons - 1; i++) {
			Neuron n = (Neuron) NeuronCluster.getInstance().getCluster().get(i);
			n.setNeighbour_prev((Neuron) NeuronCluster.getInstance()
					.getCluster().get(i - 1));
			n.setNeighbour_next((Neuron) NeuronCluster.getInstance()
					.getCluster().get(i + 1));
		}
		// first neuron
		Neuron n = (Neuron) NeuronCluster.getInstance().getCluster().get(0);
		n.setNeighbour_prev((Neuron) NeuronCluster.getInstance().getCluster()
				.get(NeuronCluster.getInstance().getCluster().size() - 1));
		n.setNeighbour_next((Neuron) NeuronCluster.getInstance().getCluster()
				.get(1));
		// last neuron
		n = (Neuron) NeuronCluster.getInstance().getCluster()
				.get(NeuronCluster.getInstance().getCluster().size() - 1);
		n.setNeighbour_prev((Neuron) NeuronCluster.getInstance().getCluster()
				.get(NeuronCluster.getInstance().getCluster().size() - 2));
		n.setNeighbour_next((Neuron) NeuronCluster.getInstance().getCluster()
				.get(0));
	}
}
