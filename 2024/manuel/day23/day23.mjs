import fs from 'fs'


let networks = fs.readFileSync('./day23.txt', 'utf8').trim().split('\n').map(l => l.split('-'))

let set = new Set();
let connections = networks
networks.forEach(computers => {
    let candidates = computers
        .map(c => {
            return connections
                .filter(pair => pair.includes(c))
                .map(pair => pair.find(x => x !== c));
        })
        .map(x => new Set(x));
    let result = candidates.reduce((a, b) => a.intersection(b));
    result.forEach(x => set.add([...computers, x].sort().join(",")));
});
console.log(set)

console.log([...set].filter(x => x.match(/(^t|,t)/)).length)



function addOneToNetworks(networks, connections = networks) {
    let set = new Set();
    networks.forEach(computers => {
        let candidates = computers
            .map(c => {
                return connections

                    .filter(pair => pair.includes(c))
                    .map(pair => pair.find(x => x !== c));
            })
            .map(x => new Set(x));
        let result = candidates.reduce((a, b) => a.intersection(b));
        result.forEach(x => set.add([...computers, x].sort().join(",")));
    });
    return set;
}

connections = fs.readFileSync('./day23.txt', 'utf8').trim().split('\n').map(l => l.split('-'))
networks = connections;
while (networks.length > 1) {
    let next = addOneToNetworks(networks, connections);
    networks = [...next].map(x => x.split(","));
}
networks[0].sort().join(",");
