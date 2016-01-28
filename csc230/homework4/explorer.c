#include <stdlib.h>
#include <string.h>
#include <stdio.h>
#include "map.h"

/**For changing direction to left*/
#define LEFT -1
/**For changing direction to right*/
#define RIGHT 1

/**Length of the longest possible command, forward, plus null*/
#define COMMAND_LEN 8
/**Length of seen array, plus null*/
#define SEEN_LEN 4
#define SEEN_LEN 4

/** 'boolean.' 0 if the map is consistent. 1 otherwise. */
static char isInconsistent = 0;

/**

Hm. Yes. Much bounds checking. Many whys. Adds seen to the map, if it's consistent.

@param dir direction of player. Preprocessor constants used.
@param pRow current row of player
@param pCol current col of player
@param seen what the player sees. Three chars
@param isMove true if the player will move forward. 1 if true.
*/
char** addToArray(char **map, int *rows, int dir, int* pRow, int* pCol, char* seen, int isMove)
{
  int cols = strlen(map[1]);

  //Set of conditionals to expand map when needed. Removes complexity from later conditionals.
  if ((dir == NORTH && *pRow == 1 && isMove == 1)|| (dir == NORTH && *pRow == 0 && isMove == 0)){

    map = expandMap(map, rows, 1, 0, 1, 0);
    *pRow += 1;
  }

  if ((dir == SOUTH && *pRow == *rows - 2 && isMove == 1)
      || (dir == SOUTH && *pRow == *rows - 1 && isMove == 0)){
    map = expandMap(map, rows, 1, 0, 0, 0);
  }
  if ((dir == EAST && *pCol == cols - 2 && isMove == 1)
      || (dir == EAST && *pCol == cols - 1 && isMove == 0)){

    map = expandMap(map, rows, 0, 1, 0, 0);
    if (isMove == 0){
      for (int i = 0; i < *rows; i++){
        map[i][cols] = ' ';
        map[i][cols+1] = '\0';
      }
    }


  }
  if ((dir == WEST && *pCol == 1 && isMove == 1) || (dir == WEST && *pCol == 0 && isMove == 0)){
    map = expandMap(map, rows, 0, 1, 0, 1);
    *pCol += 1;
  }

  if (isMove == 1){
    if (dir == NORTH){
      *pRow -= 1;
    }
    if (dir == EAST){
      *pCol +=1;
    }
    if (dir == SOUTH){
      *pRow += 1;
    }
    if (dir == WEST){
      *pCol -= 1;
    }
  }

  if (map[*pRow][*pCol] == '#'){
    fprintf(stderr, "Blocked\n");
    isInconsistent = 1;
    return map;
  }


  //for up
  if ( dir == NORTH){

    //if what the player sees to the left is either a space, or what they should see, we're good.
    if ( map[*pRow - 1][*pCol - 1] == ' '){
      map[*pRow - 1][*pCol - 1] = *seen;
    } else if (map[*pRow - 1][*pCol - 1] != *seen) {
      fprintf(stderr, "Inconsistent map\n");
      isInconsistent = 1;
      if (isMove){
        *pRow += 1;
      }
      return map;
    }
    if ( map[*pRow - 1][*pCol] == ' '){
      map[*pRow - 1][*pCol] = *(seen+1);
    } else if (map[*pRow - 1][*pCol] != *(seen + 1)) {
      fprintf(stderr, "Inconsistent map\n");
      isInconsistent = 1;
      if (isMove){
        *pRow += 1;
      }
      return map;
    }
    if ( map[*pRow - 1][*pCol + 1] == ' '){
      map[*pRow - 1][*pCol + 1] = *(seen + 2);
    } else if (map[*pRow - 1][*pCol + 1] != *(seen + 2)) {
      fprintf(stderr, "Inconsistent map\n");
      isInconsistent = 1;
      if (isMove){
        *pRow += 1;
      }
      return map;
    }


     return map;
  }

  //for down. There HAS to be an easier way to do this.
  if ( dir == SOUTH){

    //if what the player sees to the left is either a space, or what they should see, we're good.
    if ( map[*pRow + 1][*pCol + 1] == ' '){
      map[*pRow + 1][*pCol + 1] = (*seen);
    } else if (map[*pRow + 1][*pCol + 1] != *seen) {

      fprintf(stderr, "Inconsistent map\n");
      isInconsistent = 1;
      if (isMove){
        *pRow -= 1;
      }
      return map;
    }
    if ( map[*pRow + 1][*pCol] == ' '){
      map[*pRow + 1][*pCol] = *(seen+1);
    } else if (map[*pRow + 1][*pCol] != *(seen + 1)) {
      fprintf(stderr, "Inconsistent map\n");
      isInconsistent = 1;
      if (isMove){
        *pRow -= 1;
      }
      return map;
    }
    if ( map[*pRow + 1][*pCol - 1] == ' '){
      map[*pRow + 1][*pCol - 1] = *(seen + 2);
    } else if (map[*pRow + 1][*pCol - 1] != *(seen + 2)) {
      fprintf(stderr, "Inconsistent map\n");
      isInconsistent = 1;
      if (isMove){
        *pRow -= 1;
      }
      return map;
    }

  }

  //for right
  if ( dir == EAST){

    //if what the player sees to the above is either a space, or what they should see, we're good.
    if ( map[*pRow - 1][*pCol + 1] == ' '){
      map[*pRow - 1][*pCol + 1] = *seen;
    } else if (map[*pRow - 1][*pCol + 1] != *seen) {
      fprintf(stderr, "Inconsistent map\n");
      isInconsistent = 1;
      if (isMove){
        *pCol -= 1;
      }
      return map;
    }
    if ( map[*pRow][*pCol + 1] == ' '){
      map[*pRow][*pCol + 1] = *(seen+1);
    } else if (map[*pRow][*pCol + 1] != *(seen + 1)) {
      fprintf(stderr, "Inconsistent map\n");
      isInconsistent = 1;
      if (isMove){
        *pCol -= 1;
      }
      return map;
    }

    if ( map[*pRow + 1][*pCol + 1] == ' '){

      map[*pRow + 1][*pCol + 1] = *(seen + 2);
    } else if (map[*pRow + 1][*pCol + 1] != *(seen + 2)) {
      fprintf(stderr, "Inconsistent map\n");
      isInconsistent = 1;
      if (isMove){
        *pCol -= 1;
      }
      return map;
    }

  }

  //for left.
  if ( dir == WEST){

    if ( map[*pRow - 1][*pCol - 1] == ' ' ){
      map[*pRow - 1][*pCol - 1] = *(seen + 2);
    } else if (map[*pRow - 1][*pCol - 1] != *(seen+ 2)) {
      fprintf(stderr, "Inconsistent map\n");
      isInconsistent = 1;
      if (isMove){
        *pCol += 1;
      }
      return map;
    }
    if ( map[*pRow][*pCol - 1] == ' '){
      map[*pRow][*pCol - 1] = *(seen+1);
    } else if (map[*pRow][*pCol - 1] != *(seen + 1)) {
      fprintf(stderr, "Inconsistent map\n");
      isInconsistent = 1;
      if (isMove){
        *pCol += 1;
      }
      return map;
    }
    if ( map[*pRow + 1][*pCol - 1] == ' '){
      map[*pRow + 1][*pCol - 1] = *seen;
    } else if (map[*pRow + 1][*pCol - 1] != *seen) {
      fprintf(stderr, "Inconsistent map\n");
      isInconsistent = 1;
      if (isMove){
        *pCol += 1;
      }
      return map;
    }

  }

  return map;
}


