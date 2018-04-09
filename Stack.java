public class Stack 
{
	//Fields
	public Node top;
	//Constructor
	public Stack()
	{
		this.top = null;
	}
	//Methods
	public void push(Node n)
	{
		if (this.top == null)
			this.top = n;
		else
		{
			n.next = this.top;
			this.top = n;
		}
	}
	
	public Node pop()
	{
		if (this.top == null)
			return null;
		else
		{
			Node n = this.top;
			this.top = n.next;
			n.next = null;
			return n;
		}
	}
	
	public Stack copy()
	{
		Stack temp = new Stack();
		Stack newStack1 = new Stack();
		Stack newStack2 = new Stack();
		while (this.top != null)
		{
			temp.push(this.pop());
		}
		while (temp.top != null)
		{
			newStack1.push(temp.pop()); 
			newStack2.push(new Node(newStack1.peek().x, newStack1.peek().y));
		}
		this.top = newStack1.top;
		return newStack2;
	}
	
	public Node peek()
	{
		return this.top;
	}
}
