package engine;

import api.*;

public class GPSNode {

	private GPSState state;

	private GPSNode parent;

	private Integer cost;
	
	private int depth;

	public GPSNode(GPSState state, Integer cost) {
		this(state, cost, 0);
	}
	
	public GPSNode(GPSState state, Integer cost, int depth) {
		super();
		this.state = state;
		this.cost = cost;
		this.depth = depth;
	}


	public int getDepth() {
		return depth;
	}
	
	public GPSNode getParent() {
		return parent;
	}

	public void setParent(GPSNode parent) {
		this.parent = parent;
	}

	public GPSState getState() {
		return state;
	}

	public Integer getCost() {
		return cost;
	}

	@Override
	public String toString() {
		return state.toString();
	}

	public String getSolution() {
		if (this.parent == null) {
			return this.state.toString();
		}
		return this.parent.getSolution() + "\n" + this.state;
	}
}