/**

Given a pointer to the current direction of the player,
and the direction in which to change, modifies the pointer to the new direction.

@param dir pointer to direction of player.
@param change -1 for left, 1 for right
*/
void dirChange(int* dir, int change)
{

  //right from left is up.
  if (*dir == WEST && change == RIGHT){
    *dir = RIGHT;
    return;
  }

  //left from up is left.
  if (*dir == NORTH && change == LEFT){
    *dir = WEST;
    return;
  }

  //when not at boundaries, math works as it should.
  *dir += change;

}

/**
* Not sure why I made a method for this.
* Prints error messages to standard error.
*
* @param s string to print.
* @param file the name of the file, in case it couldn't be opened
*/

int printError (const char* s, const char* file)
{

  if (strcmp("", s) == 0){
    fprintf(stderr, "usage: explorer [script_file]\n");
    return EXIT_FAILURE;
  }

  fprintf(stderr, "%s %s\n", s, file);
  fprintf(stderr, "usage: explorer [script_file]\n");
  return EXIT_FAILURE;

}

/**
* Essentially a carbon copy of main. Uses fscanf to read map commands in from a file.
* Sends them to AddToArray.
* @param s name of file.
*/

int readFile(const char* s)
{


  int rows;
  int dir = NORTH;

  //at initialization, the map is 3x3. This, then, is the middle.
  int pRow = 1;
  int pCol = 1;

  FILE *script = fopen(s, "r");

  if (script == NULL){
    return printError("Can't open movement script:", s);
  }

  char** map = initMap( &rows );

  char in[COMMAND_LEN];
  *in = '\0';
  char seen[SEEN_LEN];


  fscanf(script, "%3s", seen);
  
  if(seen[0] == EOF){
      freeMap(map, rows);
      return 0;
    }

  if (strcmp(in, "qui") == 0){
      fclose(script);
      freeMap(map, rows);
      return 0;
    }

  addToArray(map, &rows, dir, &pRow, &pCol, seen, 0);
  showMap(map, rows, pRow, pCol, dir );

  //yes, I get it, it's a bad idea. I find it more readable than a larger while loop.
  inputStart:
  while (strcmp("quit", in) != 0){
    fscanf(script, "%7s", in);

    if (strcmp(in, "quit") == 0){
      fclose(script);
      freeMap(map, rows);
      return 0;
    }

    fscanf(script, "%*[A-Z 1-9!@$%^&()]%3[a-z.#]%*[a-z.#A-Z 1-9!@$%^&() ]", seen);
    
    if(seen[0] == EOF){
      freeMap(map, rows);
      return 0;
    }

    if (strlen(seen) != INITIAL_MAP_SIZE){
      fprintf(stderr, "Invalid input\n");
      goto inputStart;
      exit(EXIT_FAILURE);
    }

    if (strcmp(in, "forward") == 0) {

      map = addToArray(map, &rows, dir, &pRow, &pCol, seen, 1);

      if (isInconsistent){

        isInconsistent = 0;

      } else {
        showMap(map, rows, pRow, pCol, dir );
      }

    } else if (strcmp(in, "left") == 0){

      dirChange(&dir, LEFT);
      map = addToArray(map, &rows, dir, &pRow, &pCol, seen, 0);
      if (isInconsistent){

        dirChange(&dir, RIGHT);
        isInconsistent = 0;

      } else {
        showMap(map, rows, pRow, pCol, dir );
      }

    } else if (strcmp(in, "right") == 0){

      dirChange(&dir, RIGHT);
      map = addToArray(map, &rows, dir, &pRow, &pCol, seen, 0);
      if (isInconsistent){

        dirChange(&dir, LEFT);
        isInconsistent = 0;

      } else {
        showMap(map, rows, pRow, pCol, dir );
      }

    } else {
      fprintf(stderr, "Invalid command\n");
      goto inputStart;
      exit(EXIT_FAILURE);
    }


  }
  fclose(script);
  freeMap(map, rows);
  return 0;
}


