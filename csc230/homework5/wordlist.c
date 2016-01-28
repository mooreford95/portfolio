/**
  @file wordlist.h
  @author Curtis Moore

  File for working with wordlist struct.
  Provides support for working with word lexicon.
*/

#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "wordlist.h"

/**maximum number of words in a word file.*/
#define MAX_WORDS 414
/**Minumum chars in a word that isn't hardcoded*/
#define WORD_MIN 2

/**
  Adds hardcoded ascii values to wordlist.

  @param list wordlist to add codes to.
*/
void addHardCodes(WordList* list);


/**
  Comparison function expected by bsearch and qsort.

  @param a first string to compare.
  @param b second string.
  @return the order of the items.
*/
int myComp( const void *a, const void *b )
{

  char* aString = (char*) a;
  char* bString = (char*) b;

  return strcmp(aString, bString);

}

bool validChar( char ch )
{
  if (ch == 0x09 || ch == 0x0A || ch == 0x0D){
    return true;
  }

  if (ch >= 0x20 && ch <= 0x7E){
    return true;
  }

  return false;
}

WordList *readWordList( char const *fname )
{
  FILE* f = fopen(fname, "r");
  WordList* list = malloc(sizeof(WordList));
  list->capacity = (WORD_MAX + 1);
  list->words = malloc(sizeof(Word) * list->capacity);

  // Number of characters we're currently using.
  list->len = 0;

  int wordLen;
  char s[WORD_MAX+1]; // = {wordLen = '\0'};
  for (int i = 0; fscanf(f, "%d", &wordLen) == 1; i++) {
    fgetc(f); //skip space after the number

    if (i >= MAX_WORDS) {
      fprintf(stderr, "Invalid word file\n");
      exit(EXIT_FAILURE);
    }

    if (wordLen > WORD_MAX || wordLen < WORD_MIN) {
      fprintf(stderr, "Invalid word file\n");
      exit(EXIT_FAILURE);
    }

    for (int i = 0; i < wordLen; i++) {
      fscanf(f, "%c", &(s[i]));
      if (!validChar(s[i])){
        fprintf(stderr, "Invalid character code: %x\n", (unsigned char)s[i]);
        exit(EXIT_FAILURE);
      }
    }
    s[wordLen] = '\0';
    strcpy(list->words[list->len], s);
    list->len++;

    if (list->len >= list->capacity / 2){
      list->capacity *= 2;
      list->words = realloc(list->words, sizeof(Word) * list->capacity + 2);
    }

  }

  addHardCodes(list);

  qsort(list->words, list->len, sizeof(Word), myComp);

  fclose(f);

  return list;

}

void addHardCodes(WordList* list){

  char word[2];

  word[0] = 0x09;
  word[1] = '\0';

  strcpy(list->words[list->len], word);
  list->len++;

  word[0] = 0x0A;

  strcpy(list->words[list->len], word);
  list->len++;

  word[0] = 0x0D;

  strcpy(list->words[list->len], word);
  list->len++;

  for (int i = 0x20; i <= 0x7E; i++ ) { //flag; BVA
    word[0] = i;

    strcpy(list->words[list->len], word);
    list->len++;
  }

}

int bestCode( WordList *wordList, char const *str )
{

  Word* match = bsearch(str, wordList->words, wordList->len, sizeof(Word), myComp);

//  int index = 0;
  if (match) {
     for (int i = 0; i < (wordList->len); i++){
      if (strcmp(*match, (wordList->words[i])) == 0){
        //printf("here i: %d\n", i);
        return i;
      }
    }
  } else {
    int oldLen = strlen(str);

    if (oldLen > WORD_MAX){
      oldLen = WORD_MAX;
    }

    char newStr[oldLen];
    strncpy(newStr, str, oldLen);


    newStr[oldLen - 1] = '\0';

   // printf("%s \n", newStr);

    return bestCode(wordList, newStr);
  }
  //if it gets here, something has gone horribly wrong.
  return 0;
}

void freeWordList( WordList *wordList )
{
  free(wordList->words);
  free(wordList);
}
