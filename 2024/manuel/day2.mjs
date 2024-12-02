import { neighbors, readCsv, readGrid, readInts, readLines } from './util/load-csv.mjs';



(async () => {
    let lines
    try {
        lines = await readLines('day2.txt')
    } catch (error) {
        console.error(error);
    }
    let reports = []

    for (const report of lines) {
        let numbers = readInts(report, ' ')
        if (numbers.length > 1) {
            reports.push(numbers)
            console.log(numbers)
        }
    }

    const safeReportsCount = reports.filter((r) => {
        let oneValid = false
        for (let i = 0; i < r.length; i++) {
            const newCombination = [...r.slice(0, i), ...r.slice(i + 1)];

            oneValid = oneValid | isSafe(newCombination)
        }
        return oneValid
    }).length;
    console.log(safeReportsCount)
})();


function isSafe(report) {
    let isIncreasing = true;
    let isDecreasing = true;

    for (let i = 0; i < report.length - 1; i++) {
        const difference = report[i + 1] - report[i];
        const dist = Math.abs(difference)
        if (dist < 1 || dist > 3) return false;
        if (difference > 0) isDecreasing = false;
        if (difference < 0) isIncreasing = false;
    }

    return isIncreasing || isDecreasing;
}
