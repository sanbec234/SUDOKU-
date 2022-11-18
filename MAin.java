import java.lang.*;
import java.util.*;


class GFG{

    static int N = 9;
    
    // Function to check if all elements
    // of the board[][] array store
    // value in the range[1, 9]
    static boolean isinRange(int[][] board)
    {
        
        // Traverse board[][] array
        for(int i = 0; i < N; i++)
        {
            for(int j = 0; j < N; j++)
            {
                
                // Check if board[i][j]
                // lies in the range
                if (board[i][j] <= 0 ||
                    board[i][j] > 9)
                {
                    return false;
                }
            }
        }
        return true;
    }
    
    // Function to check if the solution
    // of sudoku puzzle is valid or not
    boolean isValidSudoku(int board[][])
    {
        
        // Check if all elements of board[][]
        // stores value in the range[1, 9]
        if (isinRange(board) == false)
        {
            return false;
        }
    
        // Stores unique value
        // from 1 to N
        boolean[] unique = new boolean[N + 1];
    
        // Traverse each row of
        // the given array
        for(int i = 0; i < N; i++)
        {
            
            // Initialize unique[]
            // array to false
            Arrays.fill(unique, false);
    
            // Traverse each column
            // of current row
            for(int j = 0; j < N; j++)
            {
                
                // Stores the value
                // of board[i][j]
                int Z = board[i][j];
    
                // Check if current row
                // stores duplicate value
                if (unique[Z])
                {
                    return false;
                }
                unique[Z] = true;
            }
        }
    
        // Traverse each column of
        // the given array
        for(int i = 0; i < N; i++)
        {
            
            // Initialize unique[]
            // array to false
            Arrays.fill(unique, false);
    
            // Traverse each row
            // of current column
            for(int j = 0; j < N; j++)
            {
                
                // Stores the value
                // of board[j][i]
                int Z = board[j][i];
    
                // Check if current column
                // stores duplicate value
                if (unique[Z])
                {
                    return false;
                }
                unique[Z] = true;
            }
        }
    
        // Traverse each block of
        // size 3 * 3 in board[][] array
        for(int i = 0; i < N - 2; i += 3)
        {
            
            // j stores first column of
            // each 3 * 3 block
            for(int j = 0; j < N - 2; j += 3)
            {
                
                // Initialize unique[]
                // array to false
                Arrays.fill(unique, false);
    
                // Traverse current block
                for(int k = 0; k < 3; k++)
                {
                    for(int l = 0; l < 3; l++)
                    {
                        
                        // Stores row number
                        // of current block
                        int X = i + k;
    
                        // Stores column number
                        // of current block
                        int Y = j + l;
    
                        // Stores the value
                        // of board[X][Y]
                        int Z = board[X][Y];
    
                        // Check if current block
                        // stores duplicate value
                        if (unique[Z])
                        {
                            return false;
                        }
                        unique[Z] = true;
                    }
                }
            }
        }
    
        // If all conditions satisfied
        return true;
    }        
}

// Java program for above approach
class Sudoku {

	// N is the size of the 2D matrix N*N
	static int N = 9;

	/* Takes a partially filled-in grid and attempts
	to assign values to all unassigned locations in
	such a way to meet the requirements for
	Sudoku solution (non-duplication across rows,
	columns, and boxes) */
	static boolean solveSudoku(int grid[][], int row,
							int col)
	{

		/*if we have reached the 8th
		row and 9th column (0
		indexed matrix) ,
		we are returning true to avoid further
		backtracking	 */
		if (row == N - 1 && col == N)
			return true;

		// Check if column value becomes 9 ,
		// we move to next row
		// and column start from 0
		if (col == N) {
			row++;
			col = 0;
		}

		// Check if the current position
		// of the grid already
		// contains value >0, we iterate
		// for next column
		if (grid[row][col] != 0)
			return solveSudoku(grid, row, col + 1);

		for (int num = 1; num < 10; num++) {

			// Check if it is safe to place
			// the num (1-9) in the
			// given row ,col ->we move to next column
			if (isSafe(grid, row, col, num)) {

				/* assigning the num in the current
				(row,col) position of the grid and
				assuming our assigned num in the position
				is correct */
				grid[row][col] = num;

				// Checking for next
				// possibility with next column
				if (solveSudoku(grid, row, col + 1))
					return true;
			}
			/* removing the assigned num , since our
			assumption was wrong , and we go for next
			assumption with diff num value */
			grid[row][col] = 0;
		}
		return false;
	}

