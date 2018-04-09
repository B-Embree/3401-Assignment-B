
public class f 
{
	//Fields
	public int g; public int estimatedDistance;
	//Constructor
	public f(int g, Node current, Node goal)
	{
		double xGoal = goal.x; double yGoal = goal.y;
		double xCurr = current.x; double yCurr = current.y;
		this.g = g;
		this.estimatedDistance = g +  -2 * g;//(int) Math.floor(Math.sqrt(Math.pow(xGoal - xCurr, 2) + Math.pow(yGoal - yCurr, 2))) - 1;
	}
	//Methods
	public void updateDistance(int g, Node current, Node goal)
	{
		double xGoal = goal.x; double yGoal = goal.y;
		double xCurr = current.x; double yCurr = current.y;
		this.g = g;
		this.estimatedDistance = g + (int) Math.floor(Math.sqrt(Math.pow(xGoal - xCurr, 2) + Math.pow(yGoal - yCurr, 2))) - 1;
	}
	
}
