with open('input.txt') as file:
    locations = file.readlines()

cntr = 0
for line in locations:
    levels = list(map(int, line.strip().split(' ')))
    increasing = levels[1] > levels[0]
    error = False

    for i in range(1, len(levels)):
        modeValid = (levels[i] > levels[i - 1] and increasing) or (levels[i] < levels[i - 1] and not increasing)
        if not (1 <= abs(levels[i] - levels[i - 1]) <= 3) or not modeValid:
            error = True
            break
    if not error:
        cntr += 1

print(cntr)


