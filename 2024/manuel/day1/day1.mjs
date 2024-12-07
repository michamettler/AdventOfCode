import { neighbors, readCsv, readGrid, readInts, readLines } from '../util/load-csv.mjs';



(async () => {
    const list1 = []
    const list2 = []
    let diff = 0
    try {

        const lines = await readLines('./day1.txt');
        console.log(lines)
        for (const line of lines) {
            const numbers = readInts(line, "   ")
            if (numbers.length > 1) {
                console.log(numbers)
                list1.push(numbers[0])
                list2.push(numbers[1])
            }
        }

        list1.sort()
        list2.sort()
        for (let i = 0; i < list1.length; i++) {
            const d = Math.abs(list1[i] - list2[i])
            diff += d
        }
        console.log("quiz 1")
        console.log(diff)

        let sim = 0

        for (const a of list1) {
            let amount = list2.filter((b) => a === b).length
            sim += amount * a;

        }
        console.log("quiz 2")
        console.log(sim)


    } catch (error) {
        console.error(error);
    }
})();
