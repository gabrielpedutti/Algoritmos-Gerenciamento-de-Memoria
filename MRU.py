from collections import OrderedDict

class LRUCache:
    def __init__(self, capacidade):
        self.capacidade = capacidade
        self.cache = OrderedDict()

    def acessar_pagina(self, pagina):
        if pagina in self.cache:
            self.cache.move_to_end(pagina)
        else:
            if len(self.cache) >= self.capacidade:
                self.cache.popitem(last=False)
            self.cache[pagina] = None

    def mostrar_cache(self):
        print("Cache:", list(self.cache.keys()))


def menu_interativo():
    capacidade = int(input("Digite a capacidade da cache: "))
    lru_cache = LRUCache(capacidade)

    while True:
        print("\n1. Acessar página")
        print("2. Mostrar cache")
        print("3. Sair")

        escolha = input("Escolha uma opção: ")

        if escolha == "1":
            pagina = int(input("Digite o número da página a ser acessada: "))
            lru_cache.acessar_pagina(pagina)
        elif escolha == "2":
            lru_cache.mostrar_cache()
        elif escolha == "3":
            break
        else:
            print("Opção inválida. Tente novamente.")


menu_interativo()

