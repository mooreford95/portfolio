/**
  @file extra.c
  @author David Sturgill (dbsturgi)

  Representation for basic types of expressions, the stuff you are given at the start of
  the assignment.
*/

#include "extra.h"

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

//////////////////////////////////////////////////////////////////////
// Binary expressions

// For arithmetic operators, this is the maximum length of a long, printed
// out as a decimal (with a sign).
#define MAX_NUMBER 20

/** Representation for an arbitrary binary operator.  The eval
    pointer decides what it computes. */
typedef struct {
  char *(*eval)( Expr *oper, Context *ctxt );
  void (*destroy)( Expr *oper );

  // Two perand expressions.
  Expr *op1, *op2;
} BinaryExpr;

/** Free memory for any type of BinaryExpr instance. */
static void destroyBinary( Expr *expr )
{
  BinaryExpr *this = (BinaryExpr *)expr;

  // Free our operand subexpressions.
  this->op1->destroy( this->op1 );
  this->op2->destroy( this->op2 );

  // Then the BinaryExpr struct itself.
  free( this );
}

/** Construct a BinaryExpr representation and fill in the parts
    that are common to all BinaryExpr instances. */
static BinaryExpr *buildBinaryExpr( Expr *op1, Expr *op2 )
{
  BinaryExpr *this = (BinaryExpr *) malloc( sizeof( BinaryExpr ) );
  this->destroy = destroyBinary;

  this->op1 = op1;
  this->op2 = op2;

  return this;
}

/** For instances of BinaryExpr that do addition, this
    is the funciton they call for eval. */
static char *evalAdd( Expr *expr, Context *ctxt )
{
  // Get a pointer to the more specific type this function works with.
  BinaryExpr *this = (BinaryExpr *)expr;

  // Evaluate our two operands
  char *left = this->op1->eval( this->op1, ctxt );
  char *right = this->op2->eval( this->op2, ctxt );

  // Parse the left and right operands as long ints.  Set them
  // to zero if they don't parse correctly.
  long a, b;
  if ( sscanf( left, "%ld", &a ) != 1 )
    a = 0;

  if ( sscanf( right, "%ld", &b ) != 1 )
    b = 0;

  // We're done with the values returned by our two subexpressions,
  // We just needed to get them as long ints.
  free( left );
  free( right );

  // Compute the result, store it in a dynamically allocated string
  // and return it to the caller.
  char *result = (char *)malloc( MAX_NUMBER + 1 );
  sprintf( result, "%ld", a + b );
  return result;
}

Expr *makeAdd( Expr *op1, Expr *op2 )
{
  // Get in a generic instance of BinaryExpr
  BinaryExpr *this = buildBinaryExpr( op1, op2 );

  // Fill in our function to do adding.
  this->eval = evalAdd;

  // Return the instance as if it's an Expr (which it sort of is)
  return (Expr *) this;
}

/** For instances of BinaryExpr that do subtraction, this
    is the funciton they call for eval. */
static char *evalSub( Expr *expr, Context *ctxt )
{
  // Get a pointer to the more specific type this function works with.
  BinaryExpr *this = (BinaryExpr *)expr;

  // Evaluate our two operands
  char *left = this->op1->eval( this->op1, ctxt );
  char *right = this->op2->eval( this->op2, ctxt );

  // Parse the left and right operands as long ints.  Set them
  // to zero if they don't parse correctly.
  long a, b;
  if ( sscanf( left, "%ld", &a ) != 1 )
    a = 0;

  if ( sscanf( right, "%ld", &b ) != 1 )
    b = 0;

  // We're done with the values returned by our two subexpressions,
  // We just needed to get them as long ints.
  free( left );
  free( right );

  // Compute the result, store it in a dynamically allocated string
  // and return it to the caller.
  char *result = (char *)malloc( MAX_NUMBER + 1 );
  sprintf( result, "%ld", a - b );
  return result;
}

Expr *makeSub( Expr *op1, Expr *op2 )
{
  // Get in a generic instance of BinaryExpr
  BinaryExpr *this = buildBinaryExpr( op1, op2 );

  // Fill in our function to do adding.
  this->eval = evalSub;

  // Return the instance as if it's an Expr (which it sort of is)
  return (Expr *) this;
}

/** For instances of BinaryExpr that do multiplication, this
    is the funciton they call for eval. */
