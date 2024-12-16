import { getNeighbors, readFile, inBounds, gcd, neighbors } from '../util/load-csv.mjs';


(async () => {
    let input = await readFile('./day15.txt')
    part1(input)
})();
function move(map, pos, dir) {
    let dest = { ...pos };
    if (dir === "v") dest.y++;
    if (dir === "^") dest.y--;
    if (dir === ">") dest.x++;
    if (dir === "<") dest.x--;

    if (map[dest.y][dest.x] === 'O') move(map, dest, dir)

    if (map[dest.y][dest.x] === "[" || map[dest.y][dest.x] === "]") {
        if (dest.x !== pos.x) {
            move(map, dest, dir)
        } else {
            let copy = map.map(row => [...row]);
            let pair = { ...dest };

            if (map[dest.y][dest.x] === "[") {
                pair.x += 1
            } else {
                pair.x -= 1
            }

            if (move(copy, dest, dir) !== dest && move(copy, pair, dir) !== pair) {
                move(map, dest, dir);
                move(map, pair, dir);
            }
        }
    }
    if (map[dest.y][dest.x] === '.') {
        map[dest.y][dest.x] = map[pos.y][pos.x]
        map[pos.y][pos.x] = '.'
        return dest
    }

    return pos

}

function part1(input) {
    let map = input.split('\n').map(l => l.split(''))
    const startY = map.findIndex(line => line.includes("S"));
    const startX = map[startY].indexOf("S");
    const start = { y: startY, x: startX }

    const endy = map.findIndex(line => line.includes("E"));
    const endx = map[endy].indexOf("E");
    const end = { y: endy, x: endx }
    const graph = createGraph(map, start)

    bfs(graph, start, end)

    printGrid(map)
}

function createGraph(map, start) {
    let graph = []
    let visited = []
    let queue = [start]

    while (queue.length) {
        let current = queue.shift()
        visited.push(current)

        const ns = neighbors(map, current.x, current.y)
        let next = []

        for (const n of ns) {
            if (map[n[1]][n[0]] !== '#' && !visited.includes(v => v.x === n[0] && v.y === n[1])) {
                queue.push({ x: n[0], y: n[1] })
            }
            if (map[n[1]][n[0]] !== '#') {
                next.push({ x: n[0], y: n[1] })
            }
        }
        if (!graph.includes(o => o.x === current.x && o.y === current.y)) {
            graph.push({ x: current.x, y: current.y, next: next })
        }
    }

}

function bfs(graph, start, end) {



}
function gpsScore(map) {
    let sum = 0
    for (let y = 0; y < map.length; y++) {
        for (let x = 0; x < map[y].length; x++) {
            if (map[y][x] === 'O' || map[y][x] === '[') {
                sum += y * 100 + x
            }
        }
    }
    return sum

}



function printGrid(grid) {
    grid.forEach(row => {
        console.log(row.join(''));
    });
}
