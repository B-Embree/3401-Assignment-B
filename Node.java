public class Node
{
	//Fields
	public int x, y; 
	public Node next;
	//Constructor
	public Node(int x, int y)
	{
		this.x = x; this.y = y; this.next = null;
	}
	public Node(int x, int y, Node next)
	{
		this.x = x; this.y = y; this.next = next;
	}
}