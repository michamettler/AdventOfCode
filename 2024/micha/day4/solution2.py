with open('2024/micha/day4/input.txt') as file:
    locations = file.readlines()

def check1(A, i, j):
    if checkRange(A, i, j):
        if A[i][j] == 'A' and A[i - 1][j - 1] == 'M' and A[i - 1][j + 1] == 'S' and (
            A[i + 1][j - 1] == 'M' and A[i + 1][j + 1] == 'S'
        ):
            return 1
    return 0

def check2(A, i, j):
    if checkRange(A, i, j):
        if A[i][j] == 'A' and A[i - 1][j - 1] == 'S' and A[i - 1][j + 1] == 'S' and (
            A[i + 1][j - 1] == 'M' and A[i + 1][j + 1] == 'M'
        ):
            return 1
    return 0

def check3(A, i, j):
    if checkRange(A, i, j):
        if A[i][j] == 'A' and A[i - 1][j - 1] == 'S' and A[i - 1][j + 1] == 'M' and (
            A[i + 1][j - 1] == 'S' and A[i + 1][j + 1] == 'M'
        ):
            return 1
    return 0

def check4(A, i, j):
    if checkRange(A, i, j):
        if A[i][j] == 'A' and A[i - 1][j - 1] == 'M' and A[i - 1][j + 1] == 'M' and (
            A[i + 1][j - 1] == 'S' and A[i + 1][j + 1] == 'S'
        ):
            return 1
    return 0

def checkRange(A, i, j):
    return 0 <= i - 1 and i + 1 < len(A) and 0 <= j - 1 and j + 1 < len(A[i - 1]) and j + 1 < len(A[i + 1])

A = []
for line in locations:
    v = list(line.strip())
    A.append(v)

cntr = 0
for i in range(len(A)):
    for j in range(len(A[i])):
        cntr += check1(A, i, j)
        cntr += check2(A, i, j)
        cntr += check3(A, i, j)
        cntr += check4(A, i, j)

print(cntr)
