import numpy as np

with open('input.txt') as file:
    locations = file.readlines()

A = []
for line in locations:
    v = list(map(int, line.strip().split('   ')))
    A.append(v)

A_sorted = []
for v in np.array(A).T:
    v = np.sort(v)
    A_sorted.append(v)

distance = 0
for v in np.array(A_sorted).T:
    distance += np.abs(v[0]-v[1])

print(distance)
