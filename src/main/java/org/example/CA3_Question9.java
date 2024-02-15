package org.example;

import java.util.Stack;

/**
 * Name:
 * Class Group:
 */

/*
Direction enum used to indicate direction.
 */
enum DIRECTION {NORTH, SOUTH,EAST,WEST};

public class CA3_Question9
{
    private int[][] maze; // this is the maze
    private int ex, ey; // ex and ey stand for exit x and exit y respectively
    private boolean[][] visited;
    private Stack<int[]> path; // the path that one is taking

    public CA3_Question9(int[][] maze, int ex, int ey) {
        this.maze = maze; this.ex = ex; this.ey = ey;

        this.visited = new boolean[maze.length][maze[0].length];
        this.path = new Stack<>();
    }

    public void solve(int x, int y, DIRECTION dir)
    {
        if (explore(x, y)) {
            System.out.println("A path has been found!");
            while (!path.isEmpty()) {
                int[] position = path.pop();
                System.out.print("(" + position[0] + ", " + position[1] + "), ");
            }

        }
        else {
            System.out.println(" No path has been found.");
        }
    }

    private boolean explore(int x, int y) {
        if (x < 0 || x >= maze.length || y < 0 || y >= maze[0].length || maze[x][y] == 1 || visited[x][y]) {
            return false;
        }

        visited[x][y] = true;
        path.push(new int[]{x, y});

        if (x == ex && y == ey) {
            return true;
        }

        if (explore(x, y + 1)) { // East
            return true;
        }
        if (explore(x, y - 1)) { // West
            return true;
        }
        if (explore(x + 1, y)) { // South
            return true;
        }
        if (explore(x - 1, y)) { // North
            return true;
        }
        path.pop();
        return false;
    }

    public static void main(String[] args) {
        int[][] maze = {
                {0, 1, 0, 0, 0},
                {0, 1, 0, 1, 0},
                {0, 0, 0, 1, 0},
                {1, 1, 1, 1, 0},
                {0, 0, 0, 0, 0}
        };
        CA3_Question9 solver = new CA3_Question9(maze, 4, 4);
        solver.solve(0, 0, DIRECTION.EAST);
    }
}
