
public class BinaryHeap 
{
	//Fields
	public int index; public A_Star[] bh;
	//Constructor
	public BinaryHeap(int size)
	{
		this.bh = new A_Star[size]; this.index = 0;
	}
	//Methods
	public A_Star getMin()
	{
		if (this.index == 0)
			return null;
		else
			return this.bh[0];
	}
	
	public void insert(A_Star as)
	{
		int currentPosition = index;
		this.index++;
		this.bh[currentPosition] = as;
		while(currentPosition != 0 || this.bh[currentPosition / 2].distanceFunction.estimatedDistance > this.bh[currentPosition].distanceFunction.estimatedDistance)
		{
			A_Star temp = this.bh[currentPosition / 2];
			this.bh[currentPosition / 2] = this.bh[currentPosition];
			this.bh[currentPosition] = temp;
			currentPosition /= 2;
		}
	}
	
	public A_Star deleteMin()
	{
		if (this.index == 0)
			return null;
		A_Star min = this.bh[0];
		this.index--;
		this.bh[0] = this.bh[index];
		this.bh[index] = null;
		int currentPosition = 0; int leftPosition = currentPosition * 2 + 1; int rightPosition = currentPosition * 2 + 2;
		boolean downHeaped = false;
		while(!downHeaped)
		{
			if (rightPosition <= index - 1) //if right child is available, left is too
			{
				if (this.bh[leftPosition].distanceFunction.estimatedDistance <= this.bh[rightPosition].distanceFunction.estimatedDistance)
				{
					if (this.bh[currentPosition].distanceFunction.estimatedDistance > this.bh[leftPosition].distanceFunction.estimatedDistance)
					{
						//left <= right and left < current
						A_Star temp = this.bh[leftPosition]; //make a copy of left
						this.bh[leftPosition] = this.bh[currentPosition]; //overwrite left with current
						this.bh[currentPosition] = temp; //put left copy in current
						currentPosition = leftPosition; leftPosition = currentPosition * 2 + 1; rightPosition = currentPosition * 2 + 2; //update positions
					}
					else //current location <= left, so we're done
						downHeaped = true;
				}
				else //right position < left position
				{
					if (this.bh[currentPosition].distanceFunction.estimatedDistance > this.bh[rightPosition].distanceFunction.estimatedDistance)
					{
						//left > right and right < current
						A_Star temp = this.bh[rightPosition]; //make a copy of right
						this.bh[rightPosition] = this.bh[currentPosition]; //overwrite right with current
						this.bh[currentPosition] = temp; //put right copy in current
						currentPosition = rightPosition; leftPosition = currentPosition * 2 + 1; rightPosition = currentPosition * 2 + 2; //update positions
					}
					else //current location <= left, so we're done
						downHeaped = true;
				}
			}
			else if (leftPosition <= index - 1) //if only the left is available
			{
				if (this.bh[currentPosition].distanceFunction.estimatedDistance > this.bh[leftPosition].distanceFunction.estimatedDistance)
				{
					//left < current
					A_Star temp = this.bh[leftPosition]; //make a copy of left
					this.bh[leftPosition] = this.bh[currentPosition]; //overwrite left with current
					this.bh[currentPosition] = temp; //put left copy in current
					currentPosition = leftPosition; leftPosition = currentPosition * 2 + 1; rightPosition = currentPosition * 2 + 2; //update positions
				}
				else //current location <= left, so we're done
					downHeaped = true;
			}
			else //at the bottom, so we're done
				downHeaped = true;
		}
		return min;
	}
}
