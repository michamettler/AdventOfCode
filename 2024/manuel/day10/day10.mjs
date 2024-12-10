import { readGrid, neighbors } from '../util/load-csv.mjs';


(async () => {

    let input = await readGrid('./day10.txt')
    console.log(part1(input))
    console.log(part2(input))

})();

function part1(grid) {
    let sum = 0
    grid = grid.filter((row) => row.length > 0)
    for (let y = 0; y < grid.length; y++) {
        for (let x = 0; x < grid[y].length; x++) {
            if (grid[y][x] === "0") {
                let queue = [{ x, y }];
                let checked = new Set();

                while (queue.length > 0) {
                    const { x, y } = queue.shift();
                    if (grid[y][x] === "9") {
                        sum++;
                        continue;
                    }
                    const possiblities = neighbors(grid, x, y).map(([x, y]) => ({ x, y }));
                    let nextposition = possiblities.filter((field) => parseInt(grid[field.y][field.x]) === parseInt(grid[y][x]) + 1)

                    for (const field of nextposition) {
                        if (!checked.has(`${field.x},${field.y}`)) {
                            checked.add(`${field.x},${field.y}`);
                            queue.push(field);
                        }
                    }
                }
            }
        }
    }
    return sum
}


function part2(grid) {
    let sum = 0
    grid = grid.filter((row) => row.length > 0)
    for (let y = 0; y < grid.length; y++) {
        for (let x = 0; x < grid[y].length; x++) {
            if (grid[y][x] === "0") {
                let queue = [{ x, y }];

                while (queue.length > 0) {
                    const { x, y } = queue.shift();
                    if (grid[y][x] === "9") {
                        sum++;
                        continue;
                    }
                    const possiblities = neighbors(grid, x, y).map(([x, y]) => ({ x, y }));
                    let nextposition = possiblities.filter((field) => parseInt(grid[field.y][field.x]) === parseInt(grid[y][x]) + 1)

                    for (const field of nextposition) {
                        queue.push(field);
                    }
                }
            }
        }
    }
    return sum
}


