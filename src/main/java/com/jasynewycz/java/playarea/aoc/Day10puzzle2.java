package com.jasynewycz.java.playarea.aoc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class Day10puzzle2 {


    static class Node {

        Tile tile;
        Node forward;
        Node backward;
        Point location;

        public Node(Tile tile, Node forward, Node backward, Point location) {
            this.tile = tile;
            this.forward = forward;
            this.backward = backward;
            this.location = location;
        }
    }


    record Point(int x, int y) {

    }

    enum Status {
        NOT_ONPATH, ON_PATH, OUTSIDE_PATH;

    }

    enum Tile {
        START('S'), VERT('|'), HORI('-'), TOP_LEFT('F'), TOP_RIGHT('7'), BOTTOM_LEFT('L'), BOTTOM_RIGHT('J'), EMPTY('.');

        char value;
        Tile(char value) {
            this.value=value;
        }

        @Override
        public String toString() {
            return ""+value;
        }
    }

    public static void main(String[] args) throws IOException {

        List<String> lines = Files.readAllLines(Path.of("./src/main/resources/com/jasynewycz/java/playarea/aoc/day10puzzle1realdata.txt"));

        Tile[][] data = new Tile[lines.size()][lines.get(0).length()];
        Status[][] status = new Status[lines.size()][lines.get(0).length()];

        int x = 0, y = 0;

        Point start = new Point(0, 0);

        for(String line : lines) {
            for(y = 0; y < line.length(); y++) {
                data[x][y] = parseChar(line.charAt(y));
                if (data[x][y] == Tile.START) {
                    start = new Point(x, y);
                }
            }
            x++;
        }

        int steps = 0;

        Node currentNode = new Node(data[start.x][start.y], null, null, new Point(start.x, start.y));
        status[currentNode.location.x][currentNode.location.y] = Status.ON_PATH;

        do {

            Point nextPoint = getNextTile(data, currentNode);
            Node nextNode = new Node(data[nextPoint.x][nextPoint.y], null, currentNode, nextPoint);
            currentNode.forward = nextNode;
            nextNode.backward = currentNode;
            currentNode = nextNode;
            steps++;
            status[currentNode.location.x][currentNode.location.y] = Status.ON_PATH;

        } while (currentNode.tile != Tile.START);

        System.out.println(start.x + "-" + start.y);
        System.out.println(steps);
        System.out.println(steps / 2);

        int enclosedCellCount = 0;

        // abandoned below approach when realised where I went wrong with the alternating in/out flag approach
/*
        // find all cells outside and on path and mark in grid
        for (int i = 0; i < status.length; i++) {
            int j = 0;
            for (; j < status[i].length; j++) {
                if(status[i][j] != Status.ON_PATH) {
                    status[i][j] = Status.OUTSIDE_PATH;
                }
                if(status[i][j] == Status.ON_PATH) {
                    break;
                }
            }

            for(int k = status[i].length-1; k > j; k--) {
                if(status[i][k] != Status.ON_PATH) {
                    status[i][k] = Status.OUTSIDE_PATH;
                }
                if(status[i][k] == Status.ON_PATH) {
                    break;
                }
            }
        }

        for (int i = 0; i < status[0].length; i++) {
            int j = 0;
            for (; j < status.length; j++) {
                if(status[j][i] != Status.ON_PATH) {
                    status[j][i] = Status.OUTSIDE_PATH;
                }
                if(status[j][i] == Status.ON_PATH) {
                    break;
                }
            }

            for(int k = status.length-1; k > j; k--) {
                if(status[k][i] != Status.ON_PATH) {
                    status[k][i] = Status.OUTSIDE_PATH;
                }
                if(status[k][i] == Status.ON_PATH) {
                    break;
                }
            }
        }
*/

        data[start.x][start.y] = Tile.TOP_RIGHT;

        for (int i = 0; i < status.length; i++) {
            boolean in = false;
            Tile previousSwitch = null;
            for(int j = 0; j < status[i].length; j++) {
                boolean switchInOut = false;
                if(status[i][j] == Status.ON_PATH) {
                    if(data[i][j] != Tile.HORI) {
                        if (data[i][j] == Tile.BOTTOM_RIGHT && previousSwitch == Tile.TOP_LEFT) {

                        } else if (data[i][j] == Tile.TOP_RIGHT && previousSwitch == Tile.BOTTOM_LEFT) {

                        } else {
                            switchInOut = true;
                            previousSwitch = data[i][j];
                        }
                    }
                    if(switchInOut) {
                        in = !in;
                    }
                }
                if(status[i][j] != Status.ON_PATH && status[i][j] != Status.OUTSIDE_PATH) {
                    if (in) {
                        enclosedCellCount++;
                    } else {
                        status[i][j] = Status.OUTSIDE_PATH;
                    }
                }
            }
        }
        printGrid(status, data);

        System.out.println(enclosedCellCount);
    }

    static Point getNextTile(Tile[][] data, Node current) {

        int newX = current.location.x;
        int newY = current.location.y;

        switch(current.tile) {

            case VERT:
                newX = current.location.x - 1;
                if (current.backward != null && newX == current.backward.location.x) {
                    newX = current.location.x + 1;
                }
                break;
            case HORI:
                newY = current.location.y - 1;
                if (current.backward != null && newY == current.backward.location.y) {
                    newY = current.location.y + 1;
                }
                break;
            case TOP_LEFT:
                newY = current.location.y + 1;
                if (current.backward != null && newY == current.backward.location.y) {
                    newY = current.location.y;
                    newX = current.location.x + 1;
                }
                break;
            case TOP_RIGHT:
                newY = current.location.y - 1;
                if (current.backward != null && newY == current.backward.location.y) {
                    newY = current.location.y;
                    newX = current.location.x + 1;
                }
                break;
            case BOTTOM_LEFT:
                newY = current.location.y + 1;
                if (current.backward != null && newY == current.backward.location.y) {
                    newY = current.location.y;
                    newX = current.location.x - 1;
                }
                break;
            case BOTTOM_RIGHT:
                newY = current.location.y - 1;
                if (current.backward != null && newY == current.backward.location.y) {
                    newY = current.location.y;
                    newX = current.location.x - 1;
                }
                break;
            case START:
                // look in all directions until we find a tile that is directed at S
                newY = current.location.y-1;
                if(newY >= 0 && (data[newX][newY] == Tile.TOP_LEFT || data[newX][newY] == Tile.BOTTOM_LEFT || data[newX][newY] == Tile.HORI)) {
                    break;
                } else {
                    newY = current.location.y+1;
                    if(newY < data[newX].length && (data[newX][newY] == Tile.TOP_RIGHT || data[newX][newY] == Tile.BOTTOM_RIGHT || data[newX][newY] == Tile.HORI)) {
                        break;
                    }
                }

                newY = current.location.y;
                newX = current.location.x-1;
                if(newX >= 0 && (data[newX][newY] == Tile.BOTTOM_LEFT || data[newX][newY] == Tile.BOTTOM_RIGHT || data[newX][newY] == Tile.VERT)) {
                    break;
                } else {
                    newX = current.location.x+1;
                    if(newX < data.length && (data[newX][newY] == Tile.BOTTOM_LEFT || data[newX][newY] == Tile.BOTTOM_RIGHT || data[newX][newY] == Tile.VERT)) {
                        break;
                    }
                }

        }
        return new Point(newX, newY);
    }

    public static Tile parseChar(char c) {

        switch(c) {
            case 'S': return Tile.START;
            case '|': return Tile.VERT;
            case '-': return Tile.HORI;
            case 'F': return Tile.TOP_LEFT;
            case '7': return Tile.TOP_RIGHT;
            case 'L': return Tile.BOTTOM_LEFT;
            case 'J': return Tile.BOTTOM_RIGHT;
            case '.': return Tile.EMPTY;
        }
        return null;
    }

    static void printGrid(Status[][] states, Tile[][] tiles) {

        for(int x = 0; x < states.length; x++) {
            System.out.print(x + "\t");
            for(int y = 0; y < states[x].length; y++) {
                if(states[x][y] == Status.ON_PATH) {
                    System.out.print(tiles[x][y]);
                } else if(states[x][y] == Status.OUTSIDE_PATH) {
                    System.out.print(".");
                } else {
                    System.out.print('\u2588');
                }
            }
            System.out.println();
        }

    }

}
