/**
* @file image.c
* @author Curtis Moore
* Conatins functions for printing circles and lines to
* an already opened file.
*/

#include "image.h"
#include <stdio.h>
#include <stdlib.h>
#include <math.h>

void clearImage( unsigned char image[ WIDTH ][ HEIGHT ], unsigned char color ){
    for (int i = 0; i < HEIGHT; i++ ){
        for (int j = 0; j < WIDTH; j++){
            image[i][j] = color;
        }
    }
}

void saveImage( unsigned char image[ WIDTH ][ HEIGHT ], FILE *outputFile ){
    fprintf(outputFile, "P2\n");
    fprintf(outputFile, "%d %d\n", WIDTH, HEIGHT);
    fprintf(outputFile, "%d\n", WHITE);
    for (int i = 0; i < HEIGHT; i++ ){
      fprintf(outputFile, "%3hhu", image[i][0]);
        for (int j = 1; j < WIDTH; j++){
          fprintf(outputFile, " %3hhu", image[i][j]);
        }
      fprintf(outputFile, "\n");
    }
}

void drawLine( unsigned char image[ WIDTH ][ HEIGHT ],
    int x1, int y1, int x2, int y2, unsigned char color ){
  int xDiff = x2-x1;
  int yDiff = y2-y1;
  double maxDiff = fmax((double)abs(xDiff), (double)abs(yDiff));

  double xUpdate = xDiff/maxDiff;
  double yUpdate = yDiff/maxDiff;

  double currX = x1;
  double currY = y1;

  for (int i = 0; i <= round(maxDiff); i++){

    int xPass = round(currX);
    int yPass = round(currY);

    if (yPass < HEIGHT && yPass >= 0 && xPass < WIDTH && xPass >= 0){
      image[yPass][xPass] = color;
    }
    currX += xUpdate;
    currY += yUpdate;
  }

}

void drawCircle( unsigned char image[ WIDTH ][ HEIGHT ],
    int cx, int cy, int radius, unsigned char color ){

  for (int i = -radius; i <= radius; i++){

    for (int j = -radius; j <= radius; j++){

      int y = cy + i;
      int x = cx + j;

      if ( (i*i) + (j*j) < radius*radius){
        if (y < WIDTH && y >= 0 && x < HEIGHT && x >= 0){
          image[y][x] = color;
        }
      }
    }
  }
}
