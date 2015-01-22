package de.mp.TSP.domain.positions;


public class Neuron extends Position
{
	private Neuron neighbour_prev;
	private Neuron neighbour_next;
	private Boolean isInFinalPosition;
	private Double accurancy;
	
	public Neuron (int position_x, int position_y)
	{
		this.position_x = position_x;
		this.position_y = position_y;
		this.neighbour_next = null;
		this.neighbour_prev = null;
		this.isInFinalPosition = false;
		this.accurancy = Double.MAX_VALUE;
	}
	
	public Neuron getNeighbour_prev() {
		return neighbour_prev;
	}
	
	public void setNeighbour_prev(Neuron neighbour_prev) {
		this.neighbour_prev = neighbour_prev;
	}
	public Neuron getNeighbour_next() {
		return neighbour_next;
	}
	
	public void setNeighbour_next(Neuron neighbour_next) {
		this.neighbour_next = neighbour_next;
	}

	public Boolean getIsInFinalPosition() {
		return isInFinalPosition;
	}

	public void setIsInFinalPosition(Boolean isInFinalPosition) {
		this.isInFinalPosition = isInFinalPosition;
	}
	
	public Double getAccurancy() {
		return accurancy;
	}
	
	public void setAccurancy(Double accurancy) {
		this.accurancy = accurancy;
	}

	@Override
	public String toString() {
		return "Neuron [neighbour_prev=" + neighbour_prev + ", neighbour_next="
				+ neighbour_next + ", isInFinalPosition=" + isInFinalPosition
				+ ", accurancy=" + accurancy + ", position_x=" + position_x
				+ ", position_y=" + position_y + "]";
	}
	
	
	
}