static char *evalMul( Expr *expr, Context *ctxt )
{
  // Get a pointer to the more specific type this function works with.
  BinaryExpr *this = (BinaryExpr *)expr;

  // Evaluate our two operands
  char *left = this->op1->eval( this->op1, ctxt );
  char *right = this->op2->eval( this->op2, ctxt );

  // Parse the left and right operands as long ints.  Set them
  // to zero if they don't parse correctly.
  long a, b;
  if ( sscanf( left, "%ld", &a ) != 1 )
    a = 0;

  if ( sscanf( right, "%ld", &b ) != 1 )
    b = 0;

  // We're done with the values returned by our two subexpressions,
  // We just needed to get them as long ints.
  free( left );
  free( right );

  // Compute the result, store it in a dynamically allocated string
  // and return it to the caller.
  char *result = (char *)malloc( MAX_NUMBER + 1 );
  sprintf( result, "%ld", a * b );
  return result;
}

Expr *makeMul( Expr *op1, Expr *op2 )
{
  // Get in a generic instance of BinaryExpr
  BinaryExpr *this = buildBinaryExpr( op1, op2 );

  // Fill in our function to do adding.
  this->eval = evalMul;

  // Return the instance as if it's an Expr (which it sort of is)
  return (Expr *) this;
}

/** For instances of BinaryExpr that do division, this
    is the funciton they call for eval. */
static char *evalDiv( Expr *expr, Context *ctxt )
{
  // Get a pointer to the more specific type this function works with.
  BinaryExpr *this = (BinaryExpr *)expr;

  // Evaluate our two operands
  char *left = this->op1->eval( this->op1, ctxt );
  char *right = this->op2->eval( this->op2, ctxt );

  // Parse the left and right operands as long ints.  Set them
  // to zero if they don't parse correctly.
  long a, b;
  if ( sscanf( left, "%ld", &a ) != 1 )
    a = 0;

  if ( sscanf( right, "%ld", &b ) != 1 )
    b = 0;

  // We're done with the values returned by our two subexpressions,
  // We just needed to get them as long ints.
  free( left );
  free( right );

  if (b == 0){
    fprintf(stderr, "Runtime Error: divide by zero\n");
    exit(EXIT_FAILURE);
  }

  // Compute the result, store it in a dynamically allocated string
  // and return it to the caller.
  char *result = (char *)malloc( MAX_NUMBER + 1 );
  sprintf( result, "%ld", (a / b) );
  return result;
}

Expr *makeDiv( Expr *op1, Expr *op2 )
{
  // Get in a generic instance of BinaryExpr
  BinaryExpr *this = buildBinaryExpr( op1, op2 );

  // Fill in our function to do adding.
  this->eval = evalDiv;

  // Return the instance as if it's an Expr (which it sort of is)
  return (Expr *) this;
}

/** For instances of BinaryExpr that do equality, this
    is the funciton they call for eval. */
static char *evalEqual( Expr *expr, Context *ctxt )
{
  // Get a pointer to the more specific type this function works with.
  BinaryExpr *this = (BinaryExpr *)expr;

  // Evaluate our two operands
  char *left = this->op1->eval( this->op1, ctxt );
  char *right = this->op2->eval( this->op2, ctxt );


  char* trueResult = ("true");

  // Compute the result, store it in a dynamically allocated string
  // and return it to the caller.
  //the longest it could be is the length of "true" plus the null
  char *result = (char *)malloc( strlen(trueResult) + 1 );

  //printf("left: %s \n right: %s \n", left, right);

  if (strcmp(left, right) == 0) { //TODO flag
    sprintf( result, "%s", trueResult);
  } else {
    sprintf(result, "%s", ""); //empty string is false
  }

  // We're done with the values returned by our two subexpressions,
  free( left );
  free( right );

  return result;
}

Expr *makeEqual( Expr *op1, Expr *op2 )
{
   // Get in a generic instance of BinaryExpr
  BinaryExpr *this = buildBinaryExpr( op1, op2 );

  // Fill in our function to do equality.
  this->eval = evalEqual;

  // Return the instance as if it's an Expr (which it sort of is)
  return (Expr *) this;
}

/** For instances of BinaryExpr that do less, this
    is the funciton they call for eval. */
static char *evalLess( Expr *expr, Context *ctxt )
{
  // Get a pointer to the more specific type this function works with.
  BinaryExpr *this = (BinaryExpr *)expr;

  // Evaluate our two operands
  char *left = this->op1->eval( this->op1, ctxt );
  char *right = this->op2->eval( this->op2, ctxt );

  // Parse the left and right operands as long ints.  Set them
  // to zero if they don't parse correctly.
  long a, b;
  if ( sscanf( left, "%ld", &a ) != 1 )
    a = 0;

  if ( sscanf( right, "%ld", &b ) != 1 )
    b = 0;

  // We're done with the values returned by our two subexpressions,
  // We just needed to get them as long ints.
  free( left );
  free( right );

  char* trueResult = ("true");

  // Compute the result, store it in a dynamically allocated string
  // and return it to the caller.
  //the longest it could be is the length of "true" plus the null
  char *result = (char *)malloc( strlen(trueResult) + 1 );

  if (a < b) {
    strcpy( result, trueResult);
  } else {
    sprintf(result, "%s", ""); //empty string is flase
  }

  return result;
}

