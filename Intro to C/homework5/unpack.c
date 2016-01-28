/**
  @file unpack.c
  @author Curtis Moore

  Takes a file that has been compressed using pack,
  converts back to real text using a lexicon.
*/

#include <string.h>
#include <stdio.h>
#include <stdlib.h>

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
  Starts the program. Reads the file in, decompresses it, prints it,
  using various helper functions.

  @param argc number of arguments
  @param argv command line arguments.
  @return the exit status of the programme.
*/
int main(int argc, char *argv[])
{


  char *wordFile = "words.txt";
  WordList *wordList;

  if (argc != NEED_WORD_FILE && argc != DONT_NEED_WORD_FILE){
    fprintf(stderr, "usage: unpack <compressed.raw> <output.txt> [word_file.txt]\n");
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
    fprintf(stderr, "usage: unpack <compressed.raw> <output.txt> [word_file.txt]\n");
    exit(EXIT_FAILURE);
  }

  FILE* output = fopen(argv[2], "w");

  if (!output){
    fprintf(stderr, "Can't open file: %s\n", argv[2]);
    fprintf(stderr, "usage: unpack <compressed.raw> <output.txt> [word_file.txt]\n");
    exit(EXIT_FAILURE);
  }

  PendingBits pending = { 0, 0 };

  int code = readCode(&pending, input);
  while (true){

    if (code == EOF || pending.bitCount == EOF){
      break;
    }

    fprintf(output, "%s", wordList->words[code]);

    code = readCode(&pending, input);

  }

  freeWordList(wordList);

  fclose(input);
  fclose(output);

  return EXIT_SUCCESS;
}
