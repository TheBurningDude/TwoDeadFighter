/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.four.group.ai;

import dk.four.group.common.data.NodeType;


/**
 *
 * @author Sidon K. K
 */
public class Node {
    
    private float X;
    private float Y;
    
    private int Width;
    private int Height;
    
    private NodeType type = NodeType.NONE;
    
    private float F = 0; // F Cost
    private float G = 0; // G Cost
    private float H = 0; // Heuristical value
    
    private Node Parent = null;

    public Node(float X, float Y){
        this.X = X;
        this.Y = Y;      
    }
    
    public Node(float  X, float Y, int Width, int Height){
        this.X = X;
        this.Y = Y;
        this.Width = Width;
        this.Height = Height;
    }
    
    public Node(float X, float Y, int Width, int Height, Boolean Unpassable){
                this.X = X;
		this.Y = Y;
		this.Width = Width;
		this.Height = Height;
    }
    
    public Node(float X, float Y, Boolean Unpassable){
		this.X = X;
		this.Y = Y;
	}
    
    public float GetCenterX(){
		return X + (Width/2);
	}
	
    public float GetCenterY(){
		return Y + (Height/2);
	}
    
    public float getX() {
        return X;
    }

    public float getY() {
        return Y;
    }

    public int getWidth() {
        return Width;
    }
    
    public int getHeight() {
        return Height;
    }

    public NodeType getType() {
        return type;
    }

    public float getF() {
        return F;
    }

    public float getG() {
        return G;
    }

    public float getH() {
        return H;
    }

    public Node getParent() {
        return Parent;
    }
    
    public void setX(float X) {
        this.X = X;
    }

    public void setY(float Y) {
        this.Y = Y;
    }

    public void setWidth(int Width) {
        this.Width = Width;
    }

    public void setHeight(int Height) {
        this.Height = Height;
    }

    public void setType(NodeType type) {
        this.type = type;
    }

    public void setF(float F) {
        this.F = F;
    }

    public void setG(float G) {
        this.G = G;
    }

    public void setH(float H) {
        this.H = H;
    }

    public void setParent(Node Parent) {
        this.Parent = Parent;
    }
    
    public void CalculateNode(Node Parent, Node Start, Node End){
		this.Parent = Parent;
		
		if (Parent != null)
		{
			/*G Cost is movement cost between this node's parent and itself.
			 * If Horizontal/Vertical, the G Cost is 10.
			 * If Diagonal, the G Cost is sqrt(10^2 + 10^2) = 14.44... or 14 for rounding.*/
			if (Math.abs(X - Parent.X) != 0 &&
					Math.abs(Y - Parent.Y) != 0)
			{
				G = Parent.G + 14;
			}
			else
			{
				G = Parent.G + 10;
			}
		}
		
		/*Heuristic Value is the distance between this node and the end node. It's used to optimise the search
		 *for the best node in each turn.		
		 */
		
		/*Manhattan Method of Calculating Heuristic, simpler to calculate but innaccurate for 8-way pathfinding*/
		//H = ((Math.abs(X-End.X)/Width) + (Math.abs(Y - End.Y)/Height)) * 10;
		
		/*Diagonal Shortcut of Calculating Heuristic, takes diagonal movements into account but slower.*/
		float xDistance = (Math.abs(X-End.X)/Width);
		float yDistance = (Math.abs(End.Y-Y)/Height);
		
		if (xDistance > yDistance)
			H = 14*yDistance + 10*(xDistance-yDistance);
	    else
	    	H = 14*xDistance + 10*(yDistance-xDistance);
		
		/*F cost is the sum of G cost and Heuristic, and is used to compare each node to see which
		 *is closest to the end node. */
		F = G + H;
	}
    
    public void Reset(){
		F = G = H = 0;
		Parent = null;
	}
    
}