	/* A utility function to print grid */
	static void print(int[][] grid)
	{
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++)
				System.out.print(grid[i][j] + " ");
			System.out.println();
		}
	}


	static boolean isSafe(int[][] grid, int row, int col,int num)
	{ 

	
		for (int x = 0; x <= 8; x++)
			if (grid[row][x] == num)
				return false;

	
		for (int x = 0; x <= 8; x++)
			if (grid[x][col] == num)
				return false;

		
		int startRow = row - row % 3, startCol= col - col % 3;
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				if (grid[i + startRow][j + startCol] == num)
					return false;

		return true;
	}
}


 
class MAin
{
    static int[] mat[];
    int n;
    int sqrtn;
    int fill;
    MAin(int n, int fill)
    {
        this.n = n;
        this.fill = fill;
        Double sqrtnd = Math.sqrt(n);
        sqrtn = sqrtnd.intValue();
 
        mat = new int[n][n];
    }
 
    public void fillValues()
    {
        fillDiag();
        fillRem(0, sqrtn);
        remDigits();
    }
    void fillDiag()
    {
 
        for (int i = 0; i<n; i=i+sqrtn)
            fillBox(i, i);
    }
 
    boolean unUsedInBox(int rowStart, int colStart, int num)
    {
        for (int i = 0; i<sqrtn; i++)
            for (int j = 0; j<sqrtn; j++)
                if (mat[rowStart+i][colStart+j]==num)
                    return false;
 
        return true;
    }
 
    void fillBox(int row,int col)
    {
        int num;
        for (int i=0; i<sqrtn; i++)
        {
            for (int j=0; j<sqrtn; j++)
            {
                do
                {
                    num = randomGenerator(n);
                }
                while (!unUsedInBox(row, col, num));
 
                mat[row+i][col+j] = num;
            }
        }
    }
 
    int randomGenerator(int num)
    {
        return (int) Math.floor((Math.random()*num+1));
    }
 
    boolean CheckIfSafe(int i,int j,int num)
    {
        return (unUsedInRow(i, num) &&
                unUsedInCol(j, num) &&
                unUsedInBox(i-i%sqrtn, j-j%sqrtn, num));
    }

    boolean unUsedInRow(int i,int num)
    {
        for (int j = 0; j<n; j++)
           if (mat[i][j] == num)
                return false;
        return true;
    }
    
    boolean unUsedInCol(int j,int num)
    {
        for (int i = 0; i<n; i++)
            if (mat[i][j] == num)
                return false;
        return true;
    }

    boolean fillRem(int i, int j)
    {
        if (j>=n && i<n-1)
        {
            i = i + 1;
            j = 0;
        }
        if (i>=n && j>=n)
            return true;
 
        if (i < sqrtn)
        {
            if (j < sqrtn)
            {
                j = sqrtn;
            }
        }
        else if (i < n-sqrtn)
        {
            if (j==(int)(i/sqrtn)*sqrtn)
            {
                j =  j + sqrtn;
            }
        }
        else
        {
            if (j == n-sqrtn)
            {
                i = i + 1;
                j = 0;
                if (i>=n)
                    return true;
            }
        }
 
        for (int num = 1; num<=n; num++)
        {
            if (CheckIfSafe(i, j, num))
            {
                mat[i][j] = num;
                if (fillRem(i, j+1))
                    return true;
 
                mat[i][j] = 0;
            }
        }
        return false;
    }

