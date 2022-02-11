
const roundRobin = function(numTeams: number){
    function getNumParents(rows: number){
        let sumP2 : number = 0;
        for(let i = 0; i < rows; i++){
            sumP2 += Math.pow(2,i);
        }
        return sumP2;
    }
}
