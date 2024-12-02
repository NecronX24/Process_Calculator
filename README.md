# Entrega de Proyecto Final de Sistemas Operativos
## Solución al problema de los procesos
Para los procesos se optó por desarrollar una lista doblemente enlazada que tenga nodos,
dónde cada nodo tenga los siguientes datos:
1. Tiempo de ejecución
2. Tiempo dónde el proceso debe iniciar
3. Tiempo de finalización del proceso
4. Tiempo de ejecución total
5. Tiempo de espera del proceso
6. Índice de servicio
7. Tiempo residual (se explicará su motivo junto a la lógica de RR)
8. Puntero al nodo siguiente
9. Puntero al nodo anterior

Los primeros 2 datos se añaden al crear un nuevo nodo. A partir del punto 3, todos los
valores son cálculos que se obtienen de los 3 primeros valores, por lo que, al momento de
ingresar el valor final, se calculan los datos del 4 al 6, el tiempo residual inicialmente es igual
al tiempo de ejecución, y los punteros se utilizan para mantener un orden.

Para la lista enlazada, se decide utilizar una lista doblemente enlazada ya que, en el
concepto de filas y colas, cada valor que se obtiene se desecha, por lo que no es conveniente.
Además, utilizando una sola clase de lista se reduce código.

Cada lista enlazada tiene un nodo cabeza y un nodo cola para el inicio y final,
respectivamente. Se tiene un arreglo reloj para guardar los puntos en que se ejecuta un
proceso, un valor de quantum que de forma predeterminada es 1, y los promedios del tiempo
total, del tiempo de espera y del índice de servicio.

Se crean 4 métodos importantes, uno para el cálculo de cola, otro para el de fila, además
de uno para RR. El 4to proceso calcula los promedios indicados anteriormente.
## Ejecución del código
El inicio del código es ejecutando el proceso de la canción, seguido de los hilos para
mostrar la interfaz gráfica. En el momento que se inicia la interfaz gráfica, los procesos los
dispara el cliente utilizando los botones, siendo el siguiente orden el indicado para ejecutar el
código de forma correcta:

1. Se consigue la dirección del archivo, se puede utilizar el botón de “Buscar Archivo”
para utilizar un submenú de búsqueda
2. Una vez se tiene el archivo, se asignan los valores a cada tipo de lista presionando
“Asignar Valores”, donde se imprimen los valores obtenidos y se calculan todos los
datos necesarios
3. Para imprimir los datos de cada lista, se utilizan cualquiera de los 3 botones restantes
4. Con los botones mencionados arriba, se obtienen todos los promedios, además de la
mejor opción.

## Lectura de datos
Los datos se deben encontrar en un archivo **.csv**, de la forma "x,y" donde "x" representa el tiempo de ejecución y "y" representa el tiempo de inicio del proceso
