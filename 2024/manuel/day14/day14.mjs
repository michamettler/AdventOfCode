import { getNeighbors, readFile, inBounds, gcd } from '../util/load-csv.mjs';


(async () => {
    let input = await readFile('./day14.txt')
    part1(input)
    await part2(input)

})();
//1111111111111111111111111111111
async function part2(input) {
    const robots = input.split('\n').filter(l => l.length > 0).map((line) => {
        let t = line.split(' ')
        let p = t[0].split('p=')[1].split(',').map(n => parseInt(n))
        let v = t[1].split('v=')[1].split(',').map(n => parseInt(n))
        return {
            p, v
        }
    })

    const limity = 103, limitx = 101;
    for (let i = 0; i < 10000; i++) {
        robots.forEach((r) => {
            r.p[0] += r.v[0]
            r.p[1] += r.v[1]
            if (r.p[0] > limitx - 1) r.p[0] -= limitx;
            if (r.p[1] > limity - 1) r.p[1] -= limity;
            if (r.p[0] < 0) r.p[0] += limitx;
            if (r.p[1] < 0) r.p[1] += limity;
        })
        if (aligned(robots)) {
            console.log(i)
            printRobotGrid(robots, limitx, limity)
        }
    }
}

function aligned(robots) {
    for (const r of robots) {
        if (robots.filter(r2 => r2.p[1] === r.p[1]).length > 10 && robots.filter(r2 => r2.p[0] === r.p[0]).length > 10) {
            const string = visualizeRobotGrid(robots, 101, 103)
            if (string.filter(row => {
                return row.includes('1111111111111111111111111111111')
            }).length === 2) {

                return true
            }
        }
    }
    return false
}

function part1(input) {
    const robots = input.split('\n').filter(l => l.length > 0).map((line) => {
        let t = line.split(' ')
        let p = t[0].split('p=')[1].split(',').map(n => parseInt(n))
        let v = t[1].split('v=')[1].split(',').map(n => parseInt(n))
        return {
            p, v
        }
    })

    const limity = 103, limitx = 101;
    robots.forEach((r) => {
        r.p[0] += (r.v[0] * 100) % limitx
        r.p[1] += (r.v[1] * 100) % limity
        if (r.p[0] > limitx - 1) r.p[0] -= limitx;
        if (r.p[1] > limity - 1) r.p[1] -= limity;
        if (r.p[0] < 0) r.p[0] += limitx;
        if (r.p[1] < 0) r.p[1] += limity;

    })
    let one = 0, two = 0, three = 0, four = 0

    robots.forEach((r) => {
        if (r.p[0] < Math.ceil((limitx - 1) / 2)) {
            //upper
            if (r.p[1] < Math.ceil((limity - 1) / 2)) {
                //left
                one++
            } else if (r.p[1] > Math.ceil((limity - 1) / 2)) {
                //right
                two++
            }

        } else if (r.p[0] > Math.ceil((limitx - 1) / 2)) {
            //lower
            if (r.p[1] < Math.ceil((limity - 1) / 2)) {
                //left
                three++
            } else if (r.p[1] > Math.ceil((limity - 1) / 2)) {
                //right
                four++
            }
        }
    })
    console.log(one, two, three, four)
    console.log(one * two * three * four)

}

function visualizeRobotGrid(robots, limitX, limitY) {
    // Initialize a 2D array (grid) filled with zeros
    // Note: y corresponds to rows and x corresponds to columns
    const grid = [];
    for (let y = 0; y < limitY; y++) {
        grid[y] = [];
        for (let x = 0; x < limitX; x++) {
            grid[y][x] = 0;
        }
    }

    // Place robots on the grid
    // For each robot, increment the cell count at [y][x]
    for (const r of robots) {
        const [x, y] = r.p;
        if (x >= 0 && x < limitX && y >= 0 && y < limitY) {
            grid[y][x] += 1;
        }
    }
    let string = []
    // Print the grid row by row
    // Typically, (0,0) is top-left. If you want to print from top to bottom:
    for (let y = 0; y < limitY; y++) {
        let rowStr = "";
        for (let x = 0; x < limitX; x++) {
            rowStr += grid[y][x].toString();
        }
        string.push(rowStr)
    }
    return string
}

function printRobotGrid(robots, limitX, limitY) {
    // Initialize a 2D array (grid) filled with zeros
    // Note: y corresponds to rows and x corresponds to columns
    const grid = [];
    for (let y = 0; y < limitY; y++) {
        grid[y] = [];
        for (let x = 0; x < limitX; x++) {
            grid[y][x] = 0;
        }
    }

    // Place robots on the grid
    // For each robot, increment the cell count at [y][x]
    for (const r of robots) {
        const [x, y] = r.p;
        if (x >= 0 && x < limitX && y >= 0 && y < limitY) {
            grid[y][x] += 1;
        }
    }
    let string = []
    // Print the grid row by row
    // Typically, (0,0) is top-left. If you want to print from top to bottom:
    for (let y = 0; y < limitY; y++) {
        let rowStr = "";
        for (let x = 0; x < limitX; x++) {
            rowStr += grid[y][x].toString();
        }
        console.log(rowStr);
        string.push(rowStr)
    }
    return string
}

