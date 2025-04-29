# ED - Solução de Labirintos em Java

## Descrição
Projeto em Java para leitura e resolução de labirintos definidos em arquivos de texto. Utiliza busca em profundidade (DFS) com pilha para encontrar um caminho da entrada (E) até a saída (S) e marca o percurso com `*`.

## Estrutura do Projeto

```
.
├── .gitignore
├── .vscode/
├── ED/
│   ├── Clonador.java       # Auxilia a clonagem de objetos Cloneable
│   ├── Coordinate.java     # Representa uma posição (x,y)
│   ├── Fila.java           # Implementação genérica de fila
│   ├── LabirintosCorretos/  # Exemplos de labirintos válidos
│   ├── LabirintosErrados/   # Exemplos de labirintos inválidos
│   ├── Main.java           # Ponto de entrada e interface de console
│   ├── Maze.java           # Representação interna do labirinto
│   ├── Pilha.java          # Implementação genérica de pilha usada na busca
│   └── TxtRead.java        # Leitura de linhas de arquivos de texto
└── README.md               # Documentação do projeto
```

## Pré-requisitos
- Java 8 ou superior

## Compilação
Abra o terminal na raiz do projeto e execute:
```bash
javac ED/*.java
```

## Execução
Ainda na raiz do projeto, execute:
```bash
java -cp ED Main
```
Ao iniciar, será solicitado o nome do arquivo de labirinto (ex: `Teste1.txt`) ou digite `sair` para encerrar. O programa procura o arquivo na pasta atual, em `ED/LabirintosCorretos` e em `ED/LabirintosErrados`.

## Principais Classes
- **Main**: Interface de console, validação e controle do fluxo da aplicação.
- **Maze**: Matriz de caracteres que armazena e manipula o labirinto.
- **Pilha**: Pilha genérica (LIFO) utilizada na busca em profundidade.
- **Fila**: Fila genérica (FIFO) disponível para futuras implementações.
- **TxtRead**: Leitor de linhas de arquivo de texto.
- **Coordinate**: Representa coordenadas no labirinto.
- **Clonador**: Realiza clonagem de objetos que implementam `Cloneable`.

## Estruturas de Dados: Pilha e Fila

A classe `Pilha` implementa uma estrutura LIFO (Last-In, First-Out) com redimensionamento dinâmico. No projeto, `Pilha` é usada na busca em profundidade (DFS): cada coordenada acessada é empilhada ao avançar e removida ao retroceder, permitindo explorar todos os caminhos.

A classe `Fila` implementa uma estrutura FIFO (First-In, First-Out) também com redimensionamento dinâmico. Embora não seja utilizada na solução de DFS atual, `Fila` está disponível para futuras estratégias de busca em largura (BFS) ou outros fluxos que requerem processamento sequencial.

## Funcionamento
1. Lê dimensões e mapa do arquivo de texto.
2. Valida caracteres e posição de entrada/saída na borda.
3. Executa DFS usando `Pilha` para encontrar caminho.
4. Exibe o labirinto com o caminho marcado ou mensagem de erro.

## Licença
Projeto para fins acadêmicos.
