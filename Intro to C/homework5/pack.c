/**
  @file pack.c
  @author Curtis Moore

  Packs a file using a lexicon of words, keying them to
  values that can be stored more compactly.
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

#include "wordlist.h"
#include "bits.h"

/**If you need to replace the default word file, here's your macro.*/
#define NEED_WORD_FILE 4
/**If you don't, it's right here.*/
#define DONT_NEED_WORD_FILE 3


/**
  Reads a file into a dynamically allocated 'string'

  @param file file to read
  @return 'string' representing file.
 */
char *readFile( FILE *fp )
{
  int capacity = WORD_MAX;
  char *buffer = malloc( capacity );

  // Number of characters we're currently using.
  int len = 0;

  // Read from standard file until we
  // reach end-of-file, storing all characters in buffer.
  char c = 0;
  while (fscanf(fp, "%c", &c) != EOF){

    if (!validChar(c)){
      fprintf(stderr, "Invalid character code: %x\n", (unsigned char)c);
      exit(EXIT_FAILURE);
    }

    if (c == EOF){
      break;
    }

    buffer[len] = c;
    buffer[len + 1] = '\0';
    len++;

    if (len >= capacity / 2){
      capacity *= 2;
      buffer = realloc(buffer, capacity + 2);
    }
   }

  return buffer;
}

/**
  Starts the program. Reads the file in, compresses it, prints it,
  using various helper functions.

  @param argc number of arguments
  @param argv command line arguments.
  @return the exit status of the programme.
*/
int main( int argc, char *argv[] )
{
  char *wordFile = "words.txt";
  WordList *wordList;

  if (argc != NEED_WORD_FILE && argc != DONT_NEED_WORD_FILE){
    fprintf(stderr, "usage: pack <input.txt> <compressed.raw> [word_file.txt]\n");
    return EXIT_FAILURE;
  }

  if (argc == NEED_WORD_FILE) {

    wordList = readWordList( argv[DONT_NEED_WORD_FILE] );

  } else {

    wordList = readWordList( wordFile );

  }

  FILE* input = fopen(argv[1], "r");

  if (!input){
    fprintf(stderr, "Can't open file: %s\n", argv[1]);
    fprintf(stderr, "usage: pack <input.txt> <compressed.raw> [word_file.txt]\n");
    exit(EXIT_FAILURE);
  }

  FILE* output = fopen(argv[2], "w");

  if (!output){
    fprintf(stderr, "Can't open file: %s\n", argv[2]);
    fprintf(stderr, "usage: pack <input.txt> <compressed.raw> [word_file.txt]\n");
    exit(EXIT_FAILURE);
  }


#ifdef DEBUG
  // Report the entire contents of the word list, once it's built.
  printf( "---- word list -----\n" );
  for ( int i = 0; i < wordList->len; i++ )
    printf( "%d == %s\n", i, wordList->words[ i ] );
  printf( "--------------------\n" );
#endif

  // Read the contents of the whole file into one big buffer.  This could be more
  // efficient, but it simplifies the rest of the program.
  char *buffer = readFile( input );

  // Write out codes for everything in the buffer.
  int pos = 0;
  PendingBits pending = { 0, 0 };
  while ( buffer[ pos ] ) {
    // Get the next code.
    int code = bestCode( wordList, buffer + pos );


#ifdef DEBUG
    printf( "%d <- %s\n", code, wordList->words[ code ] );
#endif

    // Write it out and move ahead by the number of characters we just encoded.
    writeCode( code, &pending, output );

    pos += strlen( wordList->words[ code ] );
  }

  // Write out any remaining bits in the last, partial byte.
  flushBits( &pending, output );


  free(buffer);
  freeWordList(wordList);

  fclose(input);
  fclose(output);

  return EXIT_SUCCESS;
}
