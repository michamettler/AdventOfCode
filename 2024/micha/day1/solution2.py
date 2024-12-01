import numpy as np

with open('input.txt') as file:
    locations = file.readlines()

A = []
for line in locations:
    v = list(map(int, line.strip().split('   ')))
    A.append(v)

A = np.array(A)
similarities = []
for v in A:
    cntr = 0
    for sec in A.T[1]:
        if sec == v[0]:
            cntr += 1
    similarities.append(v[0]*cntr)

score = np.sum(similarities)
print(score)
