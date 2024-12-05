with open('2024/micha/day5/testInput.txt') as file:
    locations = file.readlines()

# part two - not very efficient, but who cares :-)

def isValid(rules, pr):
    for i, page in enumerate(pr):
        if page in rules:
            for toCheck in rules[page]:
                if toCheck in pr and not pr.index(toCheck) > i:
                    return False, i, pr.index(toCheck)
    return True, -1, -1


rules = {}
prints = []

for line in locations:
    line = line.strip()
    if ('|' in line):
        v = line.split('|')
        if v[0] not in rules:
            rules[v[0]] = [v[1]]
        else:
            rules[v[0]].append(v[1])
    elif ',' in line:
        prints.append(list(line.split(',')))

cntr = 0
for pr in prints:
    valid, a, b = isValid(rules, pr)
    if not valid:
        while not valid:
            pr[b], pr[a] = pr[a], pr[b]
            valid, a, b = isValid(rules, pr)
        cntr += int(pr[len(pr)//2])
print(cntr)