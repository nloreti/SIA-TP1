Para correr el programa, primero ejecutar:

ant -buildfile makesolver.xml

Luego correr el archivo .jar de salida de la siguiente forma:

java -jar Solver.jar (Estrategia) (ruta al tablero) (heuristica)

1) Las Estrategias pueden ser:
	DFS
	BFS
	IDFS
	GREEDY
	AStar

2) La Ruta al tablero de estar parado en el directorio raiz del TP sería:
	files/board*.txt

donde * va de 1,2,3...,12,13.

3)Heuristica
Estas son utilizadas solo para los algoritmos Greedy y A*, es decir que si la busqueda es informada es necesario especificar como entrada una heurística, estas son h1 para utilizar la primera y h2 para utilizar la segunda. h1 es admisible y h2 es no admisible.

Dicho esto un ejemplo de invocación sería:

java -jar Solver.jar AStar files/board2.txt h2

De esta forma se esta pidiendo un algoritmo A* del tablero 2 y con la segunda heurística, la cuál como se mencionó anteriormente es no admisible.

Los tableros utilizados para el informe, de los cuales uno es de nivel bajo y los otros tres de nivel alto son:
Tablero nivel bajo - board2.txt
Tablero nivel alto - board11.txt
Tablero nivel alto - board12.txt
Tablero nivel alto - board13.txt