Expr *makeLess( Expr *op1, Expr *op2 )
{
  // Get in a generic instance of BinaryExpr
  BinaryExpr *this = buildBinaryExpr( op1, op2 );

  // Fill in our function to do equality.
  this->eval = evalLess;

  // Return the instance as if it's an Expr (which it sort of is)
  return (Expr *) this;
}

// Representation for a Literal expression, derived from Expr.
typedef struct {
  char *(*eval)( Expr *oper, Context *ctxt );
  void (*destroy)( Expr *oper );

  /** Value of this expression. */
  Expr *op;
} NotExpr;

/** This is the funciton not expressions call for eval.*/
static char *evalNot( Expr *expr, Context *ctxt )
{
  // Cast the this pointer to a more specific type.
  NotExpr *this = (NotExpr *)expr;

  char* value = this->op->eval(this->op, ctxt);

  char* trueResult = ("true");

  // Compute the result, store it in a dynamically allocated string
  // and return it to the caller.
  //the longest it could be is the length of "true" plus the null
  char *result = (char *)malloc( strlen(trueResult) + 1 );

  if (strcmp(value, "") == 0) {
    strcpy( result, trueResult);
  } else {
    sprintf(result, "%s", ""); //empty string is flase
  }
  free(value);
  return result;
}

// Function to free a Not expression.
static void destroyNot( Expr *expr )
{
  // Cast the this pointer to a more specific type.
  NotExpr *this = (NotExpr *)expr;

  // Free the value we contain and the Not object itself.
  this->op->destroy(this->op);
  free( this );
}

Expr *makeNot( Expr *op )
{

  // Allocate space for the NotExpr object
  NotExpr *this = (NotExpr *) malloc( sizeof( NotExpr ) );

  // Remember our virutal functions.
  this->eval = evalNot;
  this->destroy = destroyNot;

  // Remember the operator we contain.
  this->op = op;

  // Return the result, as an instance of the base.
  return (Expr *) this;
}
  ////////////////////////////////////////////////////////////////////////////////////
 //////////////////////////MakeVariable//////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////
typedef struct {
  char *(*eval)( Expr *oper, Context *ctxt );
  void (*destroy)( Expr *oper );
  //FLAG VARIABLEEXPRE

  /** Literal value of this expression. */
  const char *name;
} VariableExpr;

/** This is the function instances of
  variable expression call for eval*/
static char *evalVariable( Expr *expr, Context *ctxt )
{
  // Cast the this pointer to a more specific type.
  VariableExpr *this = (VariableExpr *)expr;

  char *val = malloc(MAX_TOKEN + 1);
  if (!isEmpty(ctxt)){
    char *varValue =(char*) getVariable(ctxt, (char*)this->name);

    sprintf(val, "%s", varValue);
  } else {
    strcpy(val, this->name);
  }

  // Make and return a copy of the value we contain.
  //printf("%s", val);
  return (char*) val;
}

// Function to free a literal expression.
static void destroyVariable( Expr *expr )
{
  // Cast the this pointer to a more specific type.
  VariableExpr *this = (VariableExpr *)expr;

  // Free the value we contain and the literal object itself.
  free( (char*) this->name );
  free( this );
}

Expr *makeVariable( char const *name )
{

  // Allocate space for the VariableExper object
  VariableExpr *this = (VariableExpr *) malloc( sizeof( VariableExpr ) );

  // Remember our virtual functions.
  this->eval = evalVariable;
  this->destroy = destroyVariable;

  // Remember the literal string we contain.
  this->name = name;

  // Return the result, as an instance of the base.
  return (Expr *) this;

}

  //////////////////////////////////////////////////////////////////////////////////////
 ///////////////////////////////////set////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////
typedef struct {
  char *(*eval)( Expr *oper, Context *ctxt );
  void (*destroy)( Expr *oper );

  /** Literal value of this expression. */
  const char *name;
  Expr *val;
} SetExpr;

/** For instances of Expr that do set, this
    is the funciton they call for eval. */
