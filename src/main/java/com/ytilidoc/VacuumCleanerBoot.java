package com.ytilidoc;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 *
 */
public class VacuumCleanerBoot {

    private static String DIRT = ".";
    private static String X = "X";
    private static Position TOP_LEFT = new Position(0,0);
    private static Direction STARTING_DIRECTION = Direction.RIGHT;

    public static void main(String[] args) {
        isTrue("['...X..','....XX','..X...'] == 6",
                (clean(List.of(
                        "...X..",
                        "....XX",
                        "..X..."
                )) == 6));

        isTrue("['....X..','X......','.....X.','.......'] == 15",
                (clean(List.of(
                        "....X..",
                        "X......",
                        ".....X.",
                        "......."
                )) == 15));

        isTrue("['...X.','.X..X','X...X','..X..'] == 9",
                (clean(List.of(
                        "...X.",
                        ".X..X",
                        "X...X",
                        "..X.."
                )) == 9));

        isTrue("['.'] == 1",
                clean(List.of(
                        "."
                )) == 1);

        isTrue("['.'] == 1",
                clean(List.of(
                        "."
                )) == 1);

        isTrue("['......'] == 6",
                clean(List.of(
                        "......"
                )) == 6);

        isTrue("['.','.','.','.'] == 4",
                clean(List.of(
                        ".",".",".","."
                )) == 4);

        isTrue("['...','.X.','...'] == 8",
                clean(List.of(
                        "...",
                        ".X.",
                        "..."
                )) == 8);
    }

    private static int clean(List<String> rows) {
        if (rows.isEmpty()) {
            return 0;
        }
        if (rows.size() == 1) {
            int length = rows.get(0).length();
            if (length == 0) {
                return 0;
            }
            if (length == 1) {
                return 1;
            }
        }

        Position bottomRight = bottomRight(rows);
        List<Square> squares = squares(rows);
        List<CleanedSquare> cleanedSquares = new ArrayList<>();
        boolean cleaning = true;
        Position currentPosition = TOP_LEFT;
        Direction currentDirection = STARTING_DIRECTION;
        Position nextPosition;
        while (cleaning) {
            nextPosition = next(currentPosition, currentDirection);
            if (canMoveTo(nextPosition, squares, TOP_LEFT, bottomRight)) {
                cleanedSquares.add(new CleanedSquare(currentPosition, currentDirection));
                currentPosition = nextPosition;
            } else {
                currentDirection = currentDirection.turn();
            }
            cleaning = keepCleaning(currentPosition, currentDirection, cleanedSquares);
        }
        return cleanedSquares.stream().
                map(CleanedSquare::position).
                collect(Collectors.toSet()).size();
    }

    private static boolean keepCleaning(Position currentPosition, Direction currentDirection, List<CleanedSquare> cleanedSquares) {
        return cleanedSquares.stream().
                noneMatch(cleanedSquare -> cleanedSquare.position().equals(currentPosition) &&
                        cleanedSquare.direction().equals(currentDirection));
    }

    private static Position bottomRight(List<String> rows) {
        return new Position(rows.size() - 1, rows.get(0).length() - 1);
    }

    private static List<Square> squares(List<String> rows) {
        List<Square> squares = new ArrayList<>();
        String row;
        for (int i = 0; i < rows.size(); i++) {
            row = rows.get(i);
            for (int j = 0; j < row.length(); j++) {
                squares.add(new Square(new Position(i, j), ((Character)row.charAt(j)).toString()));
            }
        }
        return squares;
    }

    private static Position next(Position position, Direction direction) {
        return direction.next(position);
    }

    private static boolean canMoveTo(Position position, List<Square> squares, Position topLeft, Position bottomRight) {
        if (outOfBoundaries(position, topLeft, bottomRight)) {
            return false;
        }
        Square square = squares.stream().
                filter(s -> s.position().equals(position)).
                findFirst().
                orElseThrow();
        return square.content().equals(DIRT);
    }

    private static boolean outOfBoundaries(Position position, Position topLeft, Position bottomRight) {
        return position.col() < topLeft.col() || position.col() > bottomRight.col() ||
                position.row() < topLeft.row() || position.row() > bottomRight.row();
    }

    private static record CleanedSquare(Position position, Direction direction) {
    }
    private static record Square(Position position, String content) {
    }
    private static record Position (int row, int col) {
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Position position = (Position) o;
            return row == position.row && col == position.col;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col);
        }
    }

    private static enum Direction {
        LEFT() {
            @Override
            public Position next(Position position) {
                return new Position(position.row(), position.col() - 1);
            }
            @Override
            public Direction turn() {
                return UP;
            }
        },
        RIGHT {
            public Position next(Position position) {
                return new Position(position.row(), position.col() + 1);
            }
            @Override
            public Direction turn() {
                return DOWN;
            }
        },
        UP {
            public Position next(Position position) {
                return new Position(position.row() - 1, position.col());
            }
            @Override
            public Direction turn() {
                return RIGHT;
            }
        },
        DOWN {
            public Position next(Position position) {
                return new Position(position.row() + 1, position.col());
            }
            @Override
            public Direction turn() {
                return LEFT;
            }
        };

        public abstract Position next(Position position);
        public abstract Direction turn();
    }

    private static void isTrue(String expression, boolean trueValue) {
        if (!trueValue) {
            throw new RuntimeException("NOT TRUE expression: " + expression);
        }
    }
}
