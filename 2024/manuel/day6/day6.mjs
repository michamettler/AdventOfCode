import { neighbors, readFile, readGrid, readInts, readLines } from '../util/load-csv.mjs';



(async () => {
    let input = await readFile('./day6.txt')

    console.log(tracePath(input))
    console.log(quiz2(input))

})();


function testPath(input) {
    const map = input.split("\n").map(line => line.split(""));
    const startY = map.findIndex(line => line.includes("^"));
    const startX = map[startY].indexOf("^");

    let current = { x: startX, y: startY, direction: "up" };

    const visited = new Set();
    while (map[current.y]?.[current.x] !== undefined) {

        let next = { ...current };
        if (visited.has(`${current.x},${current.y},${current.direction}`)) {
            return true;
        }
        visited.add(`${current.x},${current.y},${current.direction}`);
        if (current.direction === "up") {
            next.y -= 1;
        } else if (current.direction === "down") {
            next.y += 1;
        } else if (current.direction === "left") {
            next.x -= 1;
        } else if (current.direction === "right") {
            next.x += 1;
        }
        if (map[next.y]?.[next.x] === "#") {
            next = { ...current };
            if (current.direction === "up") next.direction = "right";
            else if (current.direction === "down") next.direction = "left";
            else if (current.direction === "left") next.direction = "up";
            else if (current.direction === "right") next.direction = "down";
        }
        current = next;
    }
    return false;
}

function quiz2(input) {
    let count = 0;
    for (let i = 0; i < input.length; i++) {
        if (
            input[i] === "." &&
            testPath(`${input.slice(0, i)}#${input.slice(i + 1)}`)
        ) {
            count++;
        }
    }
    return count;
}


function tracePath(input) {
    const grid = input.split("\n").map(line => line.split(""));
    const startY = grid.findIndex(line => line.includes("^"));
    const startX = grid[startY].indexOf("^");

    let current = { x: startX, y: startY, dir: "up" };

    const visited = new Set();

    while (grid[current.y]?.[current.x] !== undefined) {
        let next = { ...current };
        visited.add(`${current.x},${current.y}`);

        if (current.dir === "up") {
            next.y -= 1;
        } else if (current.dir === "down") {
            next.y += 1;
        } else if (current.dir === "left") {
            next.x -= 1;
        } else if (current.dir === "right") {
            next.x += 1;
        }
        if (grid[next.y]?.[next.x] === "#") {
            next = { ...current };
            if (current.dir === "up") next.dir = "right";
            else if (current.dir === "down") next.dir = "left";
            else if (current.dir === "left") next.dir = "up";
            else if (current.dir === "right") next.dir = "down";
        }
        current = next;
    }
    return visited.size;
}

