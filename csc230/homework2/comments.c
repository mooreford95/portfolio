/**
@author Curtis Moore

A program to take in another program's code from standard input and
determine the amount of it that is comments.
*/

#include <stdio.h>
#include <stdlib.h>

#define THREE 3
#define BAD_RETURN 100
#define PERCENTS_DOUBLE 100.0

/** Total count of characters. */
int totalChars = 0;

/** Total count of characters that are part of a comment. */
int commentChars = 0;

/** Total number of comments in the input. */
int commentCount = 0;

/**
Called when main encounters a comment.
Parses through the comment, counting the characters.

@return 1 if the comment is closed, 0 if not.
*/
int processComment()
{
  char c = getchar();
  totalChars++;
  commentChars += THREE;
  commentCount++;
  while (c != EOF){

    c = getchar();
    totalChars++;
    commentChars++;

    if (c == '*'){

      c = getchar();
      totalChars++;
      commentChars++;
      
      if (c == '/'){
        return 1;
      }
    }
  }
  return 0;
}
/**
Parses through text.
Calls processComment when the opening of a comment is detected.

@return 100 if the input is empty, 101 if a comment is not terminated, 0 otherwise.
*/
int main(){
  char c = getchar();
  if (c == EOF){
    printf("Empty input\n");
    return BAD_RETURN;
  }
  totalChars++;
  while (c != EOF){
    if (c == '/'){
      c = getchar();
      totalChars++;
        if (c == '*'){
          int comm = processComment();
          if (comm == 0){
            printf("Unterminated comment\n");
            return BAD_RETURN + 1;
          }
        }
    }
    c = getchar();
    if (c != EOF){
      totalChars++;
    }
  }
  
  printf("Input characters: %d", totalChars);
  printf("\nComments: %d (%.2lf%%)\n", commentCount,
    ((double)commentChars/(double)totalChars)*PERCENTS_DOUBLE);

  return 0;
}
