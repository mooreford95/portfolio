#define INITIAL_MAP_SIZE 3

/**Remember, never*/
#define NORTH 1
/**Eat*/
#define EAST 2
/**Soggy*/
#define SOUTH 3
/**Waffles*/
#define WEST 4

/**
* Create a 3 X 3 map filled with spaces. This function has already been written for you.
* This function will set the pass-by-address parameter, rows, to the height of the map (3).
*
* @param rows pointer to rows field in main.
*/
char **initMap( int *rows );

/**
* Free dynamically allocated memory used to store the given map.
* Called by main before successful programme termination.
* Also used every time map is expanded.
*
* @param map map to free
* @param rows number of rows
*/
void freeMap( char **map, int rows );

/**
* Prints given map.
*
* @param map map to print.
* @param rows number of rows
* @param rowPos player position, vertical
* @param colPos player position, horizontal
* @param dir current directon of player. Uses preprocessor constants
*/
void showMap( char **map, int rows, int rowPos, int colPos, int dir );


/**
* The hard one.
* Expands dynamically allocated map that is passed in,
* depending on the direction in which it needs to be expanded.
*
* @param map map to expand
* @param rows current number of rows
* @param extraRows rows to add
* @param extraCols columns to add
* @param shiftCols columns to shift current array to right.
* @param shiftRows rows by which to shift down.
*/
char **expandMap( char **map, int *rows, int extraRows,
  int extraCols, int shiftRows, int shiftCols );
