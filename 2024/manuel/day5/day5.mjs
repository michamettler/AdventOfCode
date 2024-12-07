import { neighbors, readFile, readGrid, readInts, readLines } from '../util/load-csv.mjs';



(async () => {
    let lines = await readLines('./day5.tst')
    let result = readManual(lines)
    console.log(result)
    let result2 = readManual2(lines)
    console.log(result2)
    let file = await readFile('./day5.tst')
    let result3 = part2(file)
    console.log(result3)
})();


function readManual(lines) {
    let ordering = []
    let updates = []
    for (const line of lines) {
        if (line.includes(',')) {
            const nums = readInts(line, ',')
            updates.push(nums);

        } else if (line.includes('|')) {
            const nums = readInts(line, '|')
            ordering.push({
                before: nums[0],
                after: nums[1]
            })
        }
    }
    console.log("ordering:", ordering)
    console.log("updates:", updates)

    let sum = 0
    for (const list of updates) {

        let validatedValues = []
        let correct = true
        for (const val of list) {
            const rules = ordering.filter((r) => r.before === val)
            const brokenrules = rules.filter((r) => validatedValues.filter((v) => v === r.after).length > 0)
            if (brokenrules.length > 0) {
                correct = false
            }
            validatedValues.push(val)

        }
        if (correct) {
            const middleIndex = Math.floor(list.length / 2);
            sum += list[middleIndex]
        }
    }

    return sum

}

function readManual2(lines) {
    let ordering = []
    let updates = []
    for (const line of lines) {
        if (line.includes(',')) {
            const nums = readInts(line, ',')
            updates.push(nums);

        } else if (line.includes('|')) {
            const nums = readInts(line, '|')
            ordering.push({
                before: nums[0],
                after: nums[1]
            })
        }
    }

    let sum = 0
    for (const list of updates) {

        let validatedValues = []
        let correct = true
        for (const val of list) {
            const rules = ordering.filter((r) => r.before === val)
            const brokenrules = rules.filter((r) => validatedValues.filter((v) => v === r.after).length > 0)
            if (brokenrules.length > 0) {
                correct = false
            }
            validatedValues.push(val)

        }
        if (!correct) {
            const newOrder = reorderArray(list, ordering)
            const middleIndex = Math.floor(newOrder.length / 2);
            sum += list[middleIndex]
        }
    }

    return sum

}
function part2(input) {
    let [rules, updates] = input.split("\n\n");
    let bad = [];
    rules = new Set(rules.split("\n"));
    updates = updates.split("\n").map(update => update.split(",").map(Number));
    for (const update of updates) {
        if (correct(update, rules) !== true) bad.push(update);
    }

    let sum = 0;
    for (const update of bad) {
        let fix;
        do {
            fix = correct(update, rules);
            if (typeof fix === "function") fix();
        } while (fix !== true);
        sum += update[(update.length - 1) / 2];
    }
    return sum;
}

function reorderArray(array, ordering) {
    const rulesByVal = new Map()
    const affected = new Map()

    array.forEach(val => {
        const rules = ordering.filter((r) => r.before === val)
        rulesByVal.set(val, rules)
    });

    array.forEach(val => {
        const rules = rulesByVal.get(val)
        const affectedVals = array.filter((val2) => {
            return rules.filter((r) => r.after === val2).length > 0
        })
        affected.set(val, affectedVals)
    })

    const priorityMap = generatePriorityMap(affected);
    const sortedArray = array.sort((a, b) => {
        const priorityA = priorityMap.get(a) ?? Infinity; // Default to Infinity for unknown values
        const priorityB = priorityMap.get(b) ?? Infinity;
        return priorityA - priorityB;
    });
    console.log(sortedArray)
    return sortedArray


}
// Function to generate priority order
function generatePriorityMap(orderMap) {
    const priority = new Map();
    let currentPriority = 0;

    // Recursive function to set priorities
    function setPriority(key) {
        if (!priority.has(key)) {
            priority.set(key, currentPriority++);
            const dependencies = orderMap.get(key) || [];
            dependencies.forEach(setPriority);
        }
    }

    // Set priorities for all keys in the map
    orderMap.forEach((_, key) => setPriority(key));

    return priority;
}