    public void remDigits()
    {
        int count = fill;
        while (count != 0)
        {
            int cellId = randomGenerator(n*n)-1;
            int i = (cellId/n);
            int j = cellId%9;
            if (j != 0)
                j = j - 1;
            if (mat[i][j] != 0)
            {
                count--;
                mat[i][j] = 0;
            }
        }
    }
 
    public void printSudoku()
    {
        int f;
        for (int i = 0; i<n; i++)
        {
            for (int j = 0; j<n; j++){
                System.out.print(mat[i][j] + " ");
                if((j+1)%3==0 && j != 0 && j != 8){
                    System.out.print("| " );                
                }
            }
            System.out.println();
            f = i+1;
 
            if(i != 0 && (i+1)%3==0 && f != 9){
                System.out.println("---------------------");
            }
        }
        System.out.println();
    }
    public int countentries(){
        int count = 0;
        for (int i = 0; i<n; i++)
        {
            for (int j = 0; j<n; j++){
                if(mat[i][j] == 0){
                    count++;
                }
            }
        }
        return count;
    }

    static boolean isSafe(int[][] grid, int row, int col, int num)
    {
 
        // Check if we find the same num
        // in the similar row , we
        // return false
        for (int x = 0; x <= 8; x++)
            if (grid[row][x] == num)
                return false;
 
        // Check if we find the same num
        // in the similar column ,
        // we return false
        for (int x = 0; x <= 8; x++)
            if (grid[x][col] == num)
                return false;
 
        // Check if we find the same num
        // in the particular 3*3
        // matrix, we return false
        int startRow = row - row % 3, startCol
                                      = col - col % 3;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (grid[i + startRow][j + startCol] == num)
                    return false;
 
        return true;
    }
    public static void main(String[] args)
    {
        int[][] ans = new int[9][9];
        for(int i = 0; i<9; i++){
            for(int j = 0; j<9; j++){
                ans[i][j] = mat[i][j];
            }
        }
        int n = 9, fill = 20;
        MAin sudoku = new MAin(n, fill);
        sudoku.fillValues();
        sudoku.printSudoku();
        int c = sudoku.countentries();
        int counter = 0;
        System.out.println("the positions having zeroes are free spots to enter the numbers to the puzzle!");
        Scanner sc = new Scanner(System.in);
        int row, column, element;
        
        while(counter < c){
            if(counter == 0)
            {
                System.out.println("enter the row number of position you wish to enter the element: ");
                row = sc.nextInt();
                System.out.println("enter the column number of position you wish to enter the element: ");
                column = sc.nextInt();
                System.out.println("enter the element: ");
                element = sc.nextInt();
                mat[row - 1][column - 1] = element;
                counter = counter + 1;
                sudoku.printSudoku();
            }
            System.out.println("enter 1 if you wanna undo last change or 2 if you wanna add elements 3 to check result 4 to view the answer: ");
            int m = sc.nextInt();
              
                switch(m)
                {
                    case 1:
                    System.out.print("enter the row: ");
                    row = sc.nextInt();
                    System.out.println("enter the column number: ");
                    column = sc.nextInt();
                    mat[row - 1][column - 1] = 0;
                    sudoku.printSudoku();
                    counter = counter - 1;
                    break;
                               
                    case 2:  
                    System.out.print("enter the row: ");
                    row = sc.nextInt();
                    System.out.println("enter the column number: ");
                    column = sc.nextInt();
                    System.out.println("enter the element: ");
                    element = sc.nextInt();
                    mat[row - 1][column - 1] = element;
                    sudoku.printSudoku();
                    counter = counter + 1;
                    break;

                    case 3:
                    if (ch.isValidSudoku(mat))
                    {
                        System.out.println("Valid");
                    }
                    else
                    {
                        System.out.println("Not Valid");
                    }
                    break;

                    case 4:

	            	if (solveSudoku(ans, 0, 0))
			            print(grid);
		            else
			            System.out.println("No Solution exists");
                    break;
                }
            
            
        }
    }
}