import { neighbors, readCsv, readGrid, readLines } from './util/load-csv.mjs';

(async () => {
    try {
        //const results = await readCsv('./test.csv');
        //console.log(results);
        //const lines = await readLines('./test.csv');
        //console.log(lines)

        const grid = await readGrid('./test.grid')
        const nearest = neighbors(grid, 1, 1, true)
        console.log(nearest)

    } catch (error) {
        console.error('Error loading CSV:', error);
    }
})();
