import { getNeighbors, readFile, inBounds, gcd } from '../util/load-csv.mjs';


(async () => {
    let input = await readFile('./day15.txt')
    part1(input)
    part2(input)

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
    let parts = input.split('\n\n').filter(l => l.length > 0)
    let map = parts[0].split('\n').map(l => l.split(''))
    let moves = parts[1].split('')
    const startY = map.findIndex(line => line.includes("@"));
    const startX = map[startY].indexOf("@");
    const pos = { y: startY, x: startX }

    moves.reduce((pos, next) => move(map, pos, next), pos);
    printGrid(map)
    console.log(gpsScore(map))
}

function part2(input) {
    input = input.replaceAll('#', '##').replaceAll('O', '[]').replaceAll('.', '..').replaceAll('@', '@.')
    let parts = input.split('\n\n').filter(l => l.length > 0)
    let map = parts[0].split('\n').map(l => l.split(''))
    let moves = parts[1].split('')
    const startY = map.findIndex(line => line.includes("@"));
    const startX = map[startY].indexOf("@");
    const pos = { y: startY, x: startX }

    moves.reduce((pos, next) => move(map, pos, next), pos);
    printGrid(map)
    console.log(gpsScore(map))
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
