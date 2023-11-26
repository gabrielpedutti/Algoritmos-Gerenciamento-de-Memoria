using System;
using System.Collections.Generic;

class FIFOCache
{
    private List<int> memory;
    private Queue<int> pageQueue;
    private int capacity;

    public FIFOCache(int capacity)
    {
        this.capacity = capacity;
        memory = new List<int>(capacity);
        pageQueue = new Queue<int>(capacity);
    }

    public void AccessPage(int pageNumber)
    {
        if (!memory.Contains(pageNumber))
        {
            if (memory.Count == capacity)
            {
                // Remover a página mais antiga
                int removedPage = pageQueue.Dequeue();
                memory.Remove(removedPage);
                Console.WriteLine($"Removida a página {removedPage}");
            }

            // Adicionar a nova página à memória e à fila
            memory.Add(pageNumber);
            pageQueue.Enqueue(pageNumber);
            Console.WriteLine($"Adicionada a página {pageNumber} à memória");
        }
        else
        {
            Console.WriteLine($"A página {pageNumber} já está na memória");
        }
    }

    public void PrintMemory()
    {
        Console.WriteLine("Estado atual da memória:");
        foreach (var page in memory)
        {
            Console.Write(page + "\n");
        }
        Console.WriteLine();
    }
}

class Program
{
    static void Main()
    {
        FIFOCache fifoCache = new FIFOCache(3);

        fifoCache.AccessPage(1);
        fifoCache.PrintMemory();

        fifoCache.AccessPage(2);
        fifoCache.PrintMemory();

        fifoCache.AccessPage(3);
        fifoCache.PrintMemory();

        fifoCache.AccessPage(4);
        fifoCache.PrintMemory();

        fifoCache.AccessPage(1);
        fifoCache.PrintMemory();
    }
}

