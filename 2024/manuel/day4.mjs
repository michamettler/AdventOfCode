import { neighbors, readGrid } from './util/load-csv.mjs';



(async () => {
    let grid = await readGrid('./day4.txt')
    let result = connectXmas(grid)
    console.log(result)
    result = quiz2(grid)
    console.log(result)

})();

function quiz2(grid) {
    let count = 0
    for (const [x, row] of grid.entries()) {
        for (const [y, field] of row.entries()) {
            if (field !== 'A') continue
            let dirs = neighbors(grid, x, y, true, true)
            let neigh = []

            for (const coor of dirs) {
                neigh.push(grid[coor[0]][coor[1]])
            }
            let countM = neigh.filter((f) => f === 'M').length
            let countS = neigh.filter((f) => f === 'S').length
            if (checkDirectionForLetter("M", grid, x, y, 1, -1) && checkDirectionForLetter("M", grid, x, y, -1, 1)) continue;
            if (checkDirectionForLetter("M", grid, x, y, -1, -1) && checkDirectionForLetter("M", grid, x, y, 1, 1)) continue;

            if (countM === 2 && countS === 2) {
                count++;
            }

        }
    }
    return count
}

function connectXmas(grid) {
    let count = 0
    for (const [x, row] of grid.entries()) {
        for (const [y, field] of row.entries()) {
            let right = checkDirection(grid, x, y, 1, 0)
            if (right) {
                count++
            }
            let left = checkDirection(grid, x, y, -1, 0)
            if (left) {
                count++
            }
            let top = checkDirection(grid, x, y, 0, -1)
            if (top) {
                count++
            }
            let bottom = checkDirection(grid, x, y, 0, 1)
            if (bottom) {
                count++
            }
            let bottom_left = checkDirection(grid, x, y, -1, 1)
            if (bottom_left) {
                count++

            }
            let bottom_right = checkDirection(grid, x, y, 1, 1)
            if (bottom_right) {
                count++
            }
            let top_left = checkDirection(grid, x, y, -1, -1)
            if (top_left) {
                count++
            }
            let top_right = checkDirection(grid, x, y, 1, -1)
            if (top_right) {
                count++
            }
        }
    }
    return count
}

function checkDirectionForLetter(letter, board, xOrigin, yOrigin, xDir, yDir) {
    const xMax = board.length;
    const xEnd = xOrigin + 1 * xDir;
    if (xEnd < 0 || xEnd >= xMax) return false

    const yMax = board[0].length;
    const yEnd = yOrigin + 1 * yDir;
    if (yEnd < 0 || yEnd >= yMax) return false

    for (let diff = 1; diff < 2; diff++) {
        let x = xOrigin + xDir * diff;
        let y = yOrigin + yDir * diff;

        const field = board[x][y];
        if (field !== letter) {
            return false
        }
    }
    return true
}

function checkDirection(board, xOrigin, yOrigin, xDir, yDir) {
    const xMax = board.length;
    const xEnd = xOrigin + 3 * xDir;
    if (xEnd < 0 || xEnd >= xMax) return false

    const yMax = board[0].length;
    const yEnd = yOrigin + 3 * yDir;
    if (yEnd < 0 || yEnd >= yMax) return false
    const word = "XMAS"

    for (let diff = 0; diff < 4; diff++) {
        let x = xOrigin + xDir * diff;
        let y = yOrigin + yDir * diff;

        const field = board[x][y];
        if (field !== word[diff]) {
            return false
        }
    }
    return true
}


