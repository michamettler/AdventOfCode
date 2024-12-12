with open("2024/micha/day7/input.txt") as file:
    lines = file.readlines()

operations = []

for line in lines:
    split = list(line.split(':'))
    operands = [int(item.strip()) for item in split[1].split(' ') if item]
    operations.append([int(split[0]), operands])

cntr = 0
for operation in operations:
    result = operation[0]
    operands = operation[1]
   
    treeLevel = [operands[0]]
    treeLevelNext = []
    for i in range(1, len(operands)):
        for node in treeLevel:
            nodeLeft = node + operands[i]
            nodeRight = node * operands[i]
            
            treeLevelNext.append(nodeRight)
            treeLevelNext.append(nodeLeft)
        treeLevel = treeLevelNext
        treeLevelNext = []
    if result in treeLevel:
        cntr += result

print(cntr)                    