Sistema de Otimização de Rotas CargoFast


Estrutura do Projeto

Aresta.java - Representa uma estrada com origem, destino, distância e custo.

Rota.java - Armazena o resultado de uma busca (caminho, custo total e distância total).

Grafo.java - Gerencia as cidades e estradas usando lista de adjacências. Mantém um Set de estradas bloqueadas que são filtradas dinamicamente.

BuscadorRotas.java - Implementa as buscas de caminho usando algoritmo baseado em Dijkstra.

SistemaCargoFast.java - Menu interativo para usar o sistema.

Algoritmo Utilizado
Usei uma variação do algoritmo de Dijkstra com fila de prioridade:

Para menor custo: fila ordenada por custo acumulado, só adiciona caminhos que não ultrapassam o limite
Para menor distância: fila ordenada por distância acumulada, sem restrição de custo

A fila de prioridade garante que sempre exploro primeiro os caminhos mais promissores. Um mapa guarda os menores valores já encontrados para evitar reprocessamento.
Decisões de Projeto

Lista de adjacências: mais eficiente que matriz para grafos esparsos
Set para bloqueios: rápido para adicionar/remover estradas bloqueadas
Classe Estado interna: encapsula informações do caminho sendo explorado
Detecção de ciclos: verifica se cidade já está no caminho antes de adicionar