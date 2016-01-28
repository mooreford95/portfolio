/**
  @file pack.c
  @author Curtis Moore

  File to be used by pack and unpack for all of their bitwise operations.
  Blood, sweat, tears and goat sacrifices involved.
*/

#include <stdio.h>

#include "bits.h"

void writeCode( int code, PendingBits *pending, FILE *fp )
{

  //I tried to define this value as a macro, but C really doesn't like that.
  unsigned short shortToPrint = 0x0000;

  if ( pending->bitCount == BITS_PER_BYTE ) {

    pending->bitCount = 0;
    flushBits(pending, fp);

  }

  shortToPrint |= code;
  shortToPrint = ( shortToPrint << pending->bitCount ) | pending->bits;

  fprintf(fp, "%c", shortToPrint);

  pending->bits &= 0x0000; //does this need to be shortToPrint?
  pending->bits |= code >> (BITS_PER_BYTE - pending->bitCount);

  pending->bitCount++;

}

void flushBits( PendingBits *pending, FILE *fp )
{

  fprintf(fp, "%c", pending->bits);
  pending->bits &= 0x0000;

}

int readCode( PendingBits *pending, FILE *fp )
{
  /**byte to be output*/
  short output = 0;
  /**Mask to get lower-order nine bits.*/
  short mask = 0x01FF;
  short blank = 0x00;

  short byte0 = 0;
  short byte1 = 0;


  if (pending->bitCount == 0) {

    byte0 = fgetc(fp);
    byte1 = fgetc(fp);

    if (/*fscanf(fp, "%c", (char*)&byte0) == EOF || */byte0 == EOF){
      return EOF;
    } else if (/*fscanf(fp, "%c", (char*)&byte1) == EOF || */byte1 == EOF) {
      pending->bitCount = EOF;
      return (int) byte0;
    }

    output = byte1 << BITS_PER_BYTE;
    output |= byte0;
    output &= mask;

    pending->bits &= blank;
    pending->bits = (byte1 >> 1);

    //pendingCode = (int) pending->bits;
    pending->bitCount = BITS_PER_BYTE - 1;


    return output; //FLAG cast(?)
  } else {

    byte0 = pending->bits;
    byte1 = fgetc(fp);

    //fscanf(fp, "%c", (char*)&byte1); //here

    if (byte1 == EOF) { //put this ^^
      pending->bitCount = EOF;
      return byte0;
    }

    output = byte1 << ( pending->bitCount );

    output |= byte0;
    output &= mask;

    pending->bits &= blank;
    pending->bits |= (byte1 >> (BITS_PER_BYTE - pending->bitCount + 1));

    //pendingCode = (int) pending->bits;
    (pending->bitCount)--;

    return (int)output;

  }

  return 0;

}