static char *evalSet( Expr *expr, Context *ctxt )
{
  // Cast the this pointer to a more specific type.
  SetExpr *this = (SetExpr *)expr;


  char* value = this->val->eval(this->val, ctxt);
  setVariable(ctxt, this->name, value);

  free(value);

  // Make and return a copy of the value we contain.
  return this->val->eval(this->val, ctxt);
}

// Function to free a literal expression.
static void destroySet( Expr *expr )
{
  // Cast the this pointer to a more specific type.
  SetExpr *this = (SetExpr *)expr;

  // Free the value we contain and the literal object itself.
  //free( this->val );
  this->val->destroy( this->val );
  free( (char*) this->name );
  free( this );
}

Expr *makeSet( char const *name, Expr *value )
{

  // Allocate space for the VariableExper object
  SetExpr *this = (SetExpr *) malloc( sizeof( SetExpr ) );

  // Remember our virtual functions.
  this->eval = evalSet;
  this->destroy = destroySet;

  // Remember the literal string we contain.
  this->name = name;
  this->val = value;

  // Return the result, as an instance of the base.
  return (Expr *) this;

}

/** For instances of BinaryExpr that do if, this
    is the funciton they call for eval. */
static char *evalIf( Expr *expr, Context *ctxt )
{
  // Get a pointer to the more specific type this function works with.
  BinaryExpr *this = (BinaryExpr *)expr;

  // Evaluate our two operands
  char *cond = this->op1->eval( this->op1, ctxt );

  //if condition is false, return it. It's already dynamically allocated, after all.
  if ( strcmp(cond, "") == 0 ) {
    return cond;
  }

  //otherwise evaluate the body.
  char *evaluate = this->op2->eval( this->op2, ctxt );
  //free the evaluation.
  free(evaluate);
  //return the condition.
  return cond;
}

Expr *makeIf( Expr *op1, Expr *op2 )
{
  // Get in a generic instance of BinaryExpr
  BinaryExpr *this = buildBinaryExpr( op1, op2 );

  // Fill in our function to do adding.
  this->eval = evalIf;

  // Return the instance as if it's an Expr (which it sort of is)
  return (Expr *) this;
}

/** For instances of BinaryExpr that do while, this
    is the funciton they call for eval. */
static char *evalWhile( Expr *expr, Context *ctxt )
{
  // Get a pointer to the more specific type this function works with.
  BinaryExpr *this = (BinaryExpr *)expr;

  // Evaluate our two operands
  char *cond = this->op1->eval( this->op1, ctxt );

  //this->op2 = this->op2->eval( this->op2, ctxt );

  int i;
  //as it turns out, you can use a for loop as a while loop with an automatically updating counter.
  for (i = 0; strcmp(cond, "") != 0; i++ ) {

    char* evaluation = this->op2->eval( this->op2, ctxt );

    free(evaluation);
    free(cond);

    cond = this->op1->eval( this->op1, ctxt );

  }
  //i shouldn't be very long in terms of char length.
  char* result = (char *) malloc(MAX_VAR_NAME + 1);
  sprintf(result, "%d", i);
  free(cond);
  return result;
}

Expr *makeWhile( Expr *op1, Expr *op2 )
{
  // Get in a generic instance of BinaryExpr
  BinaryExpr *this = buildBinaryExpr( op1, op2 );

  // Fill in our function to do adding.
  this->eval = evalWhile;

  // Return the instance as if it's an Expr (which it sort of is)
  return (Expr *) this;
}


/** For instances of BinaryExpr that do and, this
    is the funciton they call for eval. */
static char *evalAnd( Expr *expr, Context *ctxt )
{
  // Get a pointer to the more specific type this function works with.
  BinaryExpr *this = (BinaryExpr *)expr;

  // Evaluate our left operand
  char *left = this->op1->eval( this->op1, ctxt );

  //if left condition is false, return it. It's already dynamically allocated, after all.
  if ( strcmp(left, "") == 0 ) {
    return left;
  }
  free(left);

  //otherwise evaluate the right.
  char *right = this->op2->eval( this->op2, ctxt );
  //if right condition is false, return it. It's already dynamically allocated, after all.
  if ( strcmp(right, "") == 0 ) {
    return right;
  }
  //free the evaluation.

  free(right);
  //if it's gotten here, it's true.
  char *truth = "true";
  char *result = malloc(sizeof(true) + 1);

  sprintf(result, "%s", truth);
  return result;
}

Expr *makeAnd( Expr *op1, Expr *op2 )
{
  // Get in a generic instance of BinaryExpr
  BinaryExpr *this = buildBinaryExpr( op1, op2 );

  // Fill in our function to do adding.
  this->eval = evalAnd;

  // Return the instance as if it's an Expr (which it sort of is)
  return (Expr *) this;
}

