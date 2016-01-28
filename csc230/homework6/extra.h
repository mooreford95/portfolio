/**
  @file extra.h
  @author David Sturgill (dbsturgi)

  Representation for extra language funcitonality, mostly expression types added
  by the student.
*/

#ifndef _EXTRA_H_
#define _EXTRA_H_

#include "core.h"

/** Make an expression that interprets its operands as long ints and
    evaluates to their sum.
    @param op1 expression for the left-hand operand
    @param op2 expression for the right-hand operand
    @return a new expression object that adds the values of op1 and op2
 */
Expr *makeAdd( Expr *op1, Expr *op2 );

/** Make an expression that interprets its operands as long ints and
    evaluates to their difference.
    @param op1 expression for the left-hand operand
    @param op2 expression for the right-hand operand
    @return a new expression object that subtracts the values of op1 and op2
 */
Expr *makeSub( Expr *op1, Expr *op2 );

/** Make an expression that interprets its operands as long ints and
    evaluates to their product.
    @param op1 expression for the left-hand operand
    @param op2 expression for the right-hand operand
    @return a new expression object that multiplies the values of op1 and op2
 */
Expr *makeMul( Expr *op1, Expr *op2 );

/** Make an expression that interprets its operands as long ints and
    evaluates to their quotient. Checks that denomenator is nonzero.
    @param op1 expression for the left-hand operand
    @param op2 expression for the right-hand operand
    @return a new expression object that divides the values of op1 and op2
 */
Expr *makeDiv( Expr *op1, Expr *op2 );

/** Make an expression that interprets its operands and checks
    their equality
    @param op1 expression for the left-hand operand
    @param op2 expression for the right-hand operand
    @return a new expression object which will evaluate the equality of the operands
 */
Expr *makeEqual( Expr *op1, Expr *op2 );

/** Make an expression that interprets its operands and checks
    which is greater
    @param op1 expression for the left-hand operand
    @param op2 expression for the right-hand operand
    @return a new expression object which will evaluate the gravity of the operands
 */
Expr *makeLess( Expr *op1, Expr *op2 );

/** Make an expression that interprets an operand, checks
    its value, then negates it.
    @param op1 expression for operand
    @return a new expression object which will evaluate to the oposite value of the operand
 */
Expr *makeNot( Expr *op );

/** Take in a name. Make a variable.
    @param name of the variable to be made/evaluated.
    @return a new expression object that evaluates to the value of the variable.
 */
Expr *makeVariable( char const *name );

/** Take in a name. Take in a value. Make a variable with that value.
    @param name of the variable to be made/evaluated.
    @param expr expresion to evaluate to value of variable
    @return a new expression object that evaluates to the value of the variable.
 */
Expr *makeSet( char const *name, Expr *expr );

/** Make an expression that interprets an operand, checks
    its value, then executes a second expression if the first was true.
    @param cond expression to evaluate.
    @param body expression to evaluate if first is true.
    @return a new expression object which will evaluate to the value of the condition
 */
Expr *makeIf( Expr *cond, Expr *body );

/** Make an expression that interprets an operand, checks
    its value, then executes a second expression until the first is no longer true.
    @param cond expression to evaluate.
    @param body expression to evaluate while first is true.
    @return a new expression object which will evaluate to the number of times the condition ran
 */
Expr *makeWhile( Expr *cond, Expr *body );

/** Make an expression that interprets an operand, checks
    its value, then evaluates a second expression if the first was false.
    @param op1 expression to evaluate.
    @param op2 expression to evaluate if first is false.
    @return a new expression object which will evaluate to the value of the overall condition
 */
Expr *makeOr( Expr *op1, Expr *op2 );

/** Make an expression that interprets an operand, checks
    its value, then evaluates a second expression if the first was true.
    @param op1 expression to evaluate.
    @param op2 expression to evaluate if first is true.
    @return a new expression object which will evaluate to the value of the overall condition
 */
Expr *makeAnd( Expr *op1, Expr *op2 );

/** Make an expression to concatenate two strings.
    @param op1 expression for the left-hand operand
    @param op2 expression for the right-hand operand
    @return a new expression object that concatenates the values of op1 and op2
 */
Expr *makeCat( Expr *op1, Expr *op2 );

/** Make an expression to concatenate two strings.
    @param op1 expression for the left-hand operand
    @param op2 expression for the right-hand operand
    @return a new expression object that concatenates the values of op1 and op2
 */
Expr *makeSubstring( Expr *op1, Expr *op2, Expr *op3);


#endif