/**
* Takes commands in from command-line, sends them to addToArray.
* Checks command-line arguments, hands off to readFile if need be.
*
* @param argc number of command-line arguments
* @param argv the command-line arguments
*/
int main ( int argc, const char* argv[] )
{
  int rows;
  int dir = NORTH;

  //at initialization, the map is 3x3. This, then, is the middle.
  int pRow = 1;
  int pCol = 1;

  if (argc > 2){
   return printError("", "");
  }

  if (argc == 2){
    return readFile(argv[1]);
  }


  char** map = initMap( &rows );

  char in[COMMAND_LEN];
  *in = '\0';
  char seen[SEEN_LEN];


  scanf("%3s", seen);

  if(seen[0] == EOF){
      freeMap(map, rows);
      return 0;
    }

  if (strcmp(seen, "qui") == 0){
      freeMap(map, rows);
      return 0;
    }

  addToArray(map, &rows, dir, &pRow, &pCol, seen, 0);
  showMap(map, rows, pRow, pCol, dir );

  //yes, I get it, it's a bad idea. I find it more readable than a larger while loop.
  inputStart:
  while (strcmp("quit", in) != 0){
    scanf("%7s", in);

    if (strcmp(in, "quit") == 0){
      freeMap(map, rows);
      return 0;
    }

    scanf("%*[A-Z 1-9!@$%^&()]%3[a-z.#]%*[a-z.#A-Z 1-9!@$%^&() ]", seen);

    if(seen[0] == EOF){
      freeMap(map, rows);
      return 0;
    }

    if (strlen(seen) != INITIAL_MAP_SIZE){
      fprintf(stderr, "Invalid input\n");
      goto inputStart;
      exit(EXIT_FAILURE);
    }

    if (strcmp(in, "forward") == 0) {

      map = addToArray(map, &rows, dir, &pRow, &pCol, seen, 1);

      if (isInconsistent){

        isInconsistent = 0;

      } else {
        showMap(map, rows, pRow, pCol, dir );
      }
    } else if (strcmp(in, "left") == 0){

      dirChange(&dir, LEFT);
      map = addToArray(map, &rows, dir, &pRow, &pCol, seen, 0);
      if (isInconsistent){

        dirChange(&dir, RIGHT);
        isInconsistent = 0;

      } else {
        showMap(map, rows, pRow, pCol, dir );
      }

    } else if (strcmp(in, "right") == 0){

      dirChange(&dir, RIGHT);
      map = addToArray(map, &rows, dir, &pRow, &pCol, seen, 0);
      if (isInconsistent){

        dirChange(&dir, LEFT);
        isInconsistent = 0;

      } else {
        showMap(map, rows, pRow, pCol, dir );
      }

    } else {

      fprintf(stderr, "Invalid command\n");
      goto inputStart;
      exit(EXIT_FAILURE);
    }


  }

  freeMap(map, rows);
  return 0;

}
