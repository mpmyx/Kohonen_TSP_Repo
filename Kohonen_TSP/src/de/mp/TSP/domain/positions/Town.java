package de.mp.TSP.domain.positions;


public class Town extends Position
{
	public Town (int position_x, int position_y)
	{
		this.position_x = position_x;
		this.position_y = position_y;
		
		
	}

	@Override
	public String toString() {
		return "Town [position_x=" + position_x + ", position_y=" + position_y
				+ "]";
	}
}
