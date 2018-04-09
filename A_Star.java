import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class A_Star 
{
	//Fields
	public Node n; public Stack s; public f distanceFunction;
	//17 x 38
	public static int table[][] = new int[16][38];
	public static char grid[][] = { //Goal = (5, 10), Start = (15, 24)
{'C','C','C','C','C','C','C','C','C','C','C','C','C','C','C','C','C','C','C','C','C','C','C','C','C','C','C','C','C','C','C','C','C','C','C','C','C','C'}, // x is down, y is across left to right
{'C','C','C','C','C','C','C','C','C','C','C','C','C','C','C','C','C','C','C','C','C','C','C','C','C','C','C','C','C','C','C','C','C','C','C','C','C','C'},
{'C','C','C','C','C','C','C','C','C','C','C','C','C','C','C','C','C','C','C','C','C','C','C','C','C','C','C','C','C','C','C','C','C','C','C','C','C','C'},
{'C','C','C','C','R','R','C','C','C','C','C','C','C','C','C','C','C','C','C','C','C','C','R','C','C','C','C','C','C','C','C','C','C','C','R','R','C','C'},
{'C','C','R','R','C','C','R','C','C','C','C','C','C','C','C','R','R','R','C','C','C','C','C','R','C','R','R','R','C','R','R','R','R','R','R','R','C','C'},
{'R','R','R','R','C','R','R','R','R','R','G','R','R','R','R','R','R','C','C','R','R','C','C','C','R','R','R','C','C','R','C','R','R','R','C','C','R','C'},
{'C','R','R','R','C','R','R','R','R','R','R','R','R','R','R','R','R','C','C','C','C','C','C','R','R','R','R','C','R','R','R','R','R','R','C','R','R','C'},
{'R','C','R','R','C','C','C','C','C','C','R','C','C','C','C','R','R','C','R','C','C','C','C','R','R','C','C','C','R','R','R','R','R','R','C','R','R','C'},
{'C','R','R','R','R','C','C','C','R','R','C','C','R','R','C','R','R','C','C','C','C','R','R','R','C','C','C','R','R','R','R','C','C','C','C','C','C','R'},
{'C','C','C','R','R','R','C','C','C','C','C','C','R','R','R','C','C','C','R','R','R','R','R','R','C','C','C','R','R','R','R','C','C','C','C','C','R','C'},
{'C','C','R','R','R','C','R','R','C','C','R','C','R','R','R','R','C','C','R','R','R','C','R','R','C','R','R','C','C','C','C','C','R','R','C','R','R','C'},
{'C','R','C','C','C','C','R','R','R','C','R','C','R','R','R','R','C','R','R','C','C','C','C','C','C','C','C','C','C','R','C','R','C','C','C','C','C','C'},
{'C','C','C','R','C','R','R','C','C','C','C','C','R','R','R','C','C','C','C','R','C','R','C','R','R','C','C','C','R','R','R','R','C','C','C','C','C','C'},
{'C','R','C','R','R','R','R','C','C','C','C','C','C','C','R','C','C','R','R','C','C','C','R','R','R','C','C','C','C','C','R','R','R','C','C','C','C','C'},
{'R','C','C','C','C','C','R','R','C','C','C','C','C','R','R','C','C','R','R','R','R','C','C','R','R','C','C','C','C','C','R','R','R','R','C','C','C','C'},
{'R','C','C','C','R','R','C','C','C','C','R','R','C','R','R','C','R','C','C','C','C','R','R','R','S','C','R','C','C','C','C','R','R','C','C','C','C','C'} //add comma back here!
//{'C','C','C','C','R','R','C','C','C','C','C','C','C','C','C','R','C','C','C','C','C','C','C','C','C','C','C','C','C','C','C','C','C','C','C','C','C','C'},
					};
//--------------------------------------------------------------------------------------------------------------------------------------------------------------
	//Constructor
	public A_Star(Node n, Stack s, f estimatedDistance)
	{
		this.n = n; this.s = s; this.distanceFunction = estimatedDistance;
	}
	//Methods
	public A_Star findPath()
	{
		BinaryHeap bh = new BinaryHeap(16 * 38);
		bh.insert(this);
		table[this.n.x][this.n.y] = 1;
		while(bh.index != 0)
		{
			A_Star current = bh.deleteMin();
			if (current.n.x == 5 && current.n.y == 10)
				return current;
			int currentX = current.n.x; int currentY = current.n.y; 
			if (currentX - 1 >= 0 && grid[currentX - 1][currentY] != 'R' && table[currentX - 1][currentY] != 1) //move up one if in index range, not a rock, and haven't visited
			{
				Stack newStack = current.s.copy();
				newStack.push(new Node(currentX, currentY));
				table[currentX - 1][currentY] = 1;
				A_Star newNode = new A_Star(new Node(currentX - 1, currentY), newStack, new f(current.distanceFunction.g + 1, new Node(currentX - 1, currentY), new Node(5, 10)));
				bh.insert(newNode);
			}
			if (currentX + 1 <= 15 && grid[currentX + 1][currentY] != 'R' && table[currentX + 1][currentY] != 1) //move down one if in index range, not a rock, and haven't visited
			{
				Stack newStack = current.s.copy();
				newStack.push(new Node(currentX, currentY));
				table[currentX + 1][currentY] = 1;
				A_Star newNode = new A_Star(new Node(currentX + 1, currentY), newStack, new f(current.distanceFunction.g + 1, new Node(currentX + 1, currentY), new Node(5, 10)));
				bh.insert(newNode);
			}
			if (currentY - 1 >= 0 && grid[currentX][currentY - 1] != 'R' && table[currentX][currentY - 1] != 1) //move left one if in index range, not a rock, and haven't visited
			{
				Stack newStack = current.s.copy();
				newStack.push(new Node(currentX, currentY));
				table[currentX][currentY - 1] = 1;
				A_Star newNode = new A_Star(new Node(currentX, currentY - 1), newStack, new f(current.distanceFunction.g + 1, new Node(currentX, currentY - 1), new Node(5, 10)));
				bh.insert(newNode);
			}
			if (currentY + 1 <= 37 && grid[currentX][currentY + 1] != 'R' && table[currentX][currentY + 1] != 1) //move right one if in index range, not a rock, and haven't visited
			{
				Stack newStack = current.s.copy();
				newStack.push(new Node(currentX, currentY));
				table[currentX][currentY + 1] = 1;
				A_Star newNode = new A_Star(new Node(currentX, currentY + 1), newStack, new f(current.distanceFunction.g + 1, new Node(currentX, currentY + 1), new Node(5, 10)));
				bh.insert(newNode);
			}
			
		}
		
		return null;
	}
	
	public static void main(String[] args)
	{
		Node start = new Node(15, 24);
		Node goal = new Node(5, 10);
		A_Star as = new A_Star(start, new Stack(), new f(0, start, goal));
		A_Star path  = as.findPath();
		System.out.println("(" + path.n.x + ", " + path.n.y + ")");
		while (path.s.top != null)
		{
			System.out.println("(" + path.s.top.x + ", " + path.s.top.y + ")");
			path.s.pop();
		}		
	}
}
