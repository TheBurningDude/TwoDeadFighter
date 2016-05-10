/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.four.group.ai;

import dk.four.group.common.data.Entity;
import dk.four.group.common.data.EntityPosition;
import dk.four.group.common.data.NodeType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;



/**
 *
 * @author Sidon K. K
 */
public class PathFinder {
    
    
    
    private ArrayList<ArrayList<Node>> grid = new ArrayList<ArrayList<Node>>(); //Actual Grid
    private ArrayList<Node> openPath = new ArrayList<Node>(); //Open List: For all checked nodes
    private ArrayList<Node> closedPath = new ArrayList<Node>(); //Closed Lists: For all checked nodes possibly used for final path.
    private ArrayList<Node> finalPath = new ArrayList<Node>(); //Final Path
    private int GridX;
    private int GridY;
    private int StartX = -1;
    private int StartY = -1;
    private int EndX = -1; 
    private int EndY = -1;
    
    public static int Found = 1;
    public static int NonExistant = 2;
    
    public PathFinder(int ScreenWidth, int ScreenHeight, int GridSize)
	{
		for (int y = 0; y < (int)(ScreenHeight/GridSize); y++)
		{
			grid.add(new ArrayList<Node>());
			for (int x = 0; x < (int)(ScreenWidth/GridSize); x++)
			{
				grid.get(y).add(new Node(x * GridSize, y * GridSize, GridSize, GridSize));
			}
		}
		
		GridX = GridSize;
		GridY = GridSize;
	}
	
	public PathFinder(int ScreenWidth, int ScreenHeight, int GridSizeX, int GridSizeY)
	{
		for (int y = 0; y < (int)(ScreenHeight/GridSizeY); y++)
		{
			grid.add(new ArrayList<Node>());
			for (int x = 0; x < (int)(ScreenWidth/GridSizeX); x++)
			{
				grid.get(y).add(new Node(x * GridSizeX, y * GridSizeY, GridSizeX, GridSizeY));
			}
		}
		
		GridX = GridSizeX;
		GridY = GridSizeY;
	}
    
    
    public int findPath(){
		/*Clear current path*/
		openPath.clear();
		closedPath.clear();
		finalPath.clear();
		
		/*Reset all F, G and H values to 0*/
		for (int y = 0; y < grid.size(); y++)
		{
			for (int x = 0; x < grid.get(y).size(); x++)
			{
				grid.get(y).get(x).Reset();
			}
		}
		
		/*If no Start or End nodes have been set, quit the findPath.*/
		if (StartX == -1 || StartY == -1 || EndX == -1 || EndY == -1)
		{
			return NonExistant;
		}
		else if (StartX == EndX && StartY == EndY) /*If Start = End, return found.*/
		{
			return Found;
		}
		else
		{
			openPath.add(grid.get(StartY).get(StartX)); //Add Start node to open
			SetOpenList(StartX, StartY); //Set neighbours for Start node.
			
			closedPath.add(openPath.get(0)); //Add Start node to closed.
			openPath.remove(0); //Remove Start Node from open.
			
			/*If the last value in the closedPath array isn't the end node, go through the while loop*/
			while (closedPath.get(closedPath.size() - 1) != grid.get(EndY).get(EndX))
			{				
				if (!openPath.isEmpty())
				{
					float bestF = 100000;
					int bestFIndex = -1;
					
					//Get node with lowest F cost in the open list.
					for (int i = 0; i < openPath.size(); i++)
					{
						if (openPath.get(i).getF() < bestF)
						{
							bestF = openPath.get(i).getF();
							bestFIndex = i;
						}
					}
					
					if (bestFIndex != -1)
					{
						closedPath.add(openPath.get(bestFIndex)); //Add node to closed list
						openPath.remove(bestFIndex); //remove from open list
						
						//Set Neighbours for parent
						SetOpenList((int)(closedPath.get(closedPath.size() - 1).getX()/GridX), (int)(closedPath.get(closedPath.size() - 1).getY()/GridY));
					}
					else
						return NonExistant;
				}
				else
				{
					return NonExistant;
				}
			}
		}
		
		/*Time to get our final path*/
		/*Add our end node to the final path*/
		Node g = closedPath.get(closedPath.size() - 1);
		finalPath.add(g);
		
		/*Then while our last finalPath element is not the start node...*/
		while (g != grid.get(StartY).get(StartX))
		{
			/*Add the parent of that last finalPath element to the finalPath Array*/
			g = g.getParent();
			finalPath.add(g);
			/*Once the finalPath reaches the start node, we have a complete path.*/
		}
		
		/*Reverse the path so the start node will be the first element of the array
		 *not the last*/
                Collections.reverse(finalPath);
		
		return Found;
	}
    
