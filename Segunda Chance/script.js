let listaPaginacao = [
    {Processo: 'P1', Referenciado: 1},
    {Processo: 'P2', Referenciado: 1},
    {Processo: 'P3', Referenciado: 0},
    {Processo: 'P4', Referenciado: 1},
    {Processo: 'P5', Referenciado: 1},
    {Processo: 'P6', Referenciado: 1},
    {Processo: 'P7', Referenciado: 1},
    {Processo: 'P8', Referenciado: 0},
    {Processo: 'P9', Referenciado: 1},
    {Processo: 'P10', Referenciado: 1},
];

let elementosMemoriaVirtual = [];
let novoProcesso = {Processo: 'P11', Referenciado: 1}
let novoProcesso2 = {Processo: 'P12', Referenciado: 1}
let novoProcesso3 = {Processo: 'P13', Referenciado: 1}

function listarProcessosEmMemoria(momentoAtual) {
    console.log('============ ' + momentoAtual + ' ============' + '\n');
    const promises = listaPaginacao.map((element, index) => {
        return new Promise((resolve) => {
            setTimeout(() => {
                console.log(`Processo: ${element.Processo} R: ${element.Referenciado}`);
                console.log();
                resolve();
            }, 1000 * index);
        });
    });

    return Promise.all(promises);
}

function adicionarProcesso(novoProcesso) {
    console.log('============ Tentativa de entrada ' + novoProcesso.Processo + ' ============' + '\n')
    let referenciaReduzida = [];
    let primeirosElementos = [];

    for (let i = 0; i < listaPaginacao.length; i++) {
        if (listaPaginacao[i].Referenciado === 1) {
            listaPaginacao[i].Referenciado = 0;
            referenciaReduzida.push(listaPaginacao[i]);
        } else {
            primeirosElementos.push(listaPaginacao[i]);
        }
    }
    elementosMemoriaVirtual.push(primeirosElementos.shift())
    primeirosElementos.unshift(novoProcesso)
    listaPaginacao = primeirosElementos.concat(referenciaReduzida);
}

listarProcessosEmMemoria("Lista inicial")
.then(() => {
    adicionarProcesso(novoProcesso)
})
.then(() => listarProcessosEmMemoria("Verificando referências e reoordenando"))
.then(() => {
    adicionarProcesso(novoProcesso2)
})
.then(() => listarProcessosEmMemoria("Verificando referências e reoordenando"))
.then(() => {
    adicionarProcesso(novoProcesso3)
})
.then(() => listarProcessosEmMemoria("Verificando referências e reoordenando"))