import os
class Page:
  def __init__(self, id, referenced):
      self.id = id
      self.referenced = referenced

class Menu:
  def display_pages(self, pages, name):
      print("---- Current Page Status in " + name + "----")
      for page in pages:
          print("ID: " + str(page.id) + "\tR: " + str(page.referenced))
      print("\n")

  def userInput(self, limit):
      valid = False
      while not valid:
          option = int(input("Digite uma opção: "))
          if option < 1 or option > limit:
              print("Opção inválida. Digite uma opção válida")
          else:
              valid = True
      return option

  def gui(self, ram, hdd, frames, numPages):
      print(f"Posição do ponteiro: {Clock.pointer}")
      self.display_pages(ram, "Ram")
      self.display_pages(hdd, "HDD")

      print("1:Adicionar Página\n2:Referenciar Página\n3:Sair\n")
      option = self.userInput(5)
      if option == 1:
          self.addPage(ram, hdd, frames)
      elif option == 2:
          self.referencePage(ram, hdd)
      elif option == 3:
          return exit(1)

  def addPage(self, ram, hdd, frames):
      newPage = Page(len(hdd)+len(ram)+1, 1)
      Clock.pointer = Clock.victimize(ram, hdd, Clock.pointer)
      ram.insert(Clock.pointer,newPage)

  def referencePage(self, ram, hdd):

      print("Digite o número da página a ser referenciada")
      ref = self.userInput(len(ram)+len(hdd)) 
      for i in range(len(ram)):
          if(ref == ram[i].id):
              ram[i].referenced = 1
      for i in range(len(hdd)):
          if (ref == hdd[i].id):
              Clock.pointer = Clock.victimize(ram, hdd, Clock.pointer)
              hdd[i].referenced = 1
              ram.insert(Clock.pointer,hdd[i])
              hdd.pop(i)
              

class Clock:
  pointer = 0
  def __init__(self, frames, numPages):
      self.ram = [Page(i + 1, 1) for i in range(min(frames, numPages))]
      if(numPages > frames):
          self.hdd = [Page(self.ram[-1].id + 1, 0) for i in range(numPages - frames)]
      self.menu = Menu()
      while True:
          os.system('cls')
          self.menu.gui(self.ram, self.hdd, frames, numPages)

  def victimize(ram, hdd, pointer):
      if pointer >= 0:
          return victimize_from_start(ram, hdd, pointer)
      else:
          return victimize_from_end(ram, hdd, pointer)

def victimize_from_start(ram, hdd, pointer):
  while pointer < len(ram):
      if ram[pointer].referenced == 0:
          hdd.append(ram[pointer])
          ram.pop(pointer)
          return pointer
      else:
          ram[pointer].referenced = 0
      pointer += 1
  return Clock.victimize(ram, hdd, -1)

def victimize_from_end(ram, hdd, pointer):
  while pointer >= 0:
      if ram[pointer].referenced == 0:
          hdd.append(ram[pointer])
          ram.pop(pointer)
          return pointer
      else:
          ram[pointer].referenced = 0
      pointer -= 1
  return Clock.victimize(ram, hdd, 0)
