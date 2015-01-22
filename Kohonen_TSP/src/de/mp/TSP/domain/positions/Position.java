package de.mp.TSP.domain.positions;

public abstract class Position 
{
	protected int position_x;
	protected int position_y;
	
	public int getPosition_x() {
		return position_x;
	}
	public void setPosition_x(int position_x) {
		this.position_x = position_x;
	}
	public int getPosition_y() {
		return position_y;
	}
	public void setPosition_y(int position_y) {
		this.position_y = position_y;
	}
	@Override
	public String toString() {
		return "Position [position_x=" + position_x + ", position_y="
				+ position_y + "]";
	}
	
}
