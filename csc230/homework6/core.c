/**
  @file core.c
  @author David Sturgill (dbsturgi)
  @author Curtis Moore (cfmoore2)

  Central functionality for our interpreter.  Support for the context
  object, tokenization and definition of the Expr base class.
*/

#include "core.h"
#include <string.h>
#include <stdlib.h>
#include <ctype.h>
#include <stdbool.h>

/**Arbitrary length for the starting value strings of variables.*/
#define INI_VAR_LEN 10

//////////////////////////////////////////////////////////////////////
// Context
/**Variable holds a variable; a name and a value*/
typedef struct {

  char* name;

  char* value;

} Variable;

struct ContextTag{
  int len;
  int capacity;
  Variable** vars;
};

/**
  Construts a variable struct for storage in the context

  @param name name of variable to create.
  @param value of the varaible to store.
*/
Variable* constructVariable(const char* name, const char* value){
  Variable* this = (Variable*) malloc( sizeof(Variable) );
  this -> name = malloc( MAX_VAR_NAME + 1 );
  this -> value = malloc( MAX_TOKEN + 1 );
  strcpy(this -> name, name);
  strcpy(this -> value, value);
  return this;
}

/**
  Makes a context to store all the variables.
  Only created from main()
*/
Context* makeContext()
{

  Context *this = malloc(sizeof(Context));
  //dynamically sized array of variables
  this -> vars = malloc(sizeof(Variable*) * INI_VAR_LEN);
  this -> len = 0;
  this -> capacity = INI_VAR_LEN;

  return this;
}

/**
  Retreives value of given variable. Not always dynamically allocated.

  @param ctxt context from which to retrieve variable
  @param name of variable to getVar
  @return value of variable.
*/
char const *getVariable( Context *ctxt, char const *name )
{

  for (int i = 0; i < ctxt->len; i++){
    if (strcmp((ctxt->vars)[i]->name, name) == 0) {
      //printf("vale in getVar: %s", (ctxt->vars)[i]->value);
      return (ctxt->vars)[i]->value;
    }
  }
  return "";
}

/**
  Sets given value of given variable in given context.
  If the variable was not already in the context, it adds it.
  Handles dynamically sized aspects of variable array in context.

  @param ctxt ctxt to store/update variable value in.
  @param name of variable
  @param value of variable
*/
void setVariable( Context *ctxt, char const *name, char *value )
{
  for (int i = 0; i < ctxt->len; i++){
    if (strcmp(ctxt->vars[i]->name, name) == 0){
      sprintf(ctxt->vars[i]->value, "%s", value);
      return;
    }
  }

  if (ctxt->len == (ctxt->capacity/2)){
    ctxt->vars = (Variable**) realloc(ctxt->vars, sizeof(Variable*) * (ctxt->capacity) * 2);
  }
  *(ctxt -> vars + (ctxt->len)) = constructVariable(name, value);
  ctxt -> len++;
}

/**
  Frees all memory associated with variables.

  @param variable to free memory of
*/
void freeVariable(Variable *variable){
  free(variable->name);
  free(variable->value);
  free(variable);
}

/**
  Frees memory associated with context

  @param ctxt context to free
*/
void freeContext( Context *ctxt )
{
  for (int i = 0; i < ctxt->len; i++){
      freeVariable((ctxt->vars)[i]);
  }
  free(ctxt->vars);
  free(ctxt);
}

/**
  Returns true if a context has no variables stored in it.

  @param ctxt context to check
  @return true if context has no variables.
*/
bool isEmpty(Context *ctxt)
{
  if (ctxt->len < 0){
    return true;
  }
  return false;
}

//////////////////////////////////////////////////////////////////////
// Input tokenization

// Current line we're parsing, starting from 1 like most editors.
static int lineCount = 1;

bool nextToken( char *token, FILE *fp )
{
  int ch;

  // Skip whitespace and comments.
  while ( isspace( ch = fgetc( fp ) ) || ch == '#' ) {
    // If we hit the comment characer, skip the whole line.
    if ( ch == '#' )
      while ( ( ch = fgetc( fp ) ) != EOF && ch != '\n' );

    if ( ch == '\n' )
      lineCount++;
  }

  if ( ch == EOF )
    return false;

  // Record the character we'ver read and keep up with the token length.
  int len = 0;
  token[ len++ ] = ch;

  // Handle punctuation.
  if ( ch == '{' || ch == '}' ) {
    token[ len++ ] = '\0';
    return true;
  }

  // Handle non-quoted words.
  if ( ch != '"' ) {
    while ( ( ch = fgetc( fp ) ) != EOF && !isspace( ch ) &&
            ch != '{' && ch != '}' && ch != '"' && ch != '#' ) {
      // Complain if the token is too long.
      if ( len >= MAX_TOKEN ) {
        fprintf( stderr, "line %d: token too long\n", linesRead() );
        exit( EXIT_FAILURE );
      }

      token[ len++ ] = ch;
    }

    // We had to read one character too far to find the end of the token.
    // put it back.
    if ( ch != EOF )
      ungetc( ch, fp );

    token[ len++ ] = '\0';
    return true;
  }

  // Most interesting case, handle strings.

  // Is the next character escaped.
  bool escape = false;

  // Keep reading until we hit the matching close quote.
  while ( ( ch = fgetc( fp ) ) != '"' || escape ) {
    // Error conditions
    if ( ch == EOF || ch == '\n' ) {
      fprintf( stderr, "line %d: %s while reading parsing string literal.\n",
               linesRead(), ch == EOF ? "EOF" : "newline" );
      exit( EXIT_FAILURE );
    }

    // On a backslash, we just enable escape mode.
    if ( !escape && ch == '\\' ) {
      escape = true;
    } else {
      // Interpret escape sequences if we're in escape mode.
      if ( escape ) {
        switch ( ch ) {
        case 'n':
          ch = '\n';
          break;
        case 't':
          ch = '\t';
          break;
        case '"':
          ch = '"';
          break;
        case '\\':
          ch = '\\';
          break;
        default:
          fprintf( stderr, "line %d: Invalid escape sequence \"\\%c\"\n",
                   linesRead(), ch );
          exit( EXIT_FAILURE );
        }
        escape = false;
      }

      // Complain if this string, with the eventual close quote, is too long.
      if ( len + 1 >= MAX_TOKEN ) {
        fprintf( stderr, "line %d: token too long\n", linesRead() );
        exit( EXIT_FAILURE );
      }
      token[ len++ ] = ch;
    }
  }

  // Store the closing quote and the terminator and return.
  token[ len++ ] = '"';
  token[ len++ ] = '\0';
  return token;
}

int linesRead()
{
  return lineCount;
}
