import { neighbors, readCsv, readGrid, readInts, readLines, readFile } from '../util/load-csv.mjs';



(async () => {
    let lines
    let sum = 0

    try {
        lines = await readLines('day3.txt')
    } catch (error) {
        console.error(error);
    }
    const regex = "mul\\(\\d+,\\d+\\)"
    for (const line of lines) {
        const mults = line.matchAll(regex);
        for (const mult of mults) {
            const txt = mult[0].slice(4, -1)
            const ints = readInts(txt, ',')
            console.log(ints)
            sum += ints[0] * ints[1]
        }
    }
    console.log(sum)

    let sum2 = 0
    let file

    try {
        file = await readFile('day3.txt')
    } catch (error) {
        console.error(error);
    }

    const regex_break = /\r?\n|\r/g
    const regex_rm = /don't\(\).*?do\(\)/gm
    file = file.replaceAll(regex_break, '');
    let mults = file.replaceAll(regex_rm, '');
    console.log(mults)
    mults = mults.matchAll(regex);
    for (const mult of mults) {
        const txt = mult[0].slice(4, -1)
        const ints = readInts(txt, ',')
        console.log(ints)
        sum2 += ints[0] * ints[1]
    }
    console.log(sum2)

})();


