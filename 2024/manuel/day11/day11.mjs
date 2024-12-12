import { readGrid, neighbors, readInts, readFile } from '../util/load-csv.mjs';


(async () => {

    let input = await readFile('./day11.txt')
    let rocks = readInts(input, ' ')
    console.log(part1(rocks))
    rocks = readInts(input, ' ')
    console.log(part2(rocks))

})();

function part1(stones) {
    console.log(stones)
    for (let i = 0; i < 25; i++) {
        let newarr = []
        for (const stone of stones) {
            if (stone == 0) {
                newarr.push(1)
            } else if (stone.toString().length % 2 == 0) {
                const mid = Math.floor(stone.toString().length / 2); // Find the middle index
                const part1 = stone.toString().slice(0, mid);
                const part2 = stone.toString().slice(mid);

                const result1 = parseInt(part1, 10) || 0; // Default to 0 if parseInt returns NaN
                const result2 = parseInt(part2, 10) || 0;
                newarr.push(result1)
                newarr.push(result2)
            } else {
                newarr.push(stone * 2024)
            }
        }
        stones = newarr
    }


    return stones.length
}


function part2(stonesstring) {
    let stones = new Map()
    stonesstring.forEach(element => {
        stones.set(element, stonesstring.filter((el) => el === element).length)
    });
    console.log(stones)

    for (let i = 0; i < 75; i++) {
        stones = countStone(stones)
    }

    console.log(stones)
    let sum = 0
    for (const [stone, count] of stones.entries()) {
        sum += count
    }
    return sum
}

function countStone(stones) {
    const newStones = new Map;

    for (const [stone, count] of stones.entries()) {
        if (stone === 0) {
            newStones.set(1, (newStones.get(1) || 0) + count)
        } else if (stone.toString().length % 2 === 0) {
            let stoneString = stone.toString()
            const mid = Math.floor(stoneString.length / 2); // Find the middle index
            const rh = parseInt(stoneString.slice(0, mid))
            const lh = parseInt(stoneString.slice(mid))

            newStones.set(lh, (newStones.get(lh) || 0) + count)
            newStones.set(rh, (newStones.get(rh) || 0) + count)
        } else {
            let stoneInt = parseInt(stone)
            const newStone = stoneInt * 2024;
            newStones.set(newStone, (newStones.get(newStone) || 0) + count)
        }
    }

    return newStones;
}


