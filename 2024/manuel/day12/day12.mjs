import { getNeighbors, readFile, inBounds } from '../util/load-csv.mjs';


(async () => {
    let input = await readFile('./day12.txt')
    input = input.split("\n")
    if (input[input.length - 1] === "") {
        input.pop();
    }
    let total = 0

    let visited = new Set();
    for (let y = 0; y < input.length; y++) {
        for (let x = 0; x < input[0].length; x++) {
            if (!visited.has(`${y},${x}`)) {
                total += part1([y, x], input, visited);
            }
        }
    }

    console.log(total)
    total = 0

    visited = new Set();
    for (let y = 0; y < input.length; y++) {
        for (let x = 0; x < input[0].length; x++) {
            if (!visited.has(`${y},${x}`)) {
                total += part2([y, x], input, visited);
            }
        }
    }

    console.log(total)

})();


function part1(coors, input, visited) {
    let plotArea = 0;

    let edges = new Set();
    let edgeCount = 0;

    const val = input[coors[0]][coors[1]];

    const queue = [coors];

    while (queue.length > 0) {
        const current = queue.shift();
        const key = current.join(",");

        if (visited.has(key)) {
            continue;
        } else {
            visited.add(key);
        }

        plotArea += 1;

        let neighbors = getNeighbors(current);
        for (let polarity = 0; polarity < neighbors.length; polarity++) {
            let neighbor = neighbors[polarity];
            if (!inBounds(neighbor, input) || input[neighbor[0]][neighbor[1]] !== val) {
                edgeCount += 1;
                edges.add(`${polarity},${neighbor[0]},${neighbor[1]}`);
            } else {
                queue.push(neighbor);
            }

        }
    }

    const price = plotArea * edges.size;
    console.log("area", plotArea);
    console.log("edges", edgeCount);
    console.log(edges.size)

    return price;
}

function part2(coors, input, visited) {
    let plotArea = 0;

    let edges = new Set();
    let edgeCount = 0;

    const val = input[coors[0]][coors[1]];

    const queue = [coors];

    while (queue.length > 0) {
        const current = queue.shift();
        const key = current.join(",");

        if (visited.has(key)) {
            continue;
        } else {
            visited.add(key);
        }

        plotArea += 1;

        let neighbors = getNeighbors(current);
        for (let polarity = 0; polarity < neighbors.length; polarity++) {
            let neighbor = neighbors[polarity];
            if (!inBounds(neighbor, input) || input[neighbor[0]][neighbor[1]] !== val) {
                // edge
                edgeCount += 1;

                edges.add(`${polarity},${neighbor[0]},${neighbor[1]}`);

                for (const n2 of getNeighbors(neighbor)) {
                    if (edges.has(`${polarity},${n2[0]},${n2[1]}`)) {
                        edgeCount -= 1;
                    }
                }
            } else {
                queue.push(neighbor);
            }

        }
    }

    const price = plotArea * edgeCount;
    console.log("area", plotArea);
    console.log("edges", edgeCount);

    return price;
}



