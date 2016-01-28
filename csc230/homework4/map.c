#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include "map.h"

char **initMap( int *rows )
{
  // Allocate enough space to store a pointer for every row.
  *rows = INITIAL_MAP_SIZE;
  char **map = (char **) malloc( *rows * sizeof( char * ) );

  // Fill in the rows with 3-character strings of spaces.
  for ( int i = 0; i < *rows; i++ ) {
    map[ i ] = (char *)malloc( INITIAL_MAP_SIZE + 1 );
    strcpy( map[ i ], "   " );
  }

  // Return the new map.
  return map;
}

char **expandMap( char **map, int *rows, int extraRows,
    int extraCols, int shiftRows, int shiftCols )
{


  if (extraCols >= 1) {
    char **tempMap;
    int cols = strlen(map[1]);
    int newCols = cols + extraCols;

    tempMap = (char **) malloc( *rows * sizeof( char * ) );

    //malloc needed space. Fill. Null term.
    for ( int i = 0; i < *rows; i++ ) {

      tempMap[ i ] = (char *)malloc( newCols + 1 );
      for (int j = 0; j < newCols; j++ )
        tempMap[ i ][ j ] = ' ';
      tempMap[i][newCols] = '\0';

    }

    //Use the copy function to move the arrays over, individually.
    //Shift their indicies right by shiftCols.
    for (int i = 0; i < *rows; i++){
      for (int j = 0; j < cols; j++)
        tempMap[i][j+shiftCols] = map[i][j];
    }

    freeMap(map, *rows);
    return tempMap;
  }

  if (extraRows >= 1) {

    int tempRows = *rows;//
    char **tempMap;//
    int cols = strlen(map[1]);//

    *rows += extraRows;

    tempMap = (char **) malloc( *rows * sizeof(char *));

    //malloc needed space. Fill. Null term.
    for ( int i = 0; i < *rows; i++ ) {
      tempMap[ i ] = (char *)malloc( cols + 1 );
      for (int j = 0; j < cols; j++ )
        tempMap[ i ][ j ] = ' ';
      tempMap[i][cols] = '\0';
    }

    //Use the copy function to move the arrays down, individually.
    //Shift their indicies down by shiftRows.
    for (int i = 0; i < tempRows; i++){
      for (int j = 0; j < cols; j++){
       tempMap[i + shiftRows][j] = map[i][j];
      }
    }

    //set them free. Let them be wild maps, to see heaps found in the lands beyond.
    freeMap(map, tempRows);


    return tempMap;
  }

  return NULL; //you done goofed.

}


void showMap ( char **map, int rows, int rowPos, int colPos, int dir ) {

  for (int i = -1; i <= rows; i++) {

    int rowLength = strlen( map[1] );

    for (int j = -1; j <= rowLength; j++) {
      if ( i == -1 || i == rows) {
        if ( j == -1 ) {
          putchar('+');
        } else if ( j == rowLength ) {
          putchar('+');
          putchar('\n');
        }
        else {
          putchar('-');
        }
      } else if ( j == -1 || j == rowLength){
        putchar('|');
        if (j == rowLength){
          putchar('\n');
        }
      } else if (i == rowPos && j == colPos){
        if (dir == NORTH){
          putchar('^');
        } else if ( dir == EAST ){
          putchar('>');
        } else if ( dir == SOUTH ){
          putchar('V');
        } else if ( dir == WEST ){
          putchar('<');
        }
      } else {
        putchar( map[i][j] );
      }
    }

  }

}

void freeMap ( char **map, int rows )
{
  for (int i = 0; i < rows; i++){
   free(map[i]);
  }
  free(map);
}
