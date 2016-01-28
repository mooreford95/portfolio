/**
  @file wordlist.h
  @author Curtis Moore

  Header file for wordlist component.
  Provides support for working with word lexicon.
*/

#ifndef _WORDLIST_H_
#define _WORDLIST_H_

#include <stdbool.h>

/** Maximum length of a word in wordlist. */
#define WORD_MAX 20

/** Word type, used to store elements of the word list,
    with room for a word of up to 20 characters. */
typedef char Word[ WORD_MAX + 1 ];

/** Representation for the whole wordlist.  It contains
    the list of words as a resizable, dynamically allocated
    array, along with supporting fields for resizing. */
typedef struct {
  /** Number of words in the wordlist. */
  int len;

  /** Capacity of the wordlist, so we can know when we need to resize. */
  int capacity;

  /** List of words.  Should be sorted lexicographically once the word list
      has been read in. */
  Word *words;
} WordList;

#endif
/**
  Tells you if given char is valid.

  @param ch char to check
  @return true if char is valid.
*/
bool validChar( char ch );

/**
  Reads a wordlist in from the given file.

  @param fname name of the file to read from
  @return the wordlist.
 */
WordList *readWordList( char const *fname );

/**
  Given a word list, this function returns the best code for representing
  the sequence of characters at the start of the given string.

  @param wordList to search.
  @param str string to search for. Will be automatically truncated to 20 chars.
  @return the best code to represent the first portion of the string.
 */
int bestCode( WordList *wordList, char const *str );

/**
  This function frees the memory for the given wordList,
  including the dynamically allocated list of words inside
  and the wordList structure itself.

  @param wordList to free.
 */
void freeWordList( WordList *wordList );
