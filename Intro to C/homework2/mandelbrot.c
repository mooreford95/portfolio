#include <stdio.h>
#include <stdlib.h>
#include <math.h>

/** Dwell cut-off for drawing with ' ' */
#define LEVEL_1 10

/** Dwell cut-off for drawing with '.' */
#define LEVEL_2 20

/** Dwell cut-off for drawing with ':' */
#define LEVEL_3 30

/** Dwell cut-off for drawing with '-' */
#define LEVEL_4 40

/** Dwell cut-off for drawing with '=' */
#define LEVEL_5 50

/** Dwell cut-off for drawing with '+' */
#define LEVEL_6 60

/** Dwell cut-off for drawing with '*' */
#define LEVEL_7 70

/** Dwell cut-off for drawing with '#' */
#define LEVEL_8 80

/** Dwell cut-off for drawing with '%' */
#define LEVEL_9 90

/**Real cut off for for loops*/
#define REAL_MAX 35

/**Imaginary cut off for for loops*/
#define IMAG_MAX 70

int testPoint( double cReal, double cImag )
{
  //Z ← C
  double zReal = cReal;
  double zImag = cImag;
  
  int dwell = 0;
  
  while ( dwell < LEVEL_9) {
    //Z ← Z^2 + C
    double z2Real = (zReal * zReal - zImag * zImag) + cReal;
    double z2Imag = (zImag *zReal + zImag *zReal) + cImag;
    //wanted to to the above and below operations in one step,
    //but it causes the graph to be off ever so slightly.
    zReal = z2Real;
    zImag = z2Imag;
    
    if ( sqrt((zReal * zReal) + (zImag * zImag)) > 2 ){
      break;
    }
    dwell++;
  }
  return dwell;
}

/**
Takes in the dwell number. Returns the appropriate char

@param dwell - the dwell number from the current point
@return symbol for that point
*/
char dwellSymbol( int dwell )
{
  if (dwell < LEVEL_1){
    return ' ';
  }
  if (dwell < LEVEL_2){
    return '.';
  }
  if (dwell < LEVEL_3){
    return ':';
  }
  if (dwell < LEVEL_4){
    return '-';
  }
   if (dwell < LEVEL_5){
    return '=';
  }
   if (dwell < LEVEL_6){
    return '+';
  }
   if (dwell < LEVEL_7){
    return '*';
  }
   if (dwell < LEVEL_8){
    return '#';
  }
   if (dwell < LEVEL_9){
    return '%';
  }
  
  return '@';
  
}
/**
Given the user input values, prints the appropriate graph.

@param minReal user-defined minimum real value.
@param minImag user-defined mimimum imaginary value.
@param size user-defined size of the fractal.
*/
void drawFigure( double minReal, double minImag, double size )
{
  long double iIncrement = size/(REAL_MAX - 1);
  long double rIncrement = size/(IMAG_MAX - 1);
  
  long double currReal = minReal;
  long double currImaginary = minImag + size;
  
  for (int i = 1; i <= REAL_MAX; i++){ //i for imaginary.

    for (int j = 1; j <= IMAG_MAX; j++){

      putchar(dwellSymbol(testPoint(currReal, currImaginary)));

      currReal += rIncrement;
      
    }
    putchar('\n');
    currReal = minReal;
    currImaginary -= iIncrement;
  }
}

/**
Starts the program.
Takes in the user's choice of mimimum values and size.

@return 0 for a good run, 1 if the user enters bad values.
*/
int main ()
{
  double minReal;
  double minImag;
  double size;
  
  printf("Minimum real: ");
  int returnVal = scanf("%lf", &minReal);
  if (returnVal != 1){
      printf("Invalid input\n");
      return 1;
  }
  printf("Minimum imaginary: ");
  returnVal = scanf("%lf", &minImag);
  if (returnVal != 1){
      printf("Invalid input\n");
      return 1;
  }
  printf("Size: ");
  returnVal =scanf("%lf", &size);
  
  if (returnVal != 1){
      printf("Invalid input\n");
      return 1;
  }
  
  drawFigure(minReal, minImag, size);
  
  return 0;
}