/** For instances of BinaryExpr that do or, this
    is the funciton they call for eval. */
static char *evalOr( Expr *expr, Context *ctxt )
{
  // Get a pointer to the more specific type this function works with.
  BinaryExpr *this = (BinaryExpr *)expr;

  // Evaluate our left operand
  char *left = this->op1->eval( this->op1, ctxt );

  //if left condition is true, return result.
  if ( strcmp(left, "") != 0 ) {
    char *truth = "true";
    char *result = malloc(sizeof(true) + 1);

    sprintf(result, "%s", truth);
    return result;
  }
  free(left);

  //otherwise evaluate the right.
  char *right = this->op2->eval( this->op2, ctxt );
  //if right condition is true, return result.
  if ( strcmp(right, "") != 0 ) {
    char *truth = "true";
    char *result = malloc(sizeof(true) + 1);

    sprintf(result, "%s", truth);
    return result;
  }

  //if it's gotten here, it's false. return right; already dynamically allocated
  return right;
}

Expr *makeOr( Expr *op1, Expr *op2 )
{
  // Get in a generic instance of BinaryExpr
  BinaryExpr *this = buildBinaryExpr( op1, op2 );

  // Fill in our function to do adding.
  this->eval = evalOr;

  // Return the instance as if it's an Expr (which it sort of is)
  return (Expr *) this;
}

/** For instances of BinaryExpr that do concatenation, this
    is the funciton they call for eval. */
static char *evalCat( Expr *expr, Context *ctxt )
{
  // Get a pointer to the more specific type this function works with.
  BinaryExpr *this = (BinaryExpr *)expr;

  // Evaluate our left operand
  char *left = this->op1->eval( this->op1, ctxt );
  char *right = this->op2->eval( this->op2, ctxt );

  char *result = malloc(strlen(left)+ strlen(right) + 1);

  sprintf(result, "%s%s", left, right);

  free(left);
  free(right);

  return result;
}

Expr *makeCat( Expr *op1, Expr *op2 )
{
  // Get in a generic instance of BinaryExpr
  BinaryExpr *this = buildBinaryExpr( op1, op2 );

  // Fill in our function to do adding.
  this->eval = evalCat;

  // Return the instance as if it's an Expr (which it sort of is)
  return (Expr *) this;
}

typedef struct {
  char *(*eval)( Expr *oper, Context *ctxt );
  void (*destroy)( Expr *oper );

  /** Literal value of this expression. */
  Expr *val;
  Expr *begin;
  Expr *end;
} SubstringExpr;

static void destroySubtring( Expr *expr ){

  SubstringExpr *this = (SubstringExpr *)expr;
  this->val->destroy(this -> val);
  this->begin->destroy(this -> begin);
  this->end->destroy(this -> end);

  free( this );
}

/** For instances of Expr that do substring, this
    is the funciton they call for eval. */
static char *evalSubstring( Expr *expr, Context *ctxt )
{
  // Get a pointer to the more specific type this function works with.
  SubstringExpr *this = (SubstringExpr *)expr;

  // Evaluate our left operand
  char *name = this->val->eval( this->val, ctxt );
  char *begin = this->begin->eval( this->begin, ctxt );
  char *end = this->end->eval( this->end, ctxt );


  // Parse the begin and end operands as long ints.  Set them
  // to zero if they don't parse correctly.
  long a, b;
  if ( sscanf( begin, "%ld", &a ) != 1 )
    a = 0;

  if ( sscanf( end, "%ld", &b ) != 1 )
    b = 0;

  int length = strlen(name);
  if ( a < 0 )
    a = 0;
  if (b < a) {
    free(name);
    free(begin);
    free(end);
    char *result = malloc(1);
    strcpy(result, "");
    return result;
  }

  if (b > length){
    b = length;
  }

  char *result = malloc(length);

  int j = 0;
  for (int i = a; i < b; i++, j++) {
    result[j] = name[i];
  }
  result[j] = '\0';
  free(begin);
  free(end);
  free(name);
  return result;
}

Expr *makeSubstring( Expr *op1, Expr *op2, Expr *op3 )
{
  // Get in a generic instance of BinaryExpr
  SubstringExpr *this = malloc(sizeof(SubstringExpr));

  // Fill in our function to do adding.
  this->eval = evalSubstring;
  this->destroy = destroySubtring;

  this->val = op1;
  this->begin = op2;
  this->end = op3;

  // Return the instance as if it's an Expr (which it sort of is)
  return (Expr *) this;
}
