#include <stdio.h>

/**Width of array*/
#define WIDTH 255

/**Height of array*/
#define HEIGHT 255

/**Max and default value of all array elements*/
#define WHITE 255

/**
* Given an image and a color value in the range 0 to 255,
* this function will set all the pixels of the image to the given color.
* @param image the image to set with
* @param color this color
*/
void clearImage( unsigned char image[ WIDTH ][ HEIGHT ], unsigned char color );

/**
* Given an image and an output file that's been opened for writing,
* this function will write the image to the output file in PGM format.
*
* @param image image to save
* @param outputFile the file to save to
*/
void saveImage( unsigned char image[ WIDTH ][ HEIGHT ], FILE *outputFile );

/**
* Given an image, the endpoints of a line and a drawing color,
* this function will draw the given line into the image as described in the requirements section.
*
* @param image the image to print the line to
* @param x1 starting x
* @param x2 ending x
* @param y1 starting y
* @param y2 ending y
* @param color the color to set the line to
*/
void drawLine( unsigned char image[ WIDTH ][ HEIGHT ],
        int x1, int y1, int x2, int y2, unsigned char color );

/**
* Given an image, the center location of a circle,
* the circle's radius and a drawing color,
* this function will draw a filled circle into the image as described in the requirements section.
* @param image the image to print the line to
* @param cx center x
* @param cy center y
* @param radius radius of circle
* @param color the color to set the circle to
*/
void drawCircle( unsigned char image[ WIDTH ][ HEIGHT ],
        int cx, int cy, int radius, unsigned char color );