    public void SetOpenList(int X, int Y){
		/*Check position of X and Y to avoid IndexOutofBounds.*/
		Boolean ignoreLeft = (X - 1 < 0);
		Boolean ignoreRight = (X + 1 >= grid.get(Y).size());
		Boolean ignoreUp = (Y - 1 < 0);
		Boolean ignoreDown = (Y + 1 >= grid.size());
		
		/*If the adjacent node isn't out of bounds, look at the node*/
		if (!ignoreLeft && !ignoreUp)
		{
			LookNode(grid.get(Y).get(X), grid.get(Y-1).get(X-1));
		}
		
		if (!ignoreUp)
		{
			LookNode(grid.get(Y).get(X), grid.get(Y-1).get(X));
		}
		
		if (!ignoreRight && !ignoreUp)
		{
			LookNode(grid.get(Y).get(X), grid.get(Y-1).get(X+1));
		}
		
		if (!ignoreLeft)
		{
			LookNode(grid.get(Y).get(X), grid.get(Y).get(X-1));
		}
		
		if (!ignoreRight)
		{
			LookNode(grid.get(Y).get(X), grid.get(Y).get(X+1));
		}
		
		if (!ignoreLeft && !ignoreDown)
		{
			LookNode(grid.get(Y).get(X), grid.get(Y+1).get(X-1));
		}
		
		if (!ignoreDown)
		{
			LookNode(grid.get(Y).get(X), grid.get(Y+1).get(X));
		}
		
		if (!ignoreRight && !ignoreDown)
		{
			LookNode(grid.get(Y).get(X), grid.get(Y+1).get(X+1));
		}
	}
    
    public void CompareParentwithOpen(Node Parent, Node Open){
		/*Compares to see if Open Listed node would lead to a better path than the Parent node.
		 *This is done by setting a temporary G cost using the open node and an added cost
		 *depending on whether the Parent Node is Diagonal or not to said open node.*/
		
		float tempGCost = Open.getG();
		
		if (Math.abs(Open.getX() - Parent.getY())/GridX == 1 && Math.abs(Open.getY() - Parent.getY())/GridY == 1)
		{
			tempGCost += 14;
		}
		else
		{
			tempGCost += 10;
		}
		
		/*If the temporary G cost is smaller than the Parent Node's G cost,
		 *the open node is recalcuated and the Parent Node is set as the
		 *open node's parent node.*/
		if (tempGCost < Parent.getG())
		{
			Open.CalculateNode(Parent, 
							grid.get(StartY).get(StartX), 
							grid.get(EndY).get(EndX));
			openPath.set(openPath.indexOf(Open), Open);
		}
	}
    
    public void LookNode(Node Parent, Node Current){
		/*The Adjacent Node must be ignored if it's either an unpassable grid type or it's in the closedPath list*/
		if (Current.getType() != NodeType.UNPASSABLE && 
				!(closedPath.contains(Current) || closedPath.contains(Current)))
		{
			if (!(openPath.contains(Current) || openPath.contains(Current)))
			{
				/*Since the node is valid, it must be added to the openPath, with the current node
				 *set as its Parent and the F, G and H costs calculated based on the start and end node.*/
				Current.CalculateNode(Parent, grid.get(StartY).get(StartX), grid.get(EndY).get(EndX));
				openPath.add(Current);
			}
			else
			{
				/*If the node is already in the openPath list, it must be compared with the current node
				 *to see if this node will lead to a better path than the current node's path.*/
				CompareParentwithOpen(Parent, 
						openPath.get(openPath.indexOf(Current)));
			}
		}
	}
    
    public void SetGridNode(int screenX, int screenY, NodeType Type){
		/*Sets the GrideNode Type, to either START, END, UNPASSABLE or NONE.*/
		int pointX = (int)(screenX/GridX);
		int pointY = (int)(screenY/GridY);
		
		if (pointY >= 0 && pointY < grid.size())
		{
			if (pointX >=0 && pointX < grid.get(pointY).size())
			{
				if (Type == NodeType.START || Type == NodeType.END)
				{
					for (int y = 0; y < grid.size(); y++)
					{
						for (int x = 0; x < grid.get(y).size(); x++)
						{
							if (grid.get(y).get(x).getType() == Type)
							{
								if (Type == NodeType.START)
								{
									StartX = -1;
									StartY = -1;
								}
								else if (Type == NodeType.END)
								{
									EndX = -1;
									EndY = -1;
								}
								
								grid.get(y).get(x).setType(NodeType.UNPASSABLE);
							}
						}
					}
				}
				
				if (grid.get(pointY).get(pointX).getType() == Type)
					grid.get(pointY).get(pointX).setType(NodeType.UNPASSABLE);
				else
				{
					if (Type == NodeType.START)
					{
						StartX = pointX;
						StartY = pointY;
					}
					else if (Type == NodeType.END)
					{
						EndX = pointX;
						EndY = pointY;
					}
					
					grid.get(pointY).get(pointX).setType(Type);
				}
			}
		}
	}
    
    public ArrayList<Node> GetPath(){
		return finalPath;
	}

}
