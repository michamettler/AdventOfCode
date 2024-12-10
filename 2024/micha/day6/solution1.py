class Field:
    def __init__(self, symbol, visited=False):
        self.symbol = symbol
        self.visited = visited


def evalDirection(symbol):
    match symbol:
        case "^":
            return 0, -1
        case ">":
            return 1, 0
        case "<":
            return -1, 0
        case "v":
            return 0, 1
    print("error")


def turn(symbol):
    match symbol:
        case "^":
            return ">"
        case ">":
            return "v"
        case "v":
            return "<"
        case "<":
            return "^"
    print("error")


def inRange(A, x, y):
    return 0 <= y < len(A) and 0 <= x < len(A[0])


with open("2024/micha/day6/input.txt") as file:
    locations = file.readlines()

A = []
startPos = None
dir = None
for y, line in enumerate(locations):
    v = [Field(symbol=char) for char in line.strip()]
    for x, field in enumerate(v):
        if field.symbol in {"^", ">", "<", "v"}:
            startPos = (x, y)
            dir = field.symbol
            field.symbol = '.'
    A.append(v)

cntr = 1
x, y = startPos
x_delta, y_delta = evalDirection(dir)
inside = inRange(A, x + x_delta, y + y_delta)

while inside:
    if not A[y + y_delta][x + x_delta].symbol == "#":
        while inside and A[y + y_delta][x + x_delta].symbol == ".":
            if not A[y][x].visited:
                A[y][x].visited = True
                cntr += 1
            x, y = x + x_delta, y + y_delta
            inside = inRange(A, x + x_delta, y + y_delta)
    dir = turn(dir)
    x_delta, y_delta = evalDirection(dir)
print(cntr)
