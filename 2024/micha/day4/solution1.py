with open('2024/micha/day4/input.txt') as file:
    locations = file.readlines()

def checkHorizontally(A, i, j, multiplier):
    if (j + multiplier*3) < len(A[i]) and j + multiplier*3 >= 0:
        if A[i][j] == 'X' and A[i][j + multiplier*1] == 'M' and (
                A[i][j + multiplier*2] == 'A' and A[i][j + multiplier*3] == 'S'):
            return 1
    return 0

def checkVertically(A, i, j, multiplier):
    if (i + multiplier*3) < len(A) and i + multiplier*3 >= 0:
        if A[i][j] == 'X' and A[i + multiplier*1][j] == 'M' and (
            A[i + multiplier*2][j] == 'A' and A[i + multiplier*3][j] == 'S'):
            return 1
    return 0

def checkDiagonalRising(A, i, j, multiplier):
    if (i + multiplier*3) < len(A) and (j - multiplier*3) < len(A[i]) and i + multiplier*3 >= 0 and j - multiplier*3 >= 0:
        if A[i][j] == 'X' and A[i + multiplier*1][j - multiplier*1] == 'M' and (
            A[i + multiplier*2][j - multiplier*2] == 'A' and A[i + multiplier*3][j - multiplier*3] == 'S'):
            return 1
    return 0

def checkDiagonalFalling(A, i, j, multiplier):
    if (i + multiplier*3) < len(A) and (j + multiplier*3) < len(A[i]) and i + multiplier*3 >= 0 and j + multiplier*3 >= 0:
        if A[i][j] == 'X' and A[i + multiplier*1][j + multiplier*1] == 'M' and (
            A[i + multiplier*2][j + multiplier*2] == 'A' and A[i + multiplier*3][j + multiplier*3] == 'S'):
            return 1
    return 0

A = []
for line in locations:
    v = list(line.strip())
    A.append(v)

cntr = 0
for i in range(len(A)):
    for j in range(len(A[i])):
        cntr += checkHorizontally(A, i, j, 1)
        cntr += checkHorizontally(A, i, j, -1)
        
        cntr += checkVertically(A, i, j, 1)
        cntr += checkVertically(A, i, j, -1)
        
        cntr += checkDiagonalRising(A, i, j, 1)
        cntr += checkDiagonalRising(A, i, j, -1)
        
        cntr += checkDiagonalFalling(A, i, j, 1)
        cntr += checkDiagonalFalling(A, i, j, -1)

print(cntr)
