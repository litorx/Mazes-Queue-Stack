import java.util.Scanner;
import java.io.File;
import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Nome do labirinto (ex: Teste1.txt) ou 'sair' para encerrar: ");
            String filename = scanner.nextLine().trim();
            if (filename.equalsIgnoreCase("sair")) break;
            try {
                File file = new File(filename);
                if (!file.exists()) file = new File("LabirintosCorretos" + File.separator + filename);
                if (!file.exists()) file = new File("LabirintosErrados" + File.separator + filename);
                if (!file.exists()) throw new Exception("Arquivo não encontrado: " + filename);
                TxtRead txtRead = new TxtRead(file.getPath());
                String line = txtRead.lerUmaLinha();
                if (line == null) throw new Exception("Arquivo vazio");
                int rows = Integer.parseInt(line.trim());
                line = txtRead.lerUmaLinha();
                if (line == null) throw new Exception("Formato inválido");
                int cols = Integer.parseInt(line.trim());
                Maze maze = new Maze(rows, cols);
                for (int i = 0; i < rows; i++) {
                    line = txtRead.lerUmaLinha();
                    if (line == null) throw new Exception("Linhas insuficientes");
                    for (int j = 0; j < cols; j++) maze.setCell(i, j, line.charAt(j));
                }
                txtRead.close();
                // Valida caracteres inválidos
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < cols; j++) {
                        char c = maze.getCell(i, j);
                        if (c != '#' && c != ' ' && c != 'E' && c != 'S')
                            throw new Exception("Caractere inválido '" + c + "' em (" + i + "," + j + ")");
                    }
                }
                // Garante que E/S só ocorram na borda
                for (int i = 1; i < rows - 1; i++) {
                    for (int j = 1; j < cols - 1; j++) {
                        char c2 = maze.getCell(i, j);
                        if (c2 == 'E') throw new Exception("Entrada fora da borda em (" + i + "," + j + ")");
                        if (c2 == 'S') throw new Exception("Saída fora da borda em (" + i + "," + j + ")");
                    }
                }
                // Encontrar entradas e saídas na borda
                List<Coordinate> entrances = new ArrayList<>();
                List<Coordinate> exits = new ArrayList<>();
                for (int j = 0; j < cols; j++) {
                    char top = maze.getCell(0, j), bottom = maze.getCell(rows - 1, j);
                    if (top == 'E') entrances.add(new Coordinate(0, j));
                    if (bottom == 'E') entrances.add(new Coordinate(rows - 1, j));
                    if (top == 'S') exits.add(new Coordinate(0, j));
                    if (bottom == 'S') exits.add(new Coordinate(rows - 1, j));
                }
                for (int i = 1; i < rows - 1; i++) {
                    char left = maze.getCell(i, 0), right = maze.getCell(i, cols - 1);
                    if (left == 'E') entrances.add(new Coordinate(i, 0));
                    if (right == 'E') entrances.add(new Coordinate(i, cols - 1));
                    if (left == 'S') exits.add(new Coordinate(i, 0));
                    if (right == 'S') exits.add(new Coordinate(i, cols - 1));
                }
                if (entrances.isEmpty()) throw new Exception("Nenhuma entrada encontrada na borda do labirinto");
                if (entrances.size() > 1) throw new Exception("Mais de uma entrada encontrada na borda do labirinto (" + entrances.size() + ")");
                if (exits.isEmpty()) throw new Exception("Nenhuma saída encontrada na borda do labirinto");
                if (exits.size() > 1) throw new Exception("Mais de uma saída encontrada na borda do labirinto (" + exits.size() + ")");
                Coordinate entrance = entrances.get(0), exitCoord = exits.get(0);

                // DFS progressivo/regressivo
                Pilha<Coordinate> stack = new Pilha<>(rows * cols);
                boolean[][] visited = new boolean[rows][cols];
                Coordinate[][] parent = new Coordinate[rows][cols];
                stack.guardeUmItem(entrance);
                visited[entrance.getX()][entrance.getY()] = true;
                Coordinate end = null;
                int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};
                while (!stack.isVazia()) {
                    Coordinate curr = stack.recupereUmItem();
                    if (curr.equals(exitCoord)) { end = curr; break; }
                    boolean moved = false;
                    for (int[] d : dirs) {
                        int nx = curr.getX() + d[0], ny = curr.getY() + d[1];
                        if (nx >= 0 && nx < rows && ny >= 0 && ny < cols && !visited[nx][ny] && maze.getCell(nx, ny) != '#') {
                            visited[nx][ny] = true;
                            parent[nx][ny] = curr;
                            stack.guardeUmItem(new Coordinate(nx, ny));
                            moved = true;
                            break;
                        }
                    }
                    if (!moved) stack.removaUmItem();
                }
                if (end == null) {
                    System.out.println("Não existe caminho da entrada até a saída");
                } else {
                    Coordinate temp = end;
                    while (!temp.equals(entrance)) {
                        if (maze.getCell(temp.getX(), temp.getY()) != 'S') maze.setCell(temp.getX(), temp.getY(), '*');
                        temp = parent[temp.getX()][temp.getY()];
                    }
                    for (int i = 0; i < rows; i++) { for (int j = 0; j < cols; j++) System.out.print(maze.getCell(i, j)); System.out.println(); }
                    Pilha<Coordinate> pathStack = new Pilha<>(rows * cols);
                    for (Coordinate st = end; st != null; st = parent[st.getX()][st.getY()]) pathStack.guardeUmItem(st);
                    System.out.print("Caminho: ");
                    while (!pathStack.isVazia()) { System.out.print(pathStack.recupereUmItem()); pathStack.removaUmItem(); if (!pathStack.isVazia()) System.out.print(" → "); }
                    System.out.println();
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
            System.out.println();
        }
        scanner.close();
    }
}
