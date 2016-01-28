/**
* @file drawing.c
* @author Curtis Moore
* Given a drawing script and output pgm files as command-
* line arguments, outputs a crude PGM using circles and lines.
*/

#include "image.h"
#include <stdio.h>

/** The correct value of argc */
#define CORRECT_ARGS 3

/**
* Takes in a file opened for reading and one for writing.
* interprets commands form the file coming in,
* uses functions from image to follow the instructions on the
* out file.
* @param in script file
* @param out pgm file to print to
*/
int readFile(FILE *in, FILE *out){

  unsigned char image[HEIGHT][WIDTH];
  clearImage(image, WHITE);

  char circ = 'c';
  char lin = 'l';

  int cx;
  int cy;
  int radius;

  int x1;
  int x2;
  int y1;
  int y2;

  int color;

  char first;
  int success = fscanf(in, " %c ", &first);

  while (success != EOF){

    if (first != circ && first != lin){
      fprintf(stderr, "Invalid script file\n");
      return 1;
    }
    if (first == circ){
      fscanf(in, " %d %d %d %d", &cx, &cy, &radius, &color);
      if (color < 0){
        fprintf(stderr, "Invalid script file\n");
        return 1;
      }
      drawCircle(image, cx, cy, radius, color);
    }
    if (first == lin){
      fscanf(in, " %d %d %d %d %d", &x1, &y1, &x2, &y2, &color);
      if (color < 0){
        fprintf(stderr, "Invalid script file\n");
        return 1;
      }
      drawLine(image, x1, y1, x2, y2, color);
    }

    success = fscanf(in, " %c ", &first);

  }
  saveImage(image, out);
  return 0;
}

/**
* Takes in command line arguments for files in and out.
* Does some error checking, passes files on to readfile.
*
* @param argc the number of command line arguments
* @param argv the command line arguments
*/
int main (int argc, const char* argv[] )
{

  if (argc != CORRECT_ARGS){
    fprintf(stderr, "usage: drawing <script_file> <image_file>\n");
    return 1;
  }

  FILE *script = fopen(argv[1], "r");
  FILE *image = fopen(argv[2], "w");

  if (script == NULL){
    fprintf(stderr, "Can't open file: %s", argv[1]);
    fprintf(stderr, "\nusage: drawing <script_file> <image_file>\n");
    return 1;
  }
  if (image == NULL){
    fprintf(stderr, "Can't open file: %s", argv[2]);
    fprintf(stderr, "\nusage: drawing <script_file> <image_file>\n");
    return 1;
  }

  return readFile(script, image);

}
