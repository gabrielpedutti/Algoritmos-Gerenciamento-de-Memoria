let idGlobal = 0;

class Pagina{
  constructor() {
    idGlobal++;
    this.id = idGlobal;
    this.referencia = 1;
  }
}

class Memoria{
  constructor() {
    this.paginasEmMemoria = [];
    let p1 = new Pagina();
    let p2 = new Pagina();
    let p3 = new Pagina();
    this.paginasEmMemoria.push(p1);
    this.paginasEmMemoria.push(p2);
    this.paginasEmMemoria.push(p3);
  }

  // adicionarPagina() {
  //   let novaPagina = new Pagina();
  //   if(this.paginasEmMemoria.length < 5) {
  //     this.paginasEmMemoria.push(novaPagina);
  //     console.log("\n====== Página Adicionada! ======\n\n")
  //   } else {
  //     let referenciaReduzida = [];
  //     let primeirosElementos = [];

  //     for (let i = 0; i < this.paginasEmMemoria.length; i++) {
  //         if (this.paginasEmMemoria[i].referencia === 1) {
  //             this.paginasEmMemoria[i].referencia = 0;
  //             referenciaReduzida.push(this.paginasEmMemoria[i]);
  //         } else {
  //             primeirosElementos.push(this.paginasEmMemoria[i]);
  //         }
  //     }
  //     if(primeirosElementos > 0) {
  //       memoriaVirtual.hardDisk.push(primeirosElementos.shift())
  //     } else {
  //       memoriaVirtual.hardDisk.push(referenciaReduzida.shift())
  //     }
  //     primeirosElementos.unshift(novaPagina)
  //     this.paginasEmMemoria = primeirosElementos.concat(referenciaReduzida);
  //     // this.paginasEmMemoria.push(novaPagina);
  //     console.log("\n====== Página Movida para Memória Virtual! ======")
  //     console.log("====== Página Adicionada! ======\n\n")
  //   }
  // }

  adicionarPagina() {
    let novaPagina = new Pagina();
    if(this.paginasEmMemoria.length < 5) {
      this.paginasEmMemoria.push(novaPagina);
      console.log("\n====== Página Adicionada! ======\n\n")
    } else {
      let referenciaReduzida = [];
      let primeirosElementos = [];

      for (let i = 0; i < this.paginasEmMemoria.length; i++) {
          if (this.paginasEmMemoria[i].referencia === 1) {
              this.paginasEmMemoria[i].referencia = 0;
              referenciaReduzida.push(this.paginasEmMemoria[i]);
          } else {
              primeirosElementos.push(this.paginasEmMemoria[i]);
          }
      }
      if(primeirosElementos > 0) {
        memoriaVirtual.hardDisk.push(primeirosElementos.shift())
      } else {
        memoriaVirtual.hardDisk.push(referenciaReduzida.shift())
      }
      primeirosElementos.unshift(novaPagina)
      this.paginasEmMemoria = primeirosElementos.concat(referenciaReduzida);
      // this.paginasEmMemoria.push(novaPagina);
      console.log("\n====== Página Movida para Memória Virtual! ======")
      console.log("====== Página Adicionada! ======\n\n")
    }
  }

  visualizarPaginas() {
    console.log("\n====== Páginas em Memória ======");
    this.paginasEmMemoria.forEach(pagina => {
      console.log(`ID: ${pagina.id} R: ${pagina.referencia}`);
    });
    console.log("===============================");
    console.log();
  }

  referenciar(id) {
    let valorCorreto = false;
    for (let i = 0; i < this.paginasEmMemoria.length; i++) {
      if(this.paginasEmMemoria[i].id == id) {
        this.paginasEmMemoria[i].referencia = 1;
        valorCorreto = true;
        console.log("\n====== Referenciamento realizado ======\n");
      }
    }
    if(valorCorreto == false) {
      console.log("\n====== Página não encontrada ======\n")
    }
  }
}

class MemoriaVirtual {
  constructor() {
    this.hardDisk = [];
  }

  visualizarPaginas() {
    console.log("\n====== Páginas em Memória Virtual ======");
    this.hardDisk.forEach(pagina => {
      console.log(`ID: ${pagina.id} R: ${pagina.referencia}`);
    });
    console.log("===============================");
    console.log();
  }
}



function exibirMenu() {
  console.log("Digite a opção desejada:")
  console.log("1- Visualizar páginas atuais")
  console.log("2- Visualizar páginas na memória virtual")
  console.log("3- Adicionar nova página")
  console.log("4- Referenciar página")
  console.log("5- Sair")

  const readline = require('readline').createInterface({
    input: process.stdin,
    output: process.stdout
  });

  readline.question('Escolha uma opção: ', (opcao) => {
    switch (opcao) {
      case '1':
        memoria.visualizarPaginas();
        readline.close();
        exibirMenu();
        break;
      case '2':
        memoriaVirtual.visualizarPaginas();
        readline.close();
        exibirMenu();
        break;
      case '3':
        memoria.adicionarPagina();
        readline.close();
        exibirMenu();
        break;
      case '4':
        readline.question('Digite o ID da página que você deseja referenciar: ', (id) => {
        memoria.referenciar(parseInt(id));
        readline.close();
        exibirMenu();
        });
        break;
      case '5':
        console.log("\nSaindo...");
        readline.close();
        break;
      default:
        console.log("\n====== Opção inválida ======\n");
        readline.close();
        exibirMenu();
        break;
    }
  });
}
let memoria = new Memoria();
let memoriaVirtual = new MemoriaVirtual();
console.log("============ Algoritmo Segunda Chance ============")
exibirMenu()